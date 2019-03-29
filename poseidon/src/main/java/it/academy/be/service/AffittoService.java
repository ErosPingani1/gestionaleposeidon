package it.academy.be.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.academy.be.dao.AffittoDao;
import it.academy.be.dto.Affitto;
import it.academy.be.exception.DBException;

@Service
public class AffittoService {
	@Autowired
	AffittoDao dao;

	public ArrayList<Affitto> affittiById(int idutente) throws DBException {
		return dao.selectAffittiById(idutente);
	}

	public ArrayList<Affitto> getAffittiPendenti() throws DBException {
		return dao.getAllAffittiinCorso();
	}
	
}
