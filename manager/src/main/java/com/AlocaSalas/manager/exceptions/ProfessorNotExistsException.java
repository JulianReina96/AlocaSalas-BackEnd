package com.AlocaSalas.manager.exceptions;

public class ProfessorNotExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProfessorNotExistsException(String professor) {
		super("O professor " + professor + " não está cadastrado no nosso sistema!");
	}
	
	
}
