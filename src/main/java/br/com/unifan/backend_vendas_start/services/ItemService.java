package br.com.unifan.backend_vendas_start.services;

import br.com.unifan.backend_vendas_start.dtos.mapstruct.ItemMapper;
import br.com.unifan.backend_vendas_start.dtos.request.ItemRequest;
import br.com.unifan.backend_vendas_start.dtos.response.ItemResponse;
import br.com.unifan.backend_vendas_start.entity.Item;
import br.com.unifan.backend_vendas_start.entity.TipoItem;
import br.com.unifan.backend_vendas_start.exceptions.ResourceNotFoundException;
import br.com.unifan.backend_vendas_start.repository.ItemRepository;
import br.com.unifan.backend_vendas_start.repository.TipoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static br.com.unifan.backend_vendas_start.entity.enums.Status.ATIVADO;
import static br.com.unifan.backend_vendas_start.entity.enums.Status.DESATIVADO;

@Service
public class ItemService {

    @Autowired
    private TipoItemRepository tipoItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    public List<ItemResponse> findAllItems() {
        return itemMapper.toResponseList(itemRepository.findAll());
    }

    public ItemResponse findItemById(UUID id) {
        Item item = itemRepository.findByUuid(id)
                .orElseThrow(()-> new ResourceNotFoundException("Item não encontrado"));

        return itemMapper.toResponse(item);
    }

    public ItemResponse save(ItemRequest itemRequest) {
        Item item = itemMapper.toEntity(itemRequest);

        item = itemRepository.save(item);

        return itemMapper.toResponse(item);
    }

    public ItemResponse update(UUID id,ItemRequest itemRequest) {
        Item item = itemRepository.findByUuid(id)
                .orElseThrow(()-> new ResourceNotFoundException("Item não encontrado"));

        TipoItem tipoItem = tipoItemRepository.findByUuid(itemRequest.tipoId())
                        .orElseThrow(()-> new ResourceNotFoundException("Tipo não encontrado"));

        item.setDescricao(itemRequest.descricao());
        item.setNome(itemRequest.nome());
        item.setTipo(tipoItem);
        item.setValor(itemRequest.valor());

        item = itemRepository.save(item);
        return itemMapper.toResponse(item);
    }

    public ItemResponse desativarItem(UUID id) {
        Item item = itemRepository.findByUuid(id)
                .orElseThrow(()-> new ResourceNotFoundException("Item não encontrado"));

        item.setStatus(DESATIVADO);

        return itemMapper.toResponse(item);
    }

    public ItemResponse ativarItem(UUID id) {
        Item item = itemRepository.findByUuid(id)
                .orElseThrow(()-> new ResourceNotFoundException("Item não encontrado"));

        item.setStatus(ATIVADO);

        return itemMapper.toResponse(item);
    }


}
