package com.AlocaSalas.manager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AlocaSalas.manager.models.Usuario;
import com.AlocaSalas.manager.models.dto.DadosAutenticacao;
import com.AlocaSalas.manager.models.dto.DadosTokenJWT;
import com.AlocaSalas.manager.services.JWTokenService;



@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	private static final Logger logger = LoggerFactory.getLogger(AutenticacaoController.class);

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private JWTokenService tokenService;

	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados) {
		try {
			var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
			var authentication = manager.authenticate(authenticationToken);

			var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

			return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
		} catch (Exception e) {
			logger.error("Authentication failed: ", e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
		}
		
//		 var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
//	        var authentication = manager.authenticate(authenticationToken);
//
//	        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
//
//	        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}
}
