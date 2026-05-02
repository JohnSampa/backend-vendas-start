package br.com.unifan.backend_vendas_start.repository;

import br.com.unifan.backend_vendas_start.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VendaRepository extends JpaRepository<Venda,Long> {

    Optional<Venda> findByUuid(UUID uuid);


    @Query("""
        SELECT venda FROM Venda venda
        WHERE(:clienteUUID IS NULL OR venda.cliente.uuid = :clienteUUID)
        AND(
            :itemUUID IS NULL AND :tipoDeItemUUID IS NULL
            OR EXISTS(
            SELECT itemvenda FROM venda.items itemvenda
            WHERE (:itemUUID IS NULL OR itemvenda.item.uuid = :itemUUID)
            AND(:tipoDeItemUUID IS NULL OR itemvenda.item.tipo.uuid = :tipoDeItemUUID)
            )
        )
    """)
    List<Venda> findWithFilters(
            @Param("clienteUUID") UUID clienteId,
            @Param("itemUUID") UUID itemId,
            @Param("tipoDeItemUUID") UUID tipoDeItemId
    );

}
