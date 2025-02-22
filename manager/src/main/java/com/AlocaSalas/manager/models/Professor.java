package com.AlocaSalas.manager.models;

import java.util.Set;

import com.AlocaSalas.manager.models.dto.ProfessorDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "Professor")
@Entity
public class Professor {
	
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PROFESSOR_ID")
    private Long id;
    @Column(name = "NOME")
    private String nome;
    @OneToMany(mappedBy = "professor")
    private Set<Disciplina> disciplinas;
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
	public Set<Disciplina> getDisciplinas() {
		return disciplinas;
	}
	public void setDisciplinas(Set<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}
    
    public Professor(ProfessorDTO professorDto) {
    	        this.id=professorDto.id();
    	        this.nome = professorDto.nome();
    	        this.disciplinas = professorDto.disciplinas();
    	        }
    
    public Professor() {}

				public Professor(String nome) {
					this.nome = nome;
				}
    }


