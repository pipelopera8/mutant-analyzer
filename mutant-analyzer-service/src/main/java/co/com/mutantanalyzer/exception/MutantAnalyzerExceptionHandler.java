
package co.com.mutantanalyzer.exception;

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

	@SuppressWarnings("unused")
	private String message;

	/**
	 * constructor por defecto
	 */
	public MutantAnalyzerExceptionHandler() {

	}

	/**
	 * @param message
	 */
	public MutantAnalyzerExceptionHandler(String message) {
		this.message = message;
	}


}