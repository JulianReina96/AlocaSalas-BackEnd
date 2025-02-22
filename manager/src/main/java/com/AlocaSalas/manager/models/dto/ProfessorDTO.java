package com.AlocaSalas.manager.models.dto;


import java.util.Set;

import com.AlocaSalas.manager.models.Disciplina;
import com.AlocaSalas.manager.models.Professor;


import jakarta.validation.constraints.NotBlank;

public record ProfessorDTO(Long id, @NotBlank(message = "{professor.nome}") String nome, Set<Disciplina> disciplinas) {
	
	
	public ProfessorDTO(Professor professor ) {
		this(professor.getId(), professor.getNome(), professor.getDisciplinas());
	}
}
