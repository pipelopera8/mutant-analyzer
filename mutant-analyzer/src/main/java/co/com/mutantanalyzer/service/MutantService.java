
package co.com.mutantanalyzer.service;

import org.springframework.stereotype.Service;

import co.com.mutantanalyzer.general.exception.MutantAnalyzerExceptionHandler;
import co.com.mutantanalyzer.model.Mutant;
import co.com.mutantanalyzer.repository.MutantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Descripción: Servicio encargado de manejar el acceso a la base dedatos
 * 
 * @author felipe.lopera
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MutantService {

	private final MutantRepository mutantRepository;

	/**
	 * Método en cargado en determinar si la cadena de adn ingresada es un mutante o
	 * no
	 * 
	 * @param dna
	 * @return
	 * @throws MutantAnalyzerExceptionHandler
	 */
	public void saveMutant(Mutant mutant) throws MutantAnalyzerExceptionHandler {
		log.info("Guarda en la tabla si es mutante o no ");
		mutantRepository.save(mutant);
	}

}
