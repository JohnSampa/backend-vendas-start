package br.com.unifan.backend_vendas_start.dtos.response;

import java.util.UUID;

public record TipoItemResponse(
        UUID id,
        String nome,
        String descricao
) {
}
