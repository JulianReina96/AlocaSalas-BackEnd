package com.AlocaSalas.manager.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "Aula")
@Entity
public class Aula {
	
		@EqualsAndHashCode.Include
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Id
	    @Column(name = "AULA_ID")
	    private Long Id;
	
		@OneToOne
	    @JoinColumn(name = "DISCIPLINA_ID")
	    private Disciplina disciplina;
		
		@ManyToOne
		@JoinColumn(name = "SALA_ID")
		private Sala sala;
		
		@OneToOne
		@JoinColumn(name = "HORARIO_ID")
		private Horario horario;

		public Aula(Disciplina disciplina, Sala sala, Horario horario) {
			super();
			this.disciplina = disciplina;
			this.sala = sala;
			this.horario = horario;
			// TODO Auto-generated constructor stub
		}

		public Long getId() {
			return Id;
		}

		public void setId(Long id) {
			Id = id;
		}

		public Disciplina getDisciplina() {
			return disciplina;
		}

		public void setDisciplina(Disciplina disciplina) {
			this.disciplina = disciplina;
		}

		public Sala getSala() {
			return sala;
		}

		public void setSala(Sala sala) {
			this.sala = sala;
		}

		public Horario getHorario() {
			return horario;
		}

		public void setHorario(Horario horario) {
			this.horario = horario;
		}
		
	


	    
	
	

}
