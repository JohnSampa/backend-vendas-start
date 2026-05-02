package br.com.unifan.backend_vendas_start.dtos.queryDTOs;

import java.util.List;

public record TotalVendasResponse(
        Double totalDeVendas,
        Long quantidadeVendida,
        List<ItemTotalVendasResponse> totalPorItems,
        List<TotalPorTipoResponse> totalPorTipo
) {
}
