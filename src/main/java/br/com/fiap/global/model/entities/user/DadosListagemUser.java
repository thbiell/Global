package br.com.fiap.global.model.entities.user;

import br.com.fiap.global.entities.model.usuario.Usuario;

public record DadosListagemUser(
		Long id,
		String nome,
		String telefone,
		String email,
		String cpf,
		String idade,
		Boolean ativo,
		Endereco endereco,
		String login,
		Long users_id
) {

	public DadosListagemUser(User user, Usuario usuario) {

		this(
				user.getId(),
				user.getNome(),
				user.getTelefone(),
				user.getEmail(),
				user.getCpf(),
				user.getIdade(),
				user.getAtivo(),
				user.getEndereco(),
				usuario.getLogin(),
				usuario.getId()
		);
	}
}
