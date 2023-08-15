package br.com.estudo.demo.services.exceptions;

public class DataIntegratyViolationException extends RuntimeException {

	public DataIntegratyViolationException(String message) {
		super(message);
	}
}
