package com.AlocaSalas.manager.models;

import com.AlocaSalas.manager.enumerator.DiaDaSemana;
import com.AlocaSalas.manager.enumerator.TurnoHorario;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "Horario")
@Entity
public class Horario {
	
	
	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	public DiaDaSemana getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(DiaDaSemana diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	public TurnoHorario getTurnoHorario() {
		return turnoHorario;
	}

	public void setTurnoHorario(TurnoHorario turnoHorario) {
		this.turnoHorario = turnoHorario;
	}

	@EqualsAndHashCode.Include
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Id
	    @Column(name = "HORARIO_ID")
	    private Long id;
	 
	 @Column(name = "HORA_INICIO")
	 private String horaInicio;
	 
	 @Column(name = "HORA_FIM")
	 private String horaFim;
	 
	 @Column(name = "DIA_SEMANA")
		@Enumerated(EnumType.STRING)
		private DiaDaSemana diaDaSemana;
	 
	 @Column(name = "TURNO_HORARIO")
		@Enumerated(EnumType.STRING)
		private TurnoHorario turnoHorario;
	
	

}
