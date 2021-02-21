package co.com.mutantanalyzer.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import co.com.mutantanalyzer.general.exception.MutantAnalyzerExceptionHandler;
import co.com.mutantanalyzer.model.Mutant;
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
    	Mutant mutant = new Mutant();
    	mutant.setMutant(false);
    	Mockito.when(mutantRepository.save(mutant)).thenReturn(mutant);
    	boolean error = false;
    	try {
        mutantService.saveMutant(mutant);
        }catch (Exception e) {
        	error = true;
		}
    	assertFalse(error);
    }
    
}
