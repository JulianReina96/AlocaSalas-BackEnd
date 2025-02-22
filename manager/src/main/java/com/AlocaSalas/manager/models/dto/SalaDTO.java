package com.AlocaSalas.manager.models.dto;

import com.AlocaSalas.manager.models.Sala;

import jakarta.validation.constraints.NotBlank;

public record SalaDTO(Long id, @NotBlank(message = "{sala.nome}") String nome) {

	
	public SalaDTO(Sala sala ) {
		this(sala.getId(), sala.getNome());
	}


}
