package co.com.mutantanalyzer.general.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Descripción convierte un tipo de dato boolean a String
 * 
 * @author felipe.lopera
 *
 */
@Converter
public class BooleanToStringConverter implements AttributeConverter<Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean value) {
		return (value == null || !value) ? "N" : "Y";
	}

	@Override
	public Boolean convertToEntityAttribute(String value) {
		return "Y".equals(value);
	}
}
