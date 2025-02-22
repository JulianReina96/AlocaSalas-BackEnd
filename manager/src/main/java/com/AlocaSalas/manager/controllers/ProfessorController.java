
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

import com.AlocaSalas.manager.services.ProfessorService;

import com.AlocaSalas.manager.models.dto.ProfessorDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/professor")
public class ProfessorController {

	
	@Autowired
	ProfessorService professorService;
	
	private static final String errosBuscarUmProfessorBadRequest = """
			O professor {0} não está cadastrado no nosso sistema! <br>
			""";

	@Operation(summary = "Busca um professor pelo nome", responses = {
			@ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de um ProfessorDTO", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "400", description = errosBuscarUmProfessorBadRequest, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@GetMapping(value = "/{nome}")
	public ResponseEntity<ProfessorDTO> buscarUmProfessor(@PathVariable String nome) {
		var professorDTO = professorService.buscar(nome);
		if (professorDTO == null)
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok(professorDTO);
	}

	@Operation(summary = "Adiciona um professor na aplicação", responses = {
			@ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de um ProfessorDTO com o professor cadastrado", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "400", description = "", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@PostMapping
	public ResponseEntity<ProfessorDTO> adicionarUmProfessor(@RequestBody String nomeProfessor) {
		ProfessorDTO professorDtoSalvo = professorService.adicionarProfessor(nomeProfessor);
		if (professorDtoSalvo == null)
			return ResponseEntity.badRequest().build();
		return new ResponseEntity<>(professorDtoSalvo, HttpStatus.CREATED);
	}

	@Operation(summary = "Edita um professor na aplicação", responses = {
			@ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de um ProfessorDTO com o professor modificado", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "400", description = errosBuscarUmProfessorBadRequest, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@PutMapping(value = "/{idProfessor}")
	public ResponseEntity<ProfessorDTO> editarUmProfessor(@RequestBody ProfessorDTO professorDto,
			@PathVariable String idProfessor) {
		var professorDtoEditado = professorService.editarProfessor(professorDto, idProfessor);
		return ResponseEntity.ok(professorDtoEditado);
	}

	@Operation(summary = "Deleta um professor na aplicação", responses = {
			@ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de um ProfessorDTO com o professor deletado", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "400", description = errosBuscarUmProfessorBadRequest, content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@DeleteMapping(value = "/{idProfessor}")
	public ResponseEntity<ProfessorDTO> deletarUmProfessor(@PathVariable String idProfessor) {
		professorService.deletarProfessor(Long.valueOf(idProfessor));
		return ResponseEntity.ok().build();
	}
	
	@Operation(summary = "Lista todos os professores cadastrados", responses = {
			@ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de uma lista de ProfessorDTO com os professores cadastrados", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "400", description = "", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@GetMapping
	public List<ProfessorDTO> listarTodosProfessores() {
		
		List<ProfessorDTO> professores = professorService.listarProfessores();
		if (professores == null)
			return null;

		return professores;
	}
}
