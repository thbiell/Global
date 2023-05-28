package br.com.fiap.global.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.global.entities.model.usuario.DadosCadastroUsuario;
import br.com.fiap.global.entities.model.usuario.UsuarioRepository;
import br.com.fiap.global.entities.model.usuario.DadosAutenticacao;
import br.com.fiap.global.entities.model.usuario.Usuario;
import br.com.fiap.global.security.DadosTokenJWT;
import br.com.fiap.global.security.TokenService;
import jakarta.validation.Valid;

@RestController
public class AutenticacaoController {
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@RequestMapping("/login")
	@PostMapping
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
		System.out.println("Dados de autenticação:");
		System.out.println("Login: " + dados.login());
		System.out.println("Senha: " + dados.senha());

		UserDetails userDetails = repository.findByLogin(dados.login());
		if (userDetails != null) {
			var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, dados.senha(),
					userDetails.getAuthorities());
			var authentication = manager.authenticate(authenticationToken);

			var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

			return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
		} else {
			// Usuário não encontrado
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
		}
	}

	@PostMapping("/cadastro")
	@Transactional
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
		DadosCadastroUsuario cadastroUsuario = DadosCadastroUsuario.createWithEncryptedPassword(dados.login(), dados.senha());
		repository.save(new Usuario(cadastroUsuario)); // Usar cadastroUsuario em vez de dados
		Usuario user = new Usuario();
		var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(dados.login() + dados.senha());
	}


}
