package com.AlocaSalas.manager.models;

import java.util.Set;

import com.AlocaSalas.manager.models.dto.SalaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	@Table(name = "Sala")
	@Entity
	public class Sala {
	    @EqualsAndHashCode.Include
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Id
	    @Column(name = "SALA_ID")
	    private Long id;
	    @Column(name = "NOME")
	    private String nome; 
	    @JsonIgnore
	    @OneToMany(mappedBy = "sala")
	    private Set<Aula> aulas;
	    
	    public Sala() {}
	    
		public Sala(SalaDTO salaDto) {
			this.id=salaDto.id();
			this.nome = salaDto.nome();
			}		

			public Sala(String nome) {
				this.nome = nome;
			}
		
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
	
		public Set<Aula> getAulas() {
			return aulas;
		}
		public void setAulas(Set<Aula> aulas) {
			this.aulas = aulas;
		}
	
	

}
