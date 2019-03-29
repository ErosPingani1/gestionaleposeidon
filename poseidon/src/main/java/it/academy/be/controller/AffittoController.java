package it.academy.be.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.academy.be.dto.Affitto;
import it.academy.be.exception.DBException;
import it.academy.be.service.AffittoService;

@RestController
@RequestMapping("affitto")
public class AffittoController {
	@Autowired
	AffittoService as;
	
	@RequestMapping(path= "/idcliente", produces="application/json")
	public ArrayList<Affitto> ricercaId(@RequestParam("idutente") int idutente) {		
		try {
			return as.affittiById(idutente);
		} catch (DBException e) {
			return null;
		}
	}
	
	@RequestMapping(path= "/pendenti", produces="application/json")
	public ArrayList<Affitto> affittiPendenti() {		
		try {
			return as.getAffittiPendenti();
		} catch (DBException e) {
			return null;
		}
	}
	
}
