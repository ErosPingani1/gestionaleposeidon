package it.academy.be.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.academy.be.dto.Cliente;
import it.academy.be.exception.DBException;

public class ClientiDao {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName ("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println(e);
		}
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/db_poseidon?"+"user=uposeidon&password=canistrabici&serverTimezone=UTC");
	}
	
	public List<Cliente> findAll() throws DBException {
		
		List<Cliente> result = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ID, NOME, COGNOME, INDIRIZZO, CITTA, PROVINCIA, CAP");
		sql.append(" FROM CLIENTI");
		
		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			System.out.println("Sono nel try di findAll");
			connection = getConnection();
			pstm = connection.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			System.out.println("Oplà");
			
			while(rs.next()) {
				
				Cliente cliente = new Cliente();
				
				cliente.setId(rs.getInt("ID"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setCognome(rs.getString("COGNOME"));
				cliente.setIndirizzo(rs.getString("INDIRIZZO"));
				cliente.setCittà(rs.getString("CITTA"));
				cliente.setProvincia(rs.getString("PROVINCIA"));
				cliente.setCAP(rs.getString("CAP"));
				
				result.add(cliente);
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
