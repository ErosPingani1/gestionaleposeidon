package it.academy.be.service;

import java.util.List;

import it.academy.be.dao.FilmDao;
import it.academy.be.dto.Film;
import it.academy.be.exception.DBException;

public class FilmService {

	private FilmDao dao = new FilmDao();
	
	public List<Film> findFilm(int pagina, int itemPerPagina) throws DBException {
		
		List<Film> list = dao.findAll();
		
		int totElementi = list.size();
		int fromIndex = -1;
		int toIndex = -1;
		
		fromIndex = Math.min(totElementi, (pagina - 1) * itemPerPagina);
		System.out.println(fromIndex);
		toIndex = Math.min(totElementi, fromIndex + itemPerPagina);
		System.out.println(toIndex);
		
		return list.subList(fromIndex, toIndex);
	}
	
	public List<Film> findFilm() throws DBException {
		return dao.findAll();
	}
	
}
