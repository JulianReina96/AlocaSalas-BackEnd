
package com.AlocaSalas.manager.services;

import com.AlocaSalas.manager.models.Aula;
import com.AlocaSalas.manager.models.Disciplina;
import com.AlocaSalas.manager.models.Horario;
import com.AlocaSalas.manager.models.Sala;
import com.AlocaSalas.manager.models.dto.AulaCreateDTO;
import com.AlocaSalas.manager.models.dto.AulaDTO;
import com.AlocaSalas.manager.repositories.AulaRepository;
import com.AlocaSalas.manager.repositories.DisciplinaRepository;
import com.AlocaSalas.manager.repositories.HorarioRepository;
import com.AlocaSalas.manager.repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AulaService {

	@Autowired
	private AulaRepository aulaRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	@Autowired
	private SalaRepository salaRepository;

	@Autowired
	private HorarioRepository horarioRepository;

	public AulaDTO adicionarAula(AulaCreateDTO aulaCreateDto) {
		Disciplina disciplina = disciplinaRepository.findById(aulaCreateDto.disciplinaId())
				.orElseThrow(() -> new IllegalArgumentException("Disciplina not found"));
		Sala sala = salaRepository.findById(aulaCreateDto.salaId())
				.orElseThrow(() -> new IllegalArgumentException("Sala not found"));
		Horario horario = horarioRepository.findById(aulaCreateDto.horarioId())
				.orElseThrow(() -> new IllegalArgumentException("Horario not found"));

		Aula aula = new Aula(disciplina, sala, horario);
		Aula savedAula = aulaRepository.save(aula);

		return new AulaDTO(savedAula);
	}

	public AulaDTO buscarAula(Long id) {
		Aula aula = aulaRepository.findById(id).orElse(null);
		if (aula == null) {
			return null;
		}
		return new AulaDTO(aula);
	}
	
	
}
