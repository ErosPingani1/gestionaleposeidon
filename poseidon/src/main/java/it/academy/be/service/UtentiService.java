package it.academy.be.service;

import java.util.List;

import org.springframework.stereotype.Service;

import it.academy.be.dao.ClientiDao;
import it.academy.be.dto.Cliente;
import it.academy.be.exception.DBException;

@Service
public class UtentiService {

	private ClientiDao dao = new ClientiDao();
	
	public List<Cliente> findUtenti(int pagina, int itemPerPagina) throws DBException {
		
		List<Cliente> list = dao.findAll();
		
		int totElementi = list.size();
		int fromIndex = -1;
		int toIndex = -1;
		
		fromIndex = Math.min(totElementi, (pagina - 1) * itemPerPagina);
		System.out.println(fromIndex);
		toIndex = Math.min(totElementi, fromIndex + itemPerPagina);
		System.out.println(toIndex);
		
		return list.subList(fromIndex, toIndex);
	}
	
	public List<Cliente> findUtenti() throws DBException {
		
		return dao.findAll();
	}

	public Cliente findByName(String query) throws DBException {

		Cliente result = null;
		
		List<Cliente> cacheUtenti = dao.findAll();
		for(Cliente utente : cacheUtenti) {
			
			System.out.println(query);
			
			if (utente.getNome().equalsIgnoreCase(query)) {
				result = utente;
				System.out.println(result);
				break;
			}else {
				System.out.println("Uè l'utente non va bene");
			}
		}
		
		return result;
	}
}
