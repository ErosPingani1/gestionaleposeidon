package it.academy.be.service;

import java.util.List;

import it.academy.be.dao.UtentiDao;
import it.academy.be.dto.Utente;
import it.academy.be.exception.DBException;

public class UtentiService {

	private UtentiDao dao = new UtentiDao();
	
	public List<Utente> findUtenti(int pagina, int itemPerPagina) throws DBException {
		
		List<Utente> list = dao.findAll();
		
		int totElementi = list.size();
		int fromIndex = -1;
		int toIndex = -1;
		
		fromIndex = Math.min(totElementi, (pagina - 1) * itemPerPagina);
		System.out.println(fromIndex);
		toIndex = Math.min(totElementi, fromIndex + itemPerPagina);
		System.out.println(toIndex);
		
		return list.subList(fromIndex, toIndex);
	}
	
	public List<Utente> findUtenti() throws DBException {
		
		return dao.findAll();
	}

	public Utente findByName(String query) throws DBException {

		Utente result = null;
		
		List<Utente> cacheUtenti = dao.findAll();
		for(Utente utente : cacheUtenti) {
			
			if (utente.getNome().equalsIgnoreCase(query)) {
				result = utente;
				break;
			}
		}
		
		return result;
	}
}
