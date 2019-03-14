package it.academy.be.dto;

public class Film {
	
	private int id;
	private String titolo;
	private String regista;
	private String genere;
	private int copiedisponibili;
	private boolean eliminato;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getRegista() {
		return regista;
	}
	public void setRegista(String regista) {
		this.regista = regista;
	}
	public String getGenere() {
		return genere;
	}
	public void setGenere(String genere) {
		this.genere = genere;
	}
	public int getCopiedisponibili() {
		return copiedisponibili;
	}
	public void setCopiedisponibili(int copiedisponibili) {
		this.copiedisponibili = copiedisponibili;
	}
	public boolean isEliminato() {
		return eliminato;
	}
	public void setEliminato(boolean eliminato) {
		this.eliminato = eliminato;
	}
	
	@Override
	public String toString() {
		return "Film [id=" + id + ", titolo=" + titolo + ", regista=" + regista + ", genere=" + genere
				+ ", copiedisponibili=" + copiedisponibili + ", eliminato=" + eliminato + "]";
	}

}
