package br.com.fiap.global.model.entities.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoUser(
        @NotNull
        Long id,
        String nome,
        String email,
        @Pattern(regexp = "^\\d{2}$")
        String idade,
        @Pattern(regexp = "\\d{10,11}")
        String telefone,
        @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")
        String cpf,
        DadosEndereco endereco
) {}
