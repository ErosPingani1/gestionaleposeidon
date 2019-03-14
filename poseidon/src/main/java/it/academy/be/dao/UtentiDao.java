package it.academy.be.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.academy.be.dto.Utente;
import it.academy.be.exception.DBException;

public class UtentiDao {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName ("org.h2.Driver"); 
		return DriverManager.getConnection ("jdbc:h2:~/test", "sa",""); 
	}
	
	public List<Utente> findAll() throws DBException {
		
		//QUI SI SPACCAVA
		List<Utente> result = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID, NOME, COGNOME, DATA_NASCITA");
		sql.append(" FROM UTENTE");
		
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			pstm = connection.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				Utente utente = new Utente();
				
				utente.setId(rs.getInt("ID"));
				utente.setNome(rs.getString("NOME"));
				utente.setCognome(rs.getString("COGNOME"));
				utente.setDate(rs.getDate("DATA_NASCITA"));
				
				result.add(utente);
			}
		} catch(Exception e) {
			
			throw new DBException(e);
			
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// non faccio nulla
				}
			}
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e1) {
					// non faccio nulla
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e1) {
					// non faccio nulla
				}
			}			
		}
		return result;
	}
}
