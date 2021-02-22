
package co.com.mutantanalyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.mutantanalyzer.dto.DnaDTO;
import co.com.mutantanalyzer.dto.StatsDTO;
import co.com.mutantanalyzer.general.constant.Constant;
import co.com.mutantanalyzer.general.exception.MutantAnalyzerExceptionHandler;
import co.com.mutantanalyzer.model.Mutant;
import co.com.mutantanalyzer.repository.MutantRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * Descripción: Servicio encargado de manejar la logíaca del proceso de
 * selección de mutantes
 * 
 * @author felipe.lopera
 *
 */
@Service
@Slf4j
public class MutantService {

	@Autowired
	private MutantRepository mutantRepository;

	/**
	 * Método en cargado en determinar si la cadena de adn ingresada es un mutante o
	 * no
	 * 
	 * @param dna
	 * @return
	 * @throws MutantAnalyzerExceptionHandler
	 */
	public ResponseEntity<Object> isMutant(DnaDTO dna) {
		log.info("Comienza el proceo de verificación de adn, que determina si es mutante o no");
		Mutant mutant = new Mutant();
		ResponseEntity<Object> response = null;
		try {
			int count = 0;
			log.info("Valida si la cadena de adn esta vacia");
			if (dna != null && dna.getDna() != null) {
				log.info("Se evalua la matriz de adn primero por filas");
				for (String string : dna.getDna()) {
					if ((checkRow(string))) {
						count++;
					}
				}
				log.info("Si en las filas ya encontro por lo menos dos conincidencias deja de evaluar el resto de la matriz");
				if (count < 2) {
					log.info("Convierte las columnas en filas para hacer la misma evaluación por filas");
					for (String string : converterRowToColumn(dna.getDna())) {
						if ((checkRow(string))) {
							count++;
						}
					}
				}
				log.info("Si con las filas y las columnas ya encontro por lo menos dos conincidencias deja de evaluar el resto de la matriz");
				if (count < 2) {
					log.info("Evalua las diagonales, tanto la principal como la inversa para deerminar si tiene o no mas coincidencias de mutante");
					count += evaluateDiagonal(getMatriz(dna.getDna()));
				}
				log.info("Determina si  es un mutante o un humano, si es un mutante es porque el contador de concidiencias es por menos de 2 o mas");
				if (count < 2) {
					log.info("No es un mutante");
					mutant.setMutant(false);
					response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
				} else {
					log.info("Si es un mutante");
					mutant.setMutant(true);
					response = new ResponseEntity<>(HttpStatus.OK);
				}
			} else {
				mutant.setMutant(false);
				response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			log.info("si la cadena tiene alguna letra diferente de acgt");
		}
		
		new Thread() {
			public void run() {
				mutantRepository.save(mutant);
			}
		}.start();
		
		return response;
	}

	/**
	 * Vericica si en una fila se tienen base nitrogenada repedias mas de 4 veces
	 * 
	 * @param dna
	 * @return
	 */
	private static boolean checkRow(String dna){
		if(dna.contains(Constant.A) || dna.contains(Constant.C) || dna.contains(Constant.G) ||dna.contains(Constant.T))
			return true;
		return false;
	}

	/**
	 * Método encargado de convertir columna en fila, para luego ser evaluada de manera mas optima solo la fila
	 * 
	 * @param dnas
	 * @return
	 */
	private static String[] converterRowToColumn(String[] dnas) {
		char stringArray[] = dnas[0].toCharArray();
		String dna = "";
		int index = 0;
		String[] dnaReturn = new String[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			char dnaChar[] = dnas[i].toCharArray();
			dna += dnaChar[index];
			if (dna.length() == stringArray.length) {
				dnaReturn[index] = dna;
				index++;
				i = -1;
				dna = "";
			}
			if (index == stringArray.length) {
				break;
			}
		}
		return dnaReturn;
	}

	/**
	 * Método encargado de retonar las estadisticas
	 * 
	 * @param dna
	 * @return
	 * @throws MutantAnalyzerExceptionHandler
	 */
	public ResponseEntity<Object> getStats() throws MutantAnalyzerExceptionHandler {
		StatsDTO stats = new StatsDTO();
		Double ratio = 0D;
		Long countAll = mutantRepository.count();
		Long countMutant = mutantRepository.countByMutantTrue();
		Long countHuman = countAll - countMutant;
		if (countHuman == 0) {
			ratio = 1D;
		} else {
			ratio = countMutant.doubleValue() / countHuman.doubleValue();
		}
		stats.setCount_human_dna(countHuman);
		stats.setCount_mutant_dna(countMutant);
		stats.setRatio(ratio);
		return new ResponseEntity<>(stats, HttpStatus.OK);
	}

	/**
	 * Método encargado de evaluar la matriz de adn, para determinar si en alguna de
	 * las diagonales conincide la base nitrogenada
	 * 
	 * @param dna
	 * @return
	 * @throws MutantAnalyzerExceptionHandler
	 */
	public static int evaluateDiagonal(String[][] dna) throws MutantAnalyzerExceptionHandler {
		String[] principal = new String[dna.length];
		String[] secondary = new String[dna.length];
		int count = 0;
		for (int i = 0; i < dna.length; i++) {
			for (int j = 0; j < dna[i].length; j++) {
				if (i == j) {
					principal[i] = dna[i][j];
				}

				if (i + j == dna.length - 1) {
					secondary[i] = dna[i][j];
				}
			}
		}
		String charDna = "";
		for (int i = 0; i < principal.length; i++) {
			charDna += principal[i];
		}
		if ((checkRow(charDna))) {
			count++;
		}
		charDna = "";
		for (int i = 0; i < secondary.length; i++) {
			charDna += secondary[i];
		}
		if ((checkRow(charDna))) {
			count++;
		}
		return count;
	}

	/**
	 * Método encargdo de convertir el arreglo de adn's en una matriz para ser
	 * evaluada
	 * 
	 * @param dnas
	 * @return
	 */
	private static String[][] getMatriz(String[] dnas) {
		char stringArray[] = dnas[0].toCharArray();
		String[][] charDna = new String[stringArray.length][stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			char dnaChar[] = dnas[i].toCharArray();
			for (int j = 0; j < dnaChar.length; j++) {
				charDna[i][j] = String.valueOf(dnaChar[j]);
			}
		}
		return charDna;
	}

}
