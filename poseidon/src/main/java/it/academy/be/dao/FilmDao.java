package it.academy.be.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.academy.be.dto.Cliente;
import it.academy.be.dto.Film;
import it.academy.be.exception.DBException;

public class FilmDao {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			System.out.println(e);
		}
		return DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/db_poseidon?" + "user=uposeidon&password=canistrabici&serverTimezone=UTC");
	}

	public List<Film> findAll() throws DBException {

		List<Film> result = new ArrayList<>();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM FILM WHERE ELIMINATO IS FALSE");

		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			System.out.println("Sono nel try di findAll");
			connection = getConnection();
			pstm = connection.prepareStatement(sql.toString());
			rs = pstm.executeQuery();

			while (rs.next()) {

				Film film = new Film();
				film.setId(rs.getInt("IDFILM"));
				film.setTitolo(rs.getString("TITOLO"));
				film.setRegista(rs.getString("REGISTA"));
				film.setGenere(rs.getString("GENERE"));
				film.setCopiedisponibili(rs.getInt("COPIEDISPONIBILI"));
				result.add(film);

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
