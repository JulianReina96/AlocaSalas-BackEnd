package com.AlocaSalas.manager.exceptions;

public class SalaNotExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5832429616435867672L;

	public SalaNotExistsException(String sala) {
		super("A sala "+ sala +" não está cadastrada no nosso sistema!");
	}

}
