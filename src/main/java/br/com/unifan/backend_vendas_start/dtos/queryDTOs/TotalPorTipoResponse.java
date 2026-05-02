package br.com.unifan.backend_vendas_start.dtos.queryDTOs;

import java.util.UUID;

public record TotalPorTipoResponse(
        UUID id,
        String nome,
        String descricao,
        Double totalDeVendas,
        Long quantidadeVendida
) {
}
