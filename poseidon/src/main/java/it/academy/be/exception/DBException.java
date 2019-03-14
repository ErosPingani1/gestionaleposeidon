package it.academy.be.exception;

public class DBException extends Exception {

	public DBException(Exception e) {
		super(e);
	}
}
