package br.com.unifan.backend_vendas_start.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ItemsVendaRequest(
        @NotNull(message = "Insira um item")
        UUID itemId,
        @Positive(message = "A quantidade precisa ser maior que 0")
        @NotNull(message = "Insira uma quantidade")
        @Max(value = 100,message = "É permitido somente 100 unidades de um mesmo item")
        Integer quantidade
) {
}
