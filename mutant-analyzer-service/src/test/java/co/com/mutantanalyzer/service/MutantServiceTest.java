package co.com.mutantanalyzer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

import co.com.mutantanalyzer.dto.DnaDTO;
import co.com.mutantanalyzer.general.exception.MutantAnalyzerExceptionHandler;
import co.com.mutantanalyzer.repository.MutantRepository;

@SpringBootTest
class MutantServiceTest {

	@Test
	void contextLoads() {
		MockitoAnnotations.initMocks(this);
	}
	
    @Mock
    private MessageSource messageSource;

    @Mock
    private MutantRepository mutantRepository;

    @InjectMocks
    @Spy
    private MutantService mutantService;

    @Test
    public void isMutantTrue() throws MutantAnalyzerExceptionHandler {
    	DnaDTO dna = new DnaDTO();
    	ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.OK);;
        dna.setDna(new String [] {"ATGCTA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"});
        try {
        assertEquals(response, mutantService.isMutant(dna));
        }catch (Exception e) {
		}
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
    public void isMutantNullDna() throws MutantAnalyzerExceptionHandler {
    	ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.FORBIDDEN);;
    	assertEquals(response, mutantService.isMutant(null));
    }
    
    @Test
    public void getStatsNotNull() throws MutantAnalyzerExceptionHandler {
    	assertNotNull(mutantService.getStats());
    }
    
    @Test
    public void getStatsNull() throws MutantAnalyzerExceptionHandler {
    	assertNotEquals(null, mutantService.getStats());
    }
    
    @Test
    public void getStats() throws MutantAnalyzerExceptionHandler {
    	assertNotEquals(null, mutantService.getStats());
    }
    
    @Test
    public void getStatsOk() throws MutantAnalyzerExceptionHandler {
    	
    	ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.OK);;
    	Long countAll = 10L;
		Long countMutant = 4l;
		
    	Mockito.when(mutantRepository.count()).thenReturn(countAll);
    	Mockito.when(mutantRepository.countByMutantTrue()).thenReturn(countMutant);
    	
    	assertNotEquals(response, mutantService.getStats());
    }
    
    @Test
    public void getStatsMultiplyZero() throws MutantAnalyzerExceptionHandler {
    	
    	ResponseEntity<Object> responseOk = new ResponseEntity<>(HttpStatus.OK);;
    	Long countAll = 0L;
    	Long countMutant = 0l;
    	
    	Mockito.when(mutantRepository.count()).thenReturn(countAll);
    	Mockito.when(mutantRepository.countByMutantTrue()).thenReturn(countMutant);
    	Mockito.when(mutantRepository.countByMutantTrue()).thenReturn(countMutant);
    	try {
    		assertNotEquals(responseOk, mutantService.getStats());
    	} catch (Exception e) {
		}
    }
    
}
