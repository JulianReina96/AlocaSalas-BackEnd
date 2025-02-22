package com.AlocaSalas.manager.models;

import java.util.Set;

import com.AlocaSalas.manager.models.dto.DisciplinaCreateDTO;
import com.AlocaSalas.manager.models.dto.DisciplinaDTO;
import com.AlocaSalas.manager.models.dto.SalaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "Disciplina")
@Entity
public class Disciplina {
	

		@EqualsAndHashCode.Include
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Id
	    @Column(name = "DISCIPLINA_ID")
	    private Long id;
	    
	    @Column(name = "NOME")
	    private String nome;
	    
	    @Column(name="CODIGO_TURMA", unique = true)
	    private String codigoTurma;
	
	    @JsonIgnore
	   	@ManyToOne
	    @JoinColumn(name = "PROFESSOR_ID")
	    private Professor professor;
	   	
	   	@JsonIgnore
	    @OneToMany(mappedBy = "disciplina")
	   	private Set<Aula> aulas;
	   	
		public Disciplina(DisciplinaDTO disciplinaDTO) {
			this.id=disciplinaDTO.id();
			this.nome = disciplinaDTO.nome();
			this.codigoTurma = disciplinaDTO.codigoTurma();
			this.professor = disciplinaDTO.professor();
			}	
		
		public Disciplina(String nome, String codigoTurma, Professor professor) {
			this.nome = nome;
			this.codigoTurma = codigoTurma;
			this.professor = professor;
		}
		
		public Disciplina() {}
		


public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getCodigoTurma() {
	return codigoTurma;
}

public void setCodigoTurma(String codigoTurma) {
	this.codigoTurma = codigoTurma;
}

public Professor getProfessor() {
	return professor;
}

public void setProfessor(Professor professor) {
	this.professor = professor;
}

public Set<Aula> getAulas() {
    return aulas;
    }


public void setAulas(Set<Aula> aulas) {
	this.aulas = aulas;
}

    

}