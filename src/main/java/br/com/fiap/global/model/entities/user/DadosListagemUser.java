package br.com.fiap.global.model.entities.user;

public record DadosListagemUser(
		Long id,
		String nome,
		String telefone,
		String email,
		String cpf,
		String idade,
		Boolean ativo,
		Endereco endereco

	) {
	
	public DadosListagemUser(User user) {
		this(
				user.getId(),
				user.getNome(),
				user.getTelefone(),
				user.getEmail(),
				user.getCpf(),
				user.getIdade(),
				user.getAtivo(),
				user.getEndereco()
			);
	}
	
}
