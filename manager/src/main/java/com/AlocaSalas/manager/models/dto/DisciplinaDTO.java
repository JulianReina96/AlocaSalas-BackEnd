
package com.AlocaSalas.manager.models.dto;

import com.AlocaSalas.manager.models.Disciplina;
import com.AlocaSalas.manager.models.Professor;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record DisciplinaDTO(Long id, @NotBlank(message = "{disciplina.nome}") String nome, @NotBlank(message = "{disciplina.codigoTurma}") String codigoTurma, Professor professor) {

    public DisciplinaDTO(Disciplina disciplina) {
        this(disciplina.getId(), disciplina.getNome(), disciplina.getCodigoTurma(), disciplina.getProfessor());
    }
}
