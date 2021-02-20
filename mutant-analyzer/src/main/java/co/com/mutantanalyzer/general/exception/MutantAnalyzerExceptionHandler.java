
package co.com.mutantanalyzer.general.exception;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.com.mutantanalyzer.general.constants.ResponseCodes;
import co.com.mutantanalyzer.general.util.ResponseObject;

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

	@Autowired
	private MessageSource messageSource;

	private static final Logger logger = LoggerFactory.getLogger(MutantAnalyzerExceptionHandler.class);

	private String message;

	private Integer errorCode;

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

	public MutantAnalyzerExceptionHandler(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public MutantAnalyzerExceptionHandler(Integer errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	@Override
	public String getMessage() {

		return message;
	}

	public void setMessage(String message) {

		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * metodo para menejar las excepciones de Hibernate que ocurran en los servicios
	 * rest
	 * 
	 * @param ex
	 * @return ResponseObject
	 */
	@ExceptionHandler(HibernateException.class)
	public ResponseObject<Void> hibernetException(HibernateException ex) {

		logger.error(ex.getCause().getMessage());
		return new ResponseObject<>(ResponseCodes.EXCEPTION_CODE,
				messageSource.getMessage("exception.message", null, LocaleContextHolder.getLocale()));

	}

	/**
	 * metodo para menejar las excepciones de sqlException que ocurran en los
	 * servicios rest
	 * 
	 * @param ex
	 * @return ResponseObject
	 */
	@ExceptionHandler(TransactionException.class)
	public ResponseObject<Void> sqlException(TransactionException ex) {

		logger.error(ex.getCause().getMessage());
		return new ResponseObject<>(ResponseCodes.CONNECTION_CODE,
				messageSource.getMessage("connection.Message", null, LocaleContextHolder.getLocale()));

	}

	/**
	 * Ejemplo de exception aritmetica
	 * 
	 * @param ex
	 * @return ResponseObject
	 */
	@ExceptionHandler(ArithmeticException.class)
	public ResponseObject<Void> sqlExceptionException1(ArithmeticException ex) {

		logger.error(ex.getMessage());
		return new ResponseObject<>("156", ex.getMessage());

	}

	@ExceptionHandler(MutantAnalyzerExceptionHandler.class)
	public ResponseEntity<Object> processException(MutantAnalyzerExceptionHandler ex) {
		System.err.println(ex.getMessage());
		logger.error(ex.getMessage());
		return buildResponseEntity(new ResponseObject<>(ResponseCodes.EXCEPTION_CODE, ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<Object> buildResponseEntity(ResponseObject<Void> responseObject, HttpStatus status) {
		return new ResponseEntity<>(responseObject, status);
	}

}