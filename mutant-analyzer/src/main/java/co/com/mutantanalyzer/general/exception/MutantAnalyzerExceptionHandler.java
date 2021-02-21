
package co.com.mutantanalyzer.general.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Se crea clase para manejo de excepciones en las ejecuciones de los servicios
 * REST
 * 
 * @author felipe.lopera
 *
 */
@SuppressWarnings("serial")
@RestControllerAdvice
public class MutantAnalyzerExceptionHandler extends Exception {


	/**
	 * constructor por defecto
	 */
	public MutantAnalyzerExceptionHandler() {
	}

}