package br.com.fiap.global.entities.model.usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(
		@NotBlank
		String login,
		@NotBlank
		String senha) {

	public static DadosCadastroUsuario createWithEncryptedPassword(String login, String senha) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String senhaCriptografada = passwordEncoder.encode(senha);

		return new DadosCadastroUsuario(login, senhaCriptografada);
	}
}

