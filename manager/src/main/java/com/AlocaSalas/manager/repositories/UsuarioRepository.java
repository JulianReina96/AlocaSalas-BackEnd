package com.AlocaSalas.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AlocaSalas.manager.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Usuario findByEmail(String email);

}
