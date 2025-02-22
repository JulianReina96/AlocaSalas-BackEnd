package com.AlocaSalas.manager.models.dto;


import com.AlocaSalas.manager.enumerator.DiaDaSemana;
import com.AlocaSalas.manager.enumerator.TurnoHorario;
import com.AlocaSalas.manager.models.Horario;

public record HorarioDTO(Long id, TurnoHorario turnoHorario, DiaDaSemana diaDaSemana, String horaInicio, String horaFim) {

	
	  public HorarioDTO(Horario horario) {
	        this(horario.getId(), horario.getTurnoHorario(), horario.getDiaDaSemana(), horario.getHoraInicio(), horario.getHoraFim());
	    }
}
