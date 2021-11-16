package com.moraes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moraes.service.WorstProducer;
import com.moraes.usecase.WorstProducerUseCase;


@RestController
@RequestMapping("/piorfilme")
public class PiorFilme {

	@Autowired
	private WorstProducerUseCase worstProducerUseCase;
	
	
	@GetMapping	
	public ResponseEntity<String> obterPior() {
		String resultJson = worstProducerUseCase.execute();
		return ResponseEntity.ok(resultJson);
	}
	
}
