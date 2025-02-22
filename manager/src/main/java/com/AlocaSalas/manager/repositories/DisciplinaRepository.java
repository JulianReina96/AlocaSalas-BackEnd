package com.AlocaSalas.manager.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AlocaSalas.manager.models.Disciplina;



public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	
	Optional<Disciplina> findByCodigoTurma(String codigoTurma);
	List<Disciplina> findAll();
	
	Optional<Disciplina> findByNome(String nome);

}
