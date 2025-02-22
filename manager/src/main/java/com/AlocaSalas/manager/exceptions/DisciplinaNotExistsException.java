package com.AlocaSalas.manager.exceptions;

public class DisciplinaNotExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5832429616435867672L;

	public DisciplinaNotExistsException(String disciplina) {
		super("A disciplina " + disciplina + " não está cadastrada no nosso sistema!");
	}

}
