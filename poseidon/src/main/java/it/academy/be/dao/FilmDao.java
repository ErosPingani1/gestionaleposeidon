package it.academy.be.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import it.academy.be.dto.Cliente;
import it.academy.be.dto.Film;
import it.academy.be.exception.DBException;

@Repository
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
		sql.append("SELECT * FROM FILM ");

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

	public Film findFilm(String titolo) {

		Film film = new Film();

		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM FILM WHERE Titolo = ? ");

		System.out.println("Sono nel findFilm");

		try {
			connection = getConnection();
			pstm = connection.prepareStatement(sql.toString());
			pstm.setString(1, titolo);
			rs = pstm.executeQuery();

			if (rs.next()) {
				film.setId(rs.getInt("idFilm"));
				film.setTitolo(rs.getString("Titolo"));
				film.setCopiedisponibili(rs.getInt("CopieDisponibili"));
				film.setRegista(rs.getString("Regista"));
				film.setGenere(rs.getString("Genere"));

			} else {
				film.setTitolo("Attenzione");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		return film;
	}

	public List<Film> findFilmDisponibili() {

		Connection connection = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM FILM WHERE CopieDisponibili > 0 ");

		System.out.println("Sono nel findFilmDisponibili");

		List<Film> listaFilmDisponibili = new ArrayList<>();
		
		try {
			connection = getConnection();
			pstm = connection.prepareStatement(sql.toString());

			

			rs = pstm.executeQuery();

			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("idFilm"));
				film.setTitolo(rs.getString("Titolo"));
				film.setCopiedisponibili(rs.getInt("CopieDisponibili"));
				film.setRegista(rs.getString("Regista"));
				film.setGenere(rs.getString("Genere"));

				listaFilmDisponibili.add(film);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		return listaFilmDisponibili;
	}

}
