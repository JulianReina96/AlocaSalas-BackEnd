
package com.AlocaSalas.manager.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AlocaSalas.manager.exceptions.DisciplinaNotExistsException;
import com.AlocaSalas.manager.exceptions.DisciplinaWithAulaException;
import com.AlocaSalas.manager.exceptions.SalaNotExistsException;
import com.AlocaSalas.manager.models.Disciplina;
import com.AlocaSalas.manager.models.Professor;
import com.AlocaSalas.manager.models.Sala;
import com.AlocaSalas.manager.models.dto.DisciplinaCreateDTO;
import com.AlocaSalas.manager.models.dto.DisciplinaDTO;
import com.AlocaSalas.manager.repositories.DisciplinaRepository;
import com.AlocaSalas.manager.repositories.ProfessorRepository;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository disciplinaRepository;
    
    @Autowired
    ProfessorRepository professorRepository;

    public DisciplinaDTO adicionarDisciplina(DisciplinaCreateDTO disciplinaDto) {
        if(disciplinaDto == null)
            return null;
        Professor professor = professorRepository.findById(disciplinaDto.professorId())
				.orElseThrow(() -> new IllegalArgumentException("Professor not found"));
        Disciplina disciplina = new Disciplina(disciplinaDto.nome(), disciplinaDto.codigo(), professor);

        Disciplina disciplinaSalva = disciplinaRepository.save(disciplina);

        return new DisciplinaDTO(disciplinaSalva);
    }

    public DisciplinaDTO editarDisciplina(DisciplinaDTO disciplinaDto, String idDisciplinaString) {

        Long idDisciplina = Long.valueOf(idDisciplinaString);
        Disciplina disciplinaBanco = disciplinaRepository.findById(idDisciplina).orElseThrow(() -> new DisciplinaNotExistsException(idDisciplina.toString()));

        disciplinaBanco.setNome(disciplinaDto.nome());
        disciplinaBanco.setCodigoTurma(disciplinaDto.codigoTurma());

        Disciplina disciplinaSalva = disciplinaRepository.save(disciplinaBanco);

        return new DisciplinaDTO(disciplinaSalva);
    }

    public void deletarDisciplina(Long id) {
        Disciplina disciplina = disciplinaRepository.findById(id).orElseThrow(() -> new DisciplinaNotExistsException(id.toString()));
        if (disciplina.getAulas().size() > 0) {
            throw new DisciplinaWithAulaException("Não é possível deletar uma disciplina que possui aulas cadastradas");
        }
        disciplinaRepository.delete(disciplina);
    }

    public DisciplinaDTO buscar(String nome) {
        Disciplina disciplina;
        disciplina = disciplinaRepository.findByNome(nome).orElse(null);

        if (disciplina == null)
            throw new DisciplinaNotExistsException(nome);

        return new DisciplinaDTO(disciplina);
    }
    
	public List<DisciplinaDTO> listarDisciplinas() {
		List<Disciplina> disciplinas = disciplinaRepository.findAll();
		List<DisciplinaDTO> disciplinasDto = new ArrayList<>();
		for (Disciplina disciplina : disciplinas) {
			disciplinasDto.add(new DisciplinaDTO(disciplina));
		}
		return disciplinasDto;
	}
}
