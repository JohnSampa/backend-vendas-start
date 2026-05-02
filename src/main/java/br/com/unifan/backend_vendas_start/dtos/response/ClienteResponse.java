package br.com.unifan.backend_vendas_start.dtos.response;

import br.com.unifan.backend_vendas_start.entity.enums.Status;

import java.util.UUID;

public record ClienteResponse(
        UUID id,
        String nome,
        String email,
        Status status,
        String cep

) {
}
