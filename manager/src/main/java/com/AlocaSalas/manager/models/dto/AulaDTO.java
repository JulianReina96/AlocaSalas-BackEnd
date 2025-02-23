
package com.AlocaSalas.manager.models.dto;

import com.AlocaSalas.manager.models.Aula;
import com.AlocaSalas.manager.models.Disciplina;
import com.AlocaSalas.manager.models.Horario;
import com.AlocaSalas.manager.models.Sala;

public record AulaDTO(Long id, Disciplina disciplina, Sala sala, Horario horario, String professor) {

    public AulaDTO(Aula aula) {
        this(aula.getId(), aula.getDisciplina(), aula.getSala(), aula.getHorario(), 
        		aula.getDisciplina().getProfessor().getNome());
    }


}
