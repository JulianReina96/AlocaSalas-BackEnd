package com.AlocaSalas.manager.models.dto;

import java.util.ArrayList;
import java.util.List;

import com.AlocaSalas.manager.models.Usuario;



public record UsuarioDtoSaida(Long idUsuario, String nomeUsuario, String email) {
	
	
	public  UsuarioDtoSaida(Usuario usuario) {
		this(usuario.getId(),
				usuario.getNome(),
				usuario.getEmail()
				);
	}
}
