package com.AlocaSalas.manager.models.dto;

import java.util.List;

import com.AlocaSalas.manager.models.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(	Long id,
		@NotBlank(message="{usuario.nome}") String nome, 
		@NotBlank(message="{usuario.email}")
		@Email String email,
		@NotNull String senha,
		List<RoleDto> roles) {

	
	public UsuarioDto(Usuario usuario) {
		this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), 
				usuario.getRoles().stream().map(RoleDto::new).toList());
	}
}
