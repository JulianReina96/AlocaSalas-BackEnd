
package com.AlocaSalas.manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AlocaSalas.manager.models.Disciplina;
import com.AlocaSalas.manager.models.Professor;
import com.AlocaSalas.manager.models.dto.DisciplinaCreateDTO;
import com.AlocaSalas.manager.models.dto.DisciplinaDTO;
import com.AlocaSalas.manager.services.DisciplinaService;
import com.AlocaSalas.manager.services.ProfessorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/disciplina")
public class DisciplinaController {
    @Autowired
    DisciplinaService disciplinaService;
    
    @Autowired
    ProfessorService professorService;

    private static final String errosBuscarUmaDisciplinaBadRequest = """
        A disciplina {0} não está cadastrada no nosso sistema! <br>
        """;

    @Operation(summary = "Busca uma disciplina pelo nome", responses = {
        @ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de um DisciplinaDTO", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
        @ApiResponse(responseCode = "400", description = errosBuscarUmaDisciplinaBadRequest, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping(value = "/{nome}")
    public ResponseEntity<DisciplinaDTO> buscarUmaDisciplina(@PathVariable String nome) {
        var disciplinaDTO = disciplinaService.buscar(nome);
        if (disciplinaDTO == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(disciplinaDTO);
    }

    @Operation(summary = "Adiciona uma disciplina na aplicação", responses = {
        @ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de um DisciplinaDTO com a disciplina cadastrada", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
        @ApiResponse(responseCode = "400", description = "", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PostMapping
    public ResponseEntity<DisciplinaDTO> adicionarUmaDisciplina(@RequestBody DisciplinaCreateDTO disciplinaDto) {    	    
        DisciplinaDTO disciplinaDtoSalvo = disciplinaService.adicionarDisciplina(disciplinaDto);

        if (disciplinaDto == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(disciplinaDtoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Edita uma disciplina na aplicação", responses = {
        @ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de um DisciplinaDTO com a disciplina modificada", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
        @ApiResponse(responseCode = "400", description = errosBuscarUmaDisciplinaBadRequest, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PutMapping(value = "/{idDisciplina}")
    public ResponseEntity<DisciplinaDTO> editarUmaDisciplina(@RequestBody DisciplinaDTO disciplinaDto, @PathVariable String idDisciplina) {
        var disciplinaDtoEditado = disciplinaService.editarDisciplina(disciplinaDto, idDisciplina);

        return ResponseEntity.ok(disciplinaDtoEditado); /*vai ser usado para editar professor tambem*/
    }
    
    @Operation(summary = "Deleta uma disciplina na aplicação", responses = {
        @ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de um DisciplinaDTO com a disciplina deletada", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
        @ApiResponse(responseCode = "400", description = errosBuscarUmaDisciplinaBadRequest, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @DeleteMapping(value = "/{idDisciplina}") // ficar atento aos catchs se existirem salas com essa disciplina
	public ResponseEntity<DisciplinaDTO> deletarUmaDisciplina(@PathVariable String idDisciplina) {
		disciplinaService.deletarDisciplina(Long.valueOf(idDisciplina));

		return ResponseEntity.ok().build();
	}
    
	@Operation(summary = "Lista todas as disciplinas cadastradas", responses = {
			@ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de uma lista de DisciplinaDTO com as disciplinas cadastradas", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "400", description = "", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@GetMapping
	public List<DisciplinaDTO> listarTodasDisciplinas() {
		return disciplinaService.listarDisciplinas();
		
	}
}
