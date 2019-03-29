package it.academy.be.controller;

import java.util.List;

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
	public Cliente ricercaNome(@RequestParam("nome") String query) throws Exception {
		
		System.out.println("sono passato");
		return findByName(query);
	}
	
	@GetMapping(path= "/ricercaCompleta", produces="application/json")
	public List<Cliente> ricercaCompleta() throws Exception {
		
		return findAll();
	}
	
	@RequestMapping(path= "/ricercaPagina", produces="application/json")
	public List<Cliente> ricercaPagina(@RequestParam("pagina") int pagina, @RequestParam("i") int itemPerPagina) throws Exception {
		
		return findPaged(pagina, itemPerPagina);
	}
	
	//Metodo findByName che viene collegato al medesimo metodo del Service degli utenti
	private Cliente findByName(String query) throws DBException {
		
		return service.findByName(query);
	}
	
	//Metodo findAll() che viene collegato al metodo findUtenti del Service degli utenti
	private List<Cliente> findAll() throws DBException {
		
		return service.findUtenti();
	}
	
	//Metodo findAll() che viene collegato al metodo findUtenti con i parametri pagine del service degli utenti
	private List<Cliente> findPaged(int pagina, int itemPerPagina) throws DBException {
		
		return service.findUtenti(pagina, itemPerPagina);
	}

}
