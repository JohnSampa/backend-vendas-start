package br.com.unifan.backend_vendas_start.repository;

import br.com.unifan.backend_vendas_start.dtos.queryDTOs.ItemTotalVendasResponse;
import br.com.unifan.backend_vendas_start.dtos.queryDTOs.TotalPorTipoResponse;
import br.com.unifan.backend_vendas_start.entity.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long> {

    @Query("""
        SELECT SUM(iv.quantidade * i.valor)
        FROM ItemVenda iv
        JOIN iv.item i
        WHERE iv.venda.status = br.com.unifan.backend_vendas_start.entity.enums.VendaStatus.CONFIRMADA
    """)
    Optional<Double> getTotalVendas();

    @Query("""
        SELECT SUM(iv.quantidade)
        FROM ItemVenda iv
        JOIN iv.item i
        WHERE iv.venda.status = br.com.unifan.backend_vendas_start.entity.enums.VendaStatus.CONFIRMADA
    """)
    Optional<Long> getQuantidadeTotal();


    @Query("""
        SELECT new br.com.unifan.backend_vendas_start.dtos.queryDTOs.ItemTotalVendasResponse(
               i.uuid,
               i.nome,
               i.valor,
               i.descricao,
               i.status,
               SUM(iv.quantidade * i.valor),
               SUM(iv.quantidade)
        )
        FROM ItemVenda iv
        JOIN iv.item i
        WHERE iv.venda.status = br.com.unifan.backend_vendas_start.entity.enums.VendaStatus.CONFIRMADA
        GROUP BY  i.uuid,i.nome,i.valor,i.descricao,i.status
    """)
    List<ItemTotalVendasResponse> getTotalVendasByItem();


    @Query("""
        SELECT new br.com.unifan.backend_vendas_start.dtos.queryDTOs.TotalPorTipoResponse(
            t.uuid,
            t.nome,
            t.descricao,
            SUM(iv.quantidade * i.valor),
            SUM(iv.quantidade)
        )
        FROM ItemVenda iv
        JOIN iv.item i
        JOIN i.tipo t
        WHERE iv.venda.status = br.com.unifan.backend_vendas_start.entity.enums.VendaStatus.CONFIRMADA
        GROUP BY  t.uuid,t.nome,t.descricao
     """)
    List<TotalPorTipoResponse> getTotalVendasByTipo();

}
