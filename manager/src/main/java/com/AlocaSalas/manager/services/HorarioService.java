
package com.AlocaSalas.manager.services;

import com.AlocaSalas.manager.models.Aula;
import com.AlocaSalas.manager.models.Horario;
import com.AlocaSalas.manager.enumerator.*;
import com.AlocaSalas.manager.repositories.AulaRepository;
import com.AlocaSalas.manager.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

	@Autowired
	private HorarioRepository horarioRepository;
	
	@Autowired
	private AulaRepository aulaRepository;

	public List<Horario> listarTodosHorarios() {
		return horarioRepository.findAll();
	}

	public List<Horario> listarHorariosPorTurno(TurnoHorario turno) {
		return horarioRepository.findByTurnoHorario(turno);
	}

	public List<Horario> listarHorariosPorDia(DiaDaSemana dia) {
		return horarioRepository.findByDiaDaSemana(dia);
	}

	public List<Horario> buscarHorariosDisponiveis(Long salaId, Long disciplinaId) {
		
		List<Horario> horarios = horarioRepository.findAll();
		List<Aula> aulas = aulaRepository.findBySalaId(salaId);
		aulas.addAll(aulaRepository.findByDisciplinaId(disciplinaId));
		
		for (Aula aula : aulas) {
			horarios.remove(aula.getHorario());
		}
		
		return horarios;
		
		
		
		
	}
}
