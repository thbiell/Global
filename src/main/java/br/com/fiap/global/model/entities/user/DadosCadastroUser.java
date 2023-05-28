package br.com.fiap.global.model.entities.user;


import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroUser(
		@NotBlank
		String nome,
		@NotBlank
		@Email
		String email,
		@NotBlank
		@Pattern(regexp = "^\\d{2}$")
		String idade,
		@NotBlank
		@Pattern(regexp = "\\d{10,11}")
		String telefone,
		@NotBlank
		@Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")
		String cpf,
		@NotNull @Valid DadosEndereco endereco
	) {}
