
package com.AlocaSalas.manager.services;

import com.AlocaSalas.manager.models.Horario;
import com.AlocaSalas.manager.enumerator.*;
import com.AlocaSalas.manager.repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

	@Autowired
	private HorarioRepository horarioRepository;

	public List<Horario> listarTodosHorarios() {
		return horarioRepository.findAll();
	}

	public List<Horario> listarHorariosPorTurno(TurnoHorario turno) {
		return horarioRepository.findByTurnoHorario(turno);
	}

	public List<Horario> listarHorariosPorDia(DiaDaSemana dia) {
		return horarioRepository.findByDiaDaSemana(dia);
	}
}
