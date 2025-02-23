
package com.AlocaSalas.manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.AlocaSalas.manager.exceptions.SalaWithAulaException;
import com.AlocaSalas.manager.models.dto.SalaDTO;
import com.AlocaSalas.manager.services.SalaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/sala")
public class SalaController {
    @Autowired
    SalaService salaService;

    private static final String errosBuscarUmaSalaBadRequest = """
        A sala {0} não está cadastrada no nosso sistema! <br>
        """;
    
    
    @RestControllerAdvice
    public class SalaControllerAdvice {

        @ExceptionHandler(SalaWithAulaException.class)
        public ResponseEntity<String> handleSalaWithAulaException(SalaWithAulaException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @Operation(summary = "Busca uma sala pelo nome", responses = {
        @ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de um SalaDTO", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
        @ApiResponse(responseCode = "400", description = errosBuscarUmaSalaBadRequest, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping(value = "/{nome}")
    public ResponseEntity<SalaDTO> buscarUmaSala(@PathVariable String nome) {
        var salaDTO = salaService.buscar(nome);
        if (salaDTO == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(salaDTO);
    }

    @Operation(summary = "Adiciona uma sala na aplicação", responses = {
        @ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de um SalaDTO com a sala cadastrada", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
        @ApiResponse(responseCode = "400", description = "", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PostMapping
    public ResponseEntity<SalaDTO> adicionarUmaSala(@RequestBody String nomeSala)  {
    	
        SalaDTO salaDtoSalvo = salaService.adicionarSala(nomeSala);

        if (salaDtoSalvo == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(salaDtoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Edita uma sala na aplicação", responses = {
        @ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de um SalaDTO com a sala modificada", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
        @ApiResponse(responseCode = "400", description = errosBuscarUmaSalaBadRequest, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @PutMapping(value = "/{idSala}")
    public ResponseEntity<SalaDTO> editarUmaSala(@RequestBody SalaDTO salaDto, @PathVariable String idSala) {
        var salaDtoEditado = salaService.editarSala(salaDto, idSala);

        return ResponseEntity.ok(salaDtoEditado);
    }
    
    @Operation(summary = "Deleta uma sala na aplicação", responses = {
        @ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de um SalaDTO com a sala deletada", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
        @ApiResponse(responseCode = "400", description = errosBuscarUmaSalaBadRequest, content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @DeleteMapping(value = "/{idSala}") 
	public ResponseEntity<SalaDTO> deletarUmaSala(@PathVariable String idSala) throws Exception {
    	try {
		salaService.deletarSala(Long.valueOf(idSala));
		return ResponseEntity.ok().build();
	}catch(Exception e) {
		throw e;
	}
    	}
    
	@Operation(summary = "Lista todas as salas cadastradas", responses = {
        @ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de uma lista de SalaDTO com as salas cadastradas", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
        @ApiResponse(responseCode = "400", description = "", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
    })
    @GetMapping
    public List<SalaDTO> listarTodasSalas() {
    	
    	List<SalaDTO> salas = salaService.listarTodasSalas();
    	if(salas == null)
    		return null;	
    	
    	
    	return salas;
    
	}
}

	

