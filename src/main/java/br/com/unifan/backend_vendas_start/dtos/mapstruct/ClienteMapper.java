package br.com.unifan.backend_vendas_start.dtos.mapstruct;

import br.com.unifan.backend_vendas_start.dtos.request.ClienteRequest;
import br.com.unifan.backend_vendas_start.dtos.response.ClienteResponse;
import br.com.unifan.backend_vendas_start.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "vendas", ignore = true)
    Cliente toEntity(ClienteRequest request);

    @Mapping(target = "id",source = "uuid")
    ClienteResponse toResponse(Cliente cliente);

    List<ClienteResponse> toResponseList(List<Cliente> clientes);
}
