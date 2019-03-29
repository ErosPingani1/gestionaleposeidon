package it.academy.be.dto;

import java.util.Date;

public class Affitto {
	private int idaffitto;
	private int idcliente;
	private int idfilm;
	private Date dataaffitto;
	private Date datarestituzione;
	
	public int getIdaffitto() {
		return idaffitto;
	}
	public void setIdaffitto(int i) {
		this.idaffitto = i;
	}
	
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public int getIdfilm() {
		return idfilm;
	}
	public void setIdfilm(int idfilm) {
		this.idfilm = idfilm;
	}
	public Date getDataaffitto() {
		return dataaffitto;
	}
	public void setDataaffitto(Date dataaffitto) {
		this.dataaffitto = dataaffitto;
	}
	public Date getDatarestituzione() {
		return datarestituzione;
	}
	public void setDatarestituzione(Date datarestituzione) {
		this.datarestituzione = datarestituzione;
	}
	@Override
	public String toString() {
		return "Affitto [idaffitto=" + idaffitto + ", idcliente=" + idcliente + ", idfilm=" + idfilm + "]";
	}
	
}
