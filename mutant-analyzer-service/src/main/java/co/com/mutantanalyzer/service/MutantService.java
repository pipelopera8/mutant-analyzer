
package co.com.mutantanalyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.mutantanalyzer.dto.DnaDTO;
import co.com.mutantanalyzer.dto.StatsDTO;
import co.com.mutantanalyzer.general.exception.MutantAnalyzerExceptionHandler;
import co.com.mutantanalyzer.model.Mutant;
import co.com.mutantanalyzer.rabbitmq.Sender;
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
	private Sender sender;

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
		try {
			int count = 0;
			if (dna != null && dna.getDna() != null) {
				for (String string : dna.getDna()) {
					if ((checkRow(string))) {
						count++;
					}
				}
				for (String string : converterRowToColumn(dna.getDna())) {
					if ((checkRow(string))) {
						count++;
					}
				}
				if (count <= 1) {
					log.info("No es un mutante");
					mutant.setMutant(false);
					send(mutant);
					return new ResponseEntity<>(HttpStatus.FORBIDDEN);
				} else {
					log.info("Si es un mutante");
					mutant.setMutant(true);
					send(mutant);
					return new ResponseEntity<>(HttpStatus.OK);
				}
			} else {
				mutant.setMutant(false);
				send(mutant);
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} catch (MutantAnalyzerExceptionHandler e) {
			mutant.setMutant(false);
			send(mutant);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Vericica si en una fila se tienen base nitrogenada repedias mas de 4 veces
	 * 
	 * @param dna
	 * @return
	 */
	private static boolean checkRow(String dna) throws MutantAnalyzerExceptionHandler {
		char stringArray[] = dna.toCharArray();
		int ocurrence = 1;
		for (int i = 0; i < stringArray.length; i++) {
			if (i + 1 < stringArray.length) {
				if (stringArray[i] != 'A' && stringArray[i] != 'C' && stringArray[i] != 'G' && stringArray[i] != 'T') {
					throw new MutantAnalyzerExceptionHandler("Cadena invalida");
				}
				if (stringArray[i] == stringArray[i + 1]) {
					ocurrence++;
				}
			}
			if (ocurrence >= 4) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método encargado de convertir una fila en columna
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

	private void send(Mutant mutant) {
		new Thread() {
			public void run() {
				sender.send(mutant.toByteArray(mutant));
			}
		}.start();
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
		if(countHuman != 0) {
			ratio = countMutant.doubleValue()/countHuman.doubleValue();
		}
		stats.setCount_human_dna(countHuman);
		stats.setCount_mutant_dna(countMutant);
		stats.setRatio(ratio);
		return new ResponseEntity<>(stats, HttpStatus.OK);
	}
}
