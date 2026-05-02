package br.com.unifan.backend_vendas_start.dtos.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record VendasRequest(
        @NotNull(message = "A data é obrigatória")
        LocalDate date,
        @NotNull(message = "Insira um cliente")
        UUID clienteId,
        @Valid
        @NotNull(message = "A venda precisa conter items")
        @Size(min = 1,message = "Uma venda deve possuir pelo menos um item")
        List<ItemsVendaRequest> items
) {
}
