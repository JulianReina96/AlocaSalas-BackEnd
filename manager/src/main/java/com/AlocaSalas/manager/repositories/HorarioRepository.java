package com.AlocaSalas.manager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AlocaSalas.manager.enumerator.DiaDaSemana;
import com.AlocaSalas.manager.enumerator.TurnoHorario;
import com.AlocaSalas.manager.models.Disciplina;
import com.AlocaSalas.manager.models.Horario;



public interface HorarioRepository extends JpaRepository<Horario, Long> {
	
	List<Horario> findByDiaDaSemana(DiaDaSemana diaDaSemana);
	
	List<Horario> findByTurnoHorario(TurnoHorario turnoHorario);

}
