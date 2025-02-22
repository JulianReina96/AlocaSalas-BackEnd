package com.AlocaSalas.manager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AlocaSalas.manager.enumerator.DiaDaSemana;
import com.AlocaSalas.manager.models.Aula;
import com.AlocaSalas.manager.models.Disciplina;
import com.AlocaSalas.manager.models.Horario;



public interface AulaRepository extends JpaRepository<Aula, Long> {
	
	Optional<Aula> findByDisciplina(Disciplina disciplina);


	Optional<Aula> findByHorario(Horario horario);

}
