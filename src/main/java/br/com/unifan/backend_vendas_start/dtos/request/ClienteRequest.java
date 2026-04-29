package br.com.unifan.backend_vendas_start.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @Email(message = "Email inválido")
        String email,
        @NotBlank(message = "Cep é obrigatório")
        String cep
) {
}
