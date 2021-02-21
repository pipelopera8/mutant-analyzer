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

import co.com.mutantanalyzer.dto.DnaDTO;
import co.com.mutantanalyzer.service.MutantService;

@SpringBootTest
public class MutantControllerTest {

    @Mock
    private MessageSource messageSource;

    @Mock
    private MutantService mutantService;
    
    @InjectMocks
    @Spy
    private MutantController mutantController;
    
    @Test
	void contextLoads() {
		MockitoAnnotations.initMocks(this);
	}

    @Test
    public void ismutant() {
    	DnaDTO dna = new DnaDTO();
    	ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.OK);;
        Mockito.when(mutantService.isMutant(dna)).thenReturn(response);

        assertNotNull(mutantController.ismutant(dna));
    }
    
}
