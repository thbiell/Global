package br.com.fiap.global.controllers;

import br.com.fiap.global.entities.model.usuario.Usuario;
import br.com.fiap.global.entities.model.usuario.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.fiap.global.model.entities.user.DadosAtualizacaoUser;
import br.com.fiap.global.model.entities.user.DadosCadastroUser;
import br.com.fiap.global.model.entities.user.DadosListagemUser;
import br.com.fiap.global.model.entities.user.User;
import br.com.fiap.global.model.entities.user.UserRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class GlobalController {
	
	@Autowired
	private UserRepository repository;
	@Autowired
	private UsuarioRepository repositoryU;

	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity cadastrar(@PathVariable Long id, @RequestBody @Valid DadosCadastroUser dados, UriComponentsBuilder uriBuilder) {
		User user = repository.save(new User(dados));
		Usuario usuario = repositoryU.findById(id).orElse(null);
		if (usuario != null) {
			usuario.setUser(user);
			repositoryU.save(usuario);
		} else {
			System.out.println("Não foi encontrado");
		}
		var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosListagemUser(user, usuario));
	}



	@GetMapping
	public ResponseEntity<Page<DadosListagemUser>> listar(@PageableDefault(size = 5, sort = {"id"}) Pageable paginacao) {
		Page<DadosListagemUser> page = repository.findAllByAtivoTrue(paginacao)
				.map(user -> new DadosListagemUser(user, user.getUsuario()));
		return ResponseEntity.ok(page);
	}




	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoUser dados) {
		User user = repository.getReferenceById(dados.id());
		user.atualizarInformacoes(dados);
		Usuario usuario = user.getUsuario();
		return ResponseEntity.ok(new DadosListagemUser(user, usuario));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosListagemUser> detalhar(@PathVariable Long id) {
		User user = repository.getReferenceById(id);
		Usuario usuario = user.getUsuario(); // Obtém o usuário associado ao User
		DadosListagemUser dadosListagemUser = new DadosListagemUser(user, usuario);
		return ResponseEntity.ok(dadosListagemUser);
	}


	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		User user = repository.getReferenceById(id);
		user.deleteUser();
		
		return ResponseEntity.noContent().build();
	}
}
