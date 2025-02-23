
package com.AlocaSalas.manager.controllers;

import com.AlocaSalas.manager.models.dto.AulaCreateDTO;
import com.AlocaSalas.manager.models.dto.AulaDTO;
import com.AlocaSalas.manager.services.AulaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aula")
public class AulaController {

	@Autowired
	private AulaService aulaService;
	
	@Operation(summary = "Adiciona uma aula na aplicação", responses = {
            @ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de um AulaDTO com a aula cadastrada", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
            @ApiResponse(responseCode = "400", description = "", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@PostMapping
	public ResponseEntity<AulaDTO> adicionarUmaAula(@RequestBody AulaCreateDTO aulaCreateDto) {
		AulaDTO aulaDtoSalvo = aulaService.adicionarAula(aulaCreateDto);
		if (aulaDtoSalvo == null)
			return ResponseEntity.badRequest().build();
		return new ResponseEntity<>(aulaDtoSalvo, HttpStatus.CREATED);
	}
	@Operation(summary = "Edita uma aula na aplicação", responses = {
            @ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de um AulaDTO com a aula cadastrada", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
            @ApiResponse(responseCode = "400", description = "", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@PutMapping("/{id}")
	public ResponseEntity<AulaDTO> editarAula(@RequestBody AulaCreateDTO aulaCreateDto,@PathVariable String id) {
		AulaDTO aulaEdited = aulaService.editarAula(aulaCreateDto, id);
		if (aulaEdited == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(aulaEdited);
	}
	
	@Operation(summary = "Busca uma aula pelo id", responses = {
			@ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de um AulaDTO", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "400", description = "A aula não está cadastrada no nosso sistema!", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@GetMapping("/{id}")
	public ResponseEntity<AulaDTO> buscarAula(@PathVariable Long id) {
		AulaDTO aulaDto = aulaService.buscarAula(id);
		if (aulaDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(aulaDto);
	}
	
	@Operation(summary = "Buscar todas as aulas", responses = {
            @ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de uma lista de AulaDTO", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }) })
	@GetMapping
	public List<AulaDTO> buscarTodasAulas() {
		
		List<AulaDTO> aulas = aulaService.buscarTodasAulas();
		
		return aulas;
	}
	
	
	
	
}
