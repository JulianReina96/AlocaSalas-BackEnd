package com.AlocaSalas.manager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AlocaSalas.manager.exceptions.UserAlreadyExistsException;
import com.AlocaSalas.manager.models.dto.UserRoles;
import com.AlocaSalas.manager.models.dto.UsuarioDto;
import com.AlocaSalas.manager.models.dto.UsuarioDtoSaida;
import com.AlocaSalas.manager.models.dto.UsuarioDtoSalvo;
import com.AlocaSalas.manager.services.JWTokenService;
import com.AlocaSalas.manager.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	JWTokenService tokenService;

	private static final String errosSalvarUsuarioBadRequest = """
			Não foi possível encontrar nenhuma ROLE nesse usuário!<br>
			""";
	
	private static final String errosSalvarUsuarioUnprossableEntity = """
			Já existe um usuário cadastrado no nosso sistema com esse email! Por favor, tente novamente com outro email!<br>
			""";

	private static final String errosPegarRolesDoUsuário = """
			Usuário não encontrado no nosso sistema! <br>
			""";


	
	
	@Operation(summary = "Busca todos usuarios da aplicação")
	@ApiResponse(description = "retorna um Page com todos usuarios cadastrados na aplicação")
	@GetMapping
 	public List<UsuarioDto> todosUsuarios() {
		return usuarioService.todosUsuarios();
	}

	@Operation(summary = "Faz o cadastro de usuário na aplicação", responses = {
			@ApiResponse(responseCode = "201", description = "retorna um ResponseEntity de usuarioDto com a senha encryptada", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE) }),
			@ApiResponse(responseCode = "400", description = errosSalvarUsuarioBadRequest),
			@ApiResponse(responseCode = "422", description = errosSalvarUsuarioUnprossableEntity) })
	@PostMapping
	public ResponseEntity<UsuarioDtoSalvo> salvar(@RequestBody @Valid UsuarioDto usuarioDto)  {
		try {
			return new ResponseEntity<>(usuarioService.salvar(usuarioDto), HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.badRequest().build();
		} catch (RuntimeException e) {
			return ResponseEntity.unprocessableEntity().build();	
		}
		
	}

	@Operation(summary = "Busca as roles do usuario", responses = {
			@ApiResponse(responseCode = "200", description = "Retorna um ResponseEntity de UsuarioCommand com uma Lista de Roles do usuario"),
			@ApiResponse(responseCode = "400", description = errosPegarRolesDoUsuário) })
	@PostMapping(value = "/roles")
	public ResponseEntity<UserRoles> pegarRolesDoUsuario(@RequestHeader("Authorization") String authorizationHeader) {
		String token = null;
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
		}

		if (token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		var userId = tokenService.getClaim(token, "id").longValue();
		Long idUsuario;
		try {
			idUsuario = userId;
		} catch (NumberFormatException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		return new ResponseEntity<>(usuarioService.pegarRolesUsuario(idUsuario), HttpStatus.OK);
	}

	

	@Operation(summary = "Deleta um usuário cadastrado da base de dados" , responses = {
			@ApiResponse(description = "retorna um ok", responseCode = "200"),
			@ApiResponse(description = "retorna um unprocessableEntity", responseCode = "422")
	})
	@SuppressWarnings("unchecked")
	@DeleteMapping("/{id}")
	@Transactional
	@Secured("ROLE_ADMIN")
	public ResponseEntity<UsuarioDto> deletar(@PathVariable Long id) {

		return usuarioService.deletar(id) ? (ResponseEntity<UsuarioDto>) ResponseEntity.ok()
				: ResponseEntity.unprocessableEntity().build();
	}

}