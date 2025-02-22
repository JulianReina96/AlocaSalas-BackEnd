
package com.AlocaSalas.manager.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AlocaSalas.manager.exceptions.ProfessorNotExistsException;
import com.AlocaSalas.manager.exceptions.ProfessorWithDisciplinaException;
import com.AlocaSalas.manager.models.Professor;
import com.AlocaSalas.manager.models.dto.ProfessorDTO;
import com.AlocaSalas.manager.repositories.ProfessorRepository;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;
    
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
        if (professor.getDisciplinas().size() > 0) {
            throw new ProfessorWithDisciplinaException("Não é possível deletar um professor que possui disciplinas cadastradas");
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
