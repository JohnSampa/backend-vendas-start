package br.com.unifan.backend_vendas_start.dtos.response;

import br.com.unifan.backend_vendas_start.entity.enums.VendaStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record VendasResponse(
        UUID id,
        Double total,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate data,
        VendaStatus status,
        ClienteResponse cliente,
        List<ItemVendaResponse> items
) {
}
