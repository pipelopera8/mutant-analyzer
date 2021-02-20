package co.com.mutantanalyzer.general.util;

import co.com.mutantanalyzer.general.constants.ResponseCodes;

/**
 * Clase de respuesta para servicios REST
 * 
 * @author felipe.lopera
 *
 * @param <T>
 */
public class ResponseObject<T> {

	/**
	 * Código de respuesta
	 */
	private String code;

	/**
	 * Mensaje de respuesta
	 */
	private String message;

	/**
	 * Cuerpo de la respuesta
	 */
	private T body;

	/**
	 * Agrega código y mensaje de éxito
	 * 
	 * @param body cuerpo de la respuesta
	 */
	public ResponseObject(T body) {
		this.body = body;
		this.code = ResponseCodes.REQUEST_SUCCESS_CODE;
		this.message = ResponseCodes.REQUEST_SUCCESS_MESSAGE;
	}

	/**
	 * Cuerpo de respuesta vacío
	 * 
	 * @param code    código de la respuesta
	 * @param message mensaje de la respuesta
	 */
	public ResponseObject(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @param body    cuerpo de la respuesta
	 * @param code    código de la respuesta
	 * @param message mensaje de la respuesta
	 */
	public ResponseObject(T body, String code, String message) {
		this.body = body;
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
