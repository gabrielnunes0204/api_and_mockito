package br.com.estudo.demo.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException(String message) {
		super(message);
	}
}
