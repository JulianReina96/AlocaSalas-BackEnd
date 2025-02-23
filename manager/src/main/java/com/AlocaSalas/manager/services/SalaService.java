package com.AlocaSalas.manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AlocaSalas.manager.exceptions.SalaNotExistsException;
import com.AlocaSalas.manager.exceptions.SalaWithAulaException;
import com.AlocaSalas.manager.models.Aula;
import com.AlocaSalas.manager.models.Sala;
import com.AlocaSalas.manager.models.dto.SalaDTO;
import com.AlocaSalas.manager.repositories.AulaRepository;
import com.AlocaSalas.manager.repositories.SalaRepository;


@Service
public class SalaService {

	@Autowired
	SalaRepository salaRepository;
	
	@Autowired
	AulaRepository aulaRepository;
	
	public SalaDTO adicionarSala(String nomeSala) {
        if(nomeSala.isEmpty())
            return null;
        Sala sala = new Sala(nomeSala);
        
        Sala salaSalva = salaRepository.save(sala);
        
        return new SalaDTO(salaSalva);
    }
	
	public SalaDTO editarSala(SalaDTO salaDto, String idSalaString) {
		    
            Long idSala = Long.valueOf(idSalaString);
	            
            Sala salaBanco = salaRepository.findById(idSala).orElseThrow(() -> new SalaNotExistsException(idSala.toString()));
            
            salaBanco.setNome(salaDto.nome());
            
            
            Sala salaSalva = salaRepository.save(salaBanco);
            
            return new SalaDTO(salaSalva);
		
	}
	
	public void deletarSala(Long id) {
		Sala sala = salaRepository.findById(id).orElseThrow(() -> new SalaNotExistsException(id.toString()));		
		Set<Aula> aulas = sala.getAulas();
		if(aulas.size() > 0)            
		for(Aula aula : aulas) {
            aulaRepository.delete(aula);
		}			
		salaRepository.delete(sala);
	}
	
	public SalaDTO buscar(String nome) {
		Sala sala;
		sala = salaRepository.findByNome(nome).orElse(null);

		if (sala == null)
			throw new SalaNotExistsException(nome);

		return new SalaDTO(sala);
	}

	public List<SalaDTO> listarTodasSalas() {
		List<Sala> salas = salaRepository.findAll();
		
		List<SalaDTO> salasDTO = new ArrayList<>();
		for (Sala sala : salas) {
			salasDTO.add(new SalaDTO(sala));
		}
		return salasDTO;
		// TODO Auto-generated method stub
		
	}

	
}
