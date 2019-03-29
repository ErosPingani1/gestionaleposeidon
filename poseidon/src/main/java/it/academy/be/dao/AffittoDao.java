package it.academy.be.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import it.academy.be.dto.Affitto;
import it.academy.be.dto.Cliente;
import it.academy.be.exception.DBException;

@Repository
public class AffittoDao {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println(e);
		}
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db_poseidon?" + "user=uposeidon&password=canistrabici&serverTimezone=UTC");
	}

	public List<Affitto> getClienteList(Cliente cliente) {

		return null;
	}

	public ArrayList<Affitto> getAllAffittiinCorso() throws DBException {
		ArrayList<Affitto> result = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT IDAFFITTO, IDCLIENTE, IDFILM, DATAAFFITTO, DATARESTITUZIONE");
		sql.append(" FROM AFFITTO");
		sql.append(" WHERE DATARESTITUZIONE IS NULL");

		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			System.out.println("Sono nel try di findAll");
			connection = getConnection();
			pstm = connection.prepareStatement(sql.toString());
			rs = pstm.executeQuery();

			while (rs.next()) {

				Affitto affitto = new Affitto();

				affitto.setIdaffitto(rs.getInt("IDAFFITTO"));
				affitto.setIdcliente(rs.getInt("IDCLIENTE"));
				affitto.setIdfilm(rs.getInt("IDFILM"));
				affitto.setDataaffitto(rs.getDate("DATAAFFITTO"));
				affitto.setDatarestituzione(rs.getDate("DATARESTITUZIONE"));

				result.add(affitto);
			}
		} catch (Exception e) {

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

	public ArrayList<Affitto> selectAffittiById(int idutente) throws DBException {
		ArrayList<Affitto> result = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT IDAFFITTO, IDCLIENTE, IDFILM, DATAAFFITTO, DATARESTITUZIONE");
		sql.append(" FROM AFFITTO");
		sql.append(" WHERE IDCLIENTE = ?");

		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			System.out.println("Sono nel try di findAll");
			connection = getConnection();
			pstm = connection.prepareStatement(sql.toString());
			pstm.setInt(1, idutente);
			rs = pstm.executeQuery();

			while (rs.next()) {

				Affitto affitto = new Affitto();

				affitto.setIdaffitto(rs.getInt("IDAFFITTO"));
				affitto.setIdcliente(rs.getInt("IDCLIENTE"));
				affitto.setIdfilm(rs.getInt("IDFILM"));
				affitto.setDataaffitto(rs.getDate("DATAAFFITTO"));
				affitto.setDatarestituzione(rs.getDate("DATARESTITUZIONE"));

				result.add(affitto);
			}
		} catch (Exception e) {

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
