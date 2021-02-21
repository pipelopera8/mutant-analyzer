package co.com.mutantanalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.mutantanalyzer.dto.DnaDTO;
import co.com.mutantanalyzer.service.MutantService;

@RestController("mutant")
public class MutantController {
	
	@Autowired
	private MutantService mutantService;

	@PostMapping
	public ResponseEntity<Object> ismutant(@RequestBody DnaDTO dna) {
		return mutantService.isMutant(dna);
	}
}
