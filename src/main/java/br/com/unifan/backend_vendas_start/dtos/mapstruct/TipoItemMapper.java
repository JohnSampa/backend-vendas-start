package br.com.unifan.backend_vendas_start.dtos.mapstruct;

import br.com.unifan.backend_vendas_start.dtos.request.TipoItemRequest;
import br.com.unifan.backend_vendas_start.dtos.response.TipoItemResponse;
import br.com.unifan.backend_vendas_start.entity.TipoItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "items", ignore = true)
    TipoItem toEntity(TipoItemRequest tipoItem);

    @Mapping(target = "id",source = "uuid")
    TipoItemResponse toResponse(TipoItem tipoItem);

    List<TipoItemResponse> toResponseList(List<TipoItem> tipoItems);
}
