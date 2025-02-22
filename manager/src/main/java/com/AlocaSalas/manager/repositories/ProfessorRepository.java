package com.AlocaSalas.manager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.AlocaSalas.manager.models.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

	
	
	Optional<Professor> findByNome(String nome);
}
