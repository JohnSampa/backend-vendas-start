package br.com.unifan.backend_vendas_start.dtos.response;

import br.com.unifan.backend_vendas_start.entity.enums.Status;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemResponse(
        UUID id,
        String nome,
        BigDecimal valor,
        String descricao,
        Status status,
        TipoItemResponse tipo
) {
}
