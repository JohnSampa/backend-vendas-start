package br.com.unifan.backend_vendas_start.dtos.mapstruct;

import br.com.unifan.backend_vendas_start.dtos.response.VendasResponse;
import br.com.unifan.backend_vendas_start.entity.Venda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = {
        ItemMapper.class,ClienteMapper.class,ItemVendasMapper.class})
public interface VendasMapper {

    @Mapping(target = "total",expression = "java(venda.getValorTotal())")
    @Mapping(target = "id",source = "uuid")
    VendasResponse toResponse (Venda venda);

    List<VendasResponse> toResponseList(List<Venda> vendas);


}
