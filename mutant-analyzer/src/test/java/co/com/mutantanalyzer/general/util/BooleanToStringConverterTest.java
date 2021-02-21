package co.com.mutantanalyzer.general.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.mutantanalyzer.general.exception.MutantAnalyzerExceptionHandler;

@SpringBootTest
class BooleanToStringConverterTest {

	@Test
	void contextLoads() {
		MockitoAnnotations.initMocks(this);
	}
	
    @InjectMocks
    @Spy
    private BooleanToStringConverter converter;
    
    @Test
    public void convertToDatabaseColumnFalse() throws MutantAnalyzerExceptionHandler {
    	boolean error = false;
    	try {
	    	converter.convertToDatabaseColumn(false);
        }catch (Exception e) {
        	error = true;
		}
    	assertFalse(error);
    }

    @Test
    public void convertToDatabaseColumnTrue() throws MutantAnalyzerExceptionHandler {
    	boolean error = false;
    	try {
    		converter.convertToDatabaseColumn(true);
    	}catch (Exception e) {
    		error = true;
    	}
    	assertFalse(error);
    }

    @Test
    public void convertToDatabaseColumnNull() throws MutantAnalyzerExceptionHandler {
    	assertNotNull(converter.convertToDatabaseColumn(null));
    }
    
    @Test
    public void convertToEntityAttributeNull() throws MutantAnalyzerExceptionHandler {
    	assertNotNull(converter.convertToEntityAttribute(null));
    }
    
    @Test
    public void convertToEntityAttributeTrue() throws MutantAnalyzerExceptionHandler {
    	boolean error = false;
    	try {
    		converter.convertToEntityAttribute("N");
    	}catch (Exception e) {
    		error = true;
    	}
    	assertFalse(error);
    }

    
}
