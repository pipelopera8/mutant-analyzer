package co.com.mutantanalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mutantanalyzer.general.exception.MutantAnalyzerExceptionHandler;
import co.com.mutantanalyzer.service.MutantService;
import lombok.extern.slf4j.Slf4j;

@RestController("stant")
@Slf4j
public class StatMutantController {
	
	@Autowired
	private MutantService mutantService;

	@GetMapping(value = "/stats")
	public ResponseEntity<Object> getStats() throws MutantAnalyzerExceptionHandler {
		ResponseEntity<Object> response = null;
		try {
			response = mutantService.getStats();
		} catch (MutantAnalyzerExceptionHandler e) {
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
		return response;
	}
}
