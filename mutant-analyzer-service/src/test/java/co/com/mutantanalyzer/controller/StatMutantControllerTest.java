package co.com.mutantanalyzer.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.mutantanalyzer.general.exception.MutantAnalyzerExceptionHandler;
import co.com.mutantanalyzer.service.MutantService;

@SpringBootTest
public class StatMutantControllerTest {

    @Mock
    private MessageSource messageSource;

    @Mock
    private MutantService mutantService;
    
    @InjectMocks
    @Spy
    private StatMutantController mutantController;
    
    @Test
	void contextLoads() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
    public void ismutant() throws MutantAnalyzerExceptionHandler {
    	ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.OK);;
        try {
			Mockito.when(mutantService.getStats()).thenReturn(response);
		} catch (MutantAnalyzerExceptionHandler e) {
		}

        assertNotNull(mutantController.getStats());
    }
    
}
