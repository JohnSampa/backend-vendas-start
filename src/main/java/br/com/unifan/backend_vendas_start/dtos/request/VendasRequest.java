package br.com.unifan.backend_vendas_start.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record VendasRequest(
        @NotBlank(message = "A data é obrigatória")
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate date,
        @NotBlank(message = "Insira um cliente")
        UUID clienteId,
        @Size(min = 1,message = "Uma venda deve possuir pelo menos um item")
        List<UUID> itemsIds
) {
}
