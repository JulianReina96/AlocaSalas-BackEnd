
package com.AlocaSalas.manager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AlocaSalas.manager.exceptions.ProfessorNotExistsException;
import com.AlocaSalas.manager.exceptions.ProfessorWithDisciplinaException;
import com.AlocaSalas.manager.models.Aula;
import com.AlocaSalas.manager.models.Disciplina;
import com.AlocaSalas.manager.models.Professor;
import com.AlocaSalas.manager.models.dto.ProfessorDTO;
import com.AlocaSalas.manager.repositories.AulaRepository;
import com.AlocaSalas.manager.repositories.DisciplinaRepository;
import com.AlocaSalas.manager.repositories.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;
    
    @Autowired
    AulaRepository aulaRepository;
    
    @Autowired
    DisciplinaRepository disciplinaRepository;
    
	public List<ProfessorDTO> listarProfessores() {
        List<Professor> professores = professorRepository.findAll();
        List<ProfessorDTO> professoresDTO = new ArrayList<>();
        for (Professor professor : professores) {
            professoresDTO.add(new ProfessorDTO(professor));
        }
        return professoresDTO;
        }

    public ProfessorDTO adicionarProfessor(String nomeProfessor) {
        if(nomeProfessor == null)
            return null;
        Professor professor = new Professor(nomeProfessor);

        Professor professorSalvo = professorRepository.save(professor);

        return new ProfessorDTO(professorSalvo);
    }

    public ProfessorDTO editarProfessor(ProfessorDTO professorDto, String idProfessorString) {

        Long idProfessor = Long.valueOf(idProfessorString);

        Professor professorBanco = professorRepository.findById(idProfessor).orElseThrow(() -> new ProfessorNotExistsException(idProfessor.toString()));

        professorBanco.setNome(professorDto.nome());
        

        Professor professorSalvo = professorRepository.save(professorBanco);

        return new ProfessorDTO(professorSalvo);
    }

    public void deletarProfessor(Long id) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new ProfessorNotExistsException(id.toString()));
        
        
        Set<Disciplina> disciplinas = professor.getDisciplinas();                                             
        
        if(disciplinas.size() > 0) {
			for (Disciplina disciplina : disciplinas) {				
				if (disciplina.getAulas().size() > 0) {				
					for(Aula aula : disciplina.getAulas()) {
                        aulaRepository.delete(aula);
				}			
				}	
				disciplina.setProfessor(null);
				disciplinaRepository.save(disciplina);
			}
        }       
        professorRepository.delete(professor);
        }
    

    public ProfessorDTO buscar(String nome) {
        Professor professor;
        professor = professorRepository.findByNome(nome).orElse(null);

        if (professor == null)
            throw new ProfessorNotExistsException(nome);

        return new ProfessorDTO(professor);
    }
}
