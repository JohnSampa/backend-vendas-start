package br.com.unifan.backend_vendas_start.dtos.mapstruct;

import br.com.unifan.backend_vendas_start.dtos.request.ItemRequest;
import br.com.unifan.backend_vendas_start.dtos.response.ItemResponse;
import br.com.unifan.backend_vendas_start.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",uses = TipoItemMapper.class)
public interface ItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "tipo", ignore = true)
    @Mapping(target = "status", ignore = true)
    Item toEntity(ItemRequest request);

    @Mapping(target = "id",source = "uuid")
    ItemResponse toResponse(Item item);

    List<ItemResponse> toResponseList(List<Item> items);


}
