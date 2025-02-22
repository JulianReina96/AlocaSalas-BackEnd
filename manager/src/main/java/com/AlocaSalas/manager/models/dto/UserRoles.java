package com.AlocaSalas.manager.models.dto;

import java.util.List;

import com.AlocaSalas.manager.models.Role;
import com.AlocaSalas.manager.models.Usuario;

public record UserRoles(String nome,List<Role> roles) {

	
	public UserRoles(Usuario usuario) {
		this(usuario.getNome(), usuario.getRoles());
	}
}
