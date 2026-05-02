package br.com.unifan.backend_vendas_start.dtos.response;

public record ItemVendaResponse(
        ItemResponse item,
        int quantidade
) {
}
