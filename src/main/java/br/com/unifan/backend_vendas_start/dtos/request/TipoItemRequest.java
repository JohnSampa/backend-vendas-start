package br.com.unifan.backend_vendas_start.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record TipoItemRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @NotBlank(message = "Digite alguma descrição")
        String descricao
) {
}
