package com.moraes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/piorfilme")
public class PiorFilme {

	@GetMapping	
	public ResponseEntity<String> obterPior() {
		
		return ResponseEntity.ok("Olá Mundo!");
	}
	
}
