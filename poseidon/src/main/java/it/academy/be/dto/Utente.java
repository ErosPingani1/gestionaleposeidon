package it.academy.be.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utente {

	private int id;
	private String nome;
	private String cognome;
	private Date date;

	public Utente() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return this.nome + "," + this.cognome + "," + (this.date == null ? "" : sdf.format(this.date));
	}

}
