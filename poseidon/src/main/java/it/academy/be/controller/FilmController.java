package it.academy.be.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.academy.be.dto.Film;
import it.academy.be.exception.DBException;
import it.academy.be.service.FilmService;

@RestController
@RequestMapping("/gestioneFilm")
public class FilmController {

	@Autowired
	private FilmService filmservice;

	@RequestMapping(path = "/ottieniAllFilms", produces = "application/json")
	public List<Film> ritornaAllFilms() throws DBException {

		return filmservice.findFilm();
	}

	
	
	@RequestMapping(path="cercaFilm", produces = "application/json")
	public Film trovaFilm(@RequestParam("titolo") String filmRichiesto) {
		
		
		return filmservice.findFilmRichiesto(filmRichiesto);
	}
	
	
	@RequestMapping(path="filmDisponibili", produces ="application/json")
	public List<Film> trovaFilmDisponibili(){
		
		return filmservice.findFilmAffittabili();
	}
	
	

}
