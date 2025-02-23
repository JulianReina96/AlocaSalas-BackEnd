
package com.AlocaSalas.manager.controllers;

import com.AlocaSalas.manager.models.Horario;
import com.AlocaSalas.manager.models.dto.HorarioDTO;
import com.AlocaSalas.manager.enumerator.*;
import com.AlocaSalas.manager.services.HorarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

	@Autowired
	private HorarioService horarioService;

	
    @Operation(summary = "Lista todos os horarios cadastrados", responses = {
            @ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de uma lista de HorarioDTO com os horarios cadastrados", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
        })
	@GetMapping
	public List<HorarioDTO> listarTodosHorarios() {
    	
    	List<Horario> horarioBanco =horarioService.listarTodosHorarios();
    	if(horarioBanco == null)
    		return null;	
    	List<HorarioDTO> horarios = new ArrayList<HorarioDTO>();
    	
    	for (Horario horario : horarioBanco) {
    		horarios.add(new HorarioDTO(horario));
    	}
    	return horarios;
	}

    @Operation(summary = "Lista os horarios de determinado turno do dia", responses = {
            @ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de uma lista de HorarioDTO de um determinado turno do dia ", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
        })
	@GetMapping("/turno/{turno}")
	public List<HorarioDTO> listarHorariosPorTurno(@PathVariable TurnoHorario turno) {
		
		List<Horario> horarioBanco =horarioService.listarHorariosPorTurno(turno);
    	if(horarioBanco == null)
    		return null;	
    	List<HorarioDTO> horarios = new ArrayList<HorarioDTO>();
    	
    	for (Horario horario : horarioBanco) {
    		horarios.add(new HorarioDTO(horario));
    	}
    	return horarios;
		
		
	}
    @Operation(summary = "Lista os horarios de determinado dia da semana", responses = {
            @ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de uma lista de HorarioDTO de um determinado dia da semana ", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
        })

	@GetMapping("/dia/{dia}")
	public List<HorarioDTO> listarHorariosPorDia(@PathVariable DiaDaSemana dia) {
		
		List<Horario> horarioBanco =horarioService.listarHorariosPorDia(dia);
    	if(horarioBanco == null)
    		return null;	
    	List<HorarioDTO> horarios = new ArrayList<HorarioDTO>();
    	
    	for (Horario horario : horarioBanco) {
    		horarios.add(new HorarioDTO(horario));
    	}
    	return horarios;
    }
    @Operation(summary = "Lista os horarios de disponiveis para a disciplina naquela sala", responses = {
            @ApiResponse(responseCode = "201", description = "Retorna um ResponseEntity de uma lista de HorarioDTO de um determinado dia da semana ", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)}),
            @ApiResponse(responseCode = "400", description = "", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)})
        })
    	@GetMapping(value = "/{salaId}/{disciplinaId}")
    	public List<HorarioDTO> buscarHorariosDisponiveis(@PathVariable Long salaId, @PathVariable Long disciplinaId) {

			List<Horario> horarioBanco = horarioService.buscarHorariosDisponiveis(salaId, disciplinaId);
			if (horarioBanco == null)
				return null;
			List<HorarioDTO> horarios = new ArrayList<HorarioDTO>();

			for (Horario horario : horarioBanco) {
				horarios.add(new HorarioDTO(horario));
			}
			return horarios;
    		
    		
    	}
		
		
	
}
