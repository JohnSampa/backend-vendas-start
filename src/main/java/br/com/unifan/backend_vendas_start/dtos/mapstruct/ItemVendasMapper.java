package br.com.unifan.backend_vendas_start.dtos.mapstruct;

import br.com.unifan.backend_vendas_start.dtos.response.ItemVendaResponse;
import br.com.unifan.backend_vendas_start.entity.ItemVenda;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface ItemVendasMapper {

    ItemVendaResponse ToItemVendaResponse(ItemVenda itemVenda);

    List<ItemVendaResponse> ToItemVendaResponse(List<ItemVenda> itemVendas);
}
