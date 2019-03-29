package it.academy.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.academy.be.dto.Cliente;
import it.academy.be.exception.DBException;
import it.academy.be.service.UtentiService;

@RestController
@RequestMapping("/gestioneUtenti")
public class UtentiController {
	
	@Autowired
	private UtentiService service;
	
	@RequestMapping(path= "/ricercaNome", produces="application/json")
	public Cliente ricercaNome(@RequestParam("q") String query) throws Exception {
		
		return findByName(query);
	}
	
	//Metodo findByName che viene collegato al medesimo metodo del Service
	private Cliente findByName(String query) throws DBException {
		
		return service.findByName(query);
	}

}
