package br.com.payload.filter.demo.exceptions;

public class UnexptedFieldTypeException extends FilterException {

	private static final long serialVersionUID = 1L;

	public UnexptedFieldTypeException(String message) {
		super(message);
	}

}
