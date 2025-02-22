package com.AlocaSalas.manager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AlocaSalas.manager.models.Sala;


public interface SalaRepository extends JpaRepository<Sala, Long> {
	
	
	
	Optional<Sala> findByNome(String nome);
	
	

}
