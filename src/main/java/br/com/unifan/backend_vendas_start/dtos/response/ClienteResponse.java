package br.com.unifan.backend_vendas_start.dtos.response;

import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String nome,
        String email,
        String cep

) {
}
