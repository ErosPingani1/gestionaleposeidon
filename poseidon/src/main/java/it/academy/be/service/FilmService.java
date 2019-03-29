package it.academy.be.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.academy.be.dao.FilmDao;
import it.academy.be.dto.Film;
import it.academy.be.exception.DBException;

@Service
public class FilmService {

	@Autowired
	private FilmDao dao;

	public List<Film> findFilm() throws DBException {
		return dao.findAll();
	}
	
	public Film findFilmRichiesto(String titolo){
		
		return dao.findFilm(titolo);
	}
	
	public List<Film> findFilmAffittabili(){
		
		return dao.findFilmDisponibili();
		
	}

}
