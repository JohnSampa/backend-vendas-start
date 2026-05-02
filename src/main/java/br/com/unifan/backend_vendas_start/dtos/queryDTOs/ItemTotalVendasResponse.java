package br.com.unifan.backend_vendas_start.dtos.queryDTOs;

import br.com.unifan.backend_vendas_start.entity.enums.Status;

import java.util.UUID;

public record ItemTotalVendasResponse (
        UUID id,
        String nome,
        Double valor,
        String descricao,
        Status status,
        Double valorTotal,
        Long quantidadeVendida
){
}
