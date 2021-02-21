package co.com.mutantanalyzer.handler;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import co.com.mutantanalyzer.general.exception.MutantAnalyzerExceptionHandler;
import co.com.mutantanalyzer.general.util.Utils;
import co.com.mutantanalyzer.model.Mutant;
import co.com.mutantanalyzer.service.MutantService;

@SpringBootTest
class MutantHandlerTest {

	@Test
	void contextLoads() {
		MockitoAnnotations.initMocks(this);
	}
	
    @Mock
    private Utils utils;
    
    @Mock
    private MutantService mutantService;

    @InjectMocks
    @Spy
    private MutantHandler mutantHandler;

    @Test
    public void receiveOk() throws MutantAnalyzerExceptionHandler {
    	byte[] obj = new byte[1];
    	boolean error = false;
    	try {
	    	Mutant mutant = new Mutant();
	    	Mockito.when(utils.byteToObject(obj)).thenReturn(mutant);
	    	mutantService.saveMutant(mutant);
    		mutantHandler.receive(obj);
        }catch (Exception e) {
        	error = true;
		}
    	assertFalse(error);
    }
    
}
