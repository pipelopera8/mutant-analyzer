package co.com.mutantanalyzer.general.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.mutantanalyzer.general.exception.MutantAnalyzerExceptionHandler;

@SpringBootTest
class UtilsTest {

	@Test
	void contextLoads() {
		MockitoAnnotations.initMocks(this);
	}
	
    @InjectMocks
    @Spy
    private Utils utils;
    
    @Test
    public void byteToObjectFail() throws MutantAnalyzerExceptionHandler {
    	byte[] obj = new byte[1];
    	boolean error = false;
    	try {
	    	utils.byteToObject(obj);
        }catch (Exception e) {
        	error = true;
		}
    	assertTrue(error);
    }

    
}
