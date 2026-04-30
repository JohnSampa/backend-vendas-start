package br.com.unifan.backend_vendas_start.dtos.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemRequest(
        @NotBlank(message = "O nome do item é obrigatório")
        String nome,

        @NotNull(message = "O valor não pode ser nulo")
        @DecimalMin(value = "0.0",message = "O valor mínimo é 0.0")
        @DecimalMax(value = "100.000.0",message = "O valor máximo é 100.000.0")
        @Positive(message = "O valor não pode ser menor que 0")
        BigDecimal valor,

        @NotBlank(message = "Insira alguma descrição")
        String descricao,

        @NotNull(message = "Insira um id de tipo")
        UUID tipoId
) {
}
