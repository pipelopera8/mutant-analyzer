package co.com.mutantanalyzer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.com.mutantanalyzer.dto.DnaDTO;
import co.com.mutantanalyzer.exception.MutantAnalyzerExceptionHandler;

@SpringBootTest
class MutantServiceTest {

	@Test
	void contextLoads() {
		MockitoAnnotations.initMocks(this);
	}
	
    @Mock
    private MessageSource messageSource;

    @InjectMocks
    @Spy
    private MutantService mutantService;

    @Test
    public void isMutantTrue() throws MutantAnalyzerExceptionHandler {
    	DnaDTO dna = new DnaDTO();
    	ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.OK);;
        dna.setDna(new String [] {"ATGCTA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
        assertEquals(response, mutantService.isMutant(dna));
    }

    @Test
    public void isMutantNull() throws MutantAnalyzerExceptionHandler {
    	ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.FORBIDDEN);;
    	DnaDTO dna = new DnaDTO();
    	assertEquals(response, mutantService.isMutant(dna));
    }

    @Test
    public void isMutantFalse() throws MutantAnalyzerExceptionHandler {
    	DnaDTO dna = new DnaDTO();
    	ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.FORBIDDEN);;
    	dna.setDna(new String [] {"ATCCGA","CCGTGC","TTATGT","AGAGTG","CCGCTA","TCACTG"});
    	assertEquals(response, mutantService.isMutant(dna));
    }
    
    @Test
    public void isMutantDistinctLetter() throws MutantAnalyzerExceptionHandler {
    	DnaDTO dna = new DnaDTO();
    	ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.FORBIDDEN);;
    	dna.setDna(new String [] {"ATGCTA","ZAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
    	assertEquals(response, mutantService.isMutant(dna));
    }


    @Test
    public void isMutantNullDna() throws MutantAnalyzerExceptionHandler {
    	ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.FORBIDDEN);;
    	assertEquals(response, mutantService.isMutant(null));
    }
    
//    @Test
//    public void getStatsNotNull() throws MutantAnalyzerExceptionHandler {
//    	assertNotNull(mutantService.getStats());
//    }
//    
//    @Test
//    public void getStatsNull() throws MutantAnalyzerExceptionHandler {
//    	assertNotEquals(null, mutantService.getStats());
//    }
	
}
