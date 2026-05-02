package br.com.unifan.backend_vendas_start.services;

import br.com.unifan.backend_vendas_start.dtos.request.ItemsVendaRequest;
import br.com.unifan.backend_vendas_start.dtos.queryDTOs.ItemTotalVendasResponse;
import br.com.unifan.backend_vendas_start.dtos.queryDTOs.TotalPorTipoResponse;
import br.com.unifan.backend_vendas_start.entity.Item;
import br.com.unifan.backend_vendas_start.entity.ItemVenda;
import br.com.unifan.backend_vendas_start.entity.enums.Status;
import br.com.unifan.backend_vendas_start.exceptions.BusinessException;
import br.com.unifan.backend_vendas_start.exceptions.ResourceNotFoundException;
import br.com.unifan.backend_vendas_start.repository.ItemRepository;
import br.com.unifan.backend_vendas_start.repository.ItemVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemVendaService {

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemVenda> getItemsVendasByRequest(
            List<ItemsVendaRequest> itemsVendaRequests
    ) {
        List<UUID> ids = itemsVendaRequests.stream()
                .map(ItemsVendaRequest::itemId)
                .toList();

        List<Item> itemsFromDb = itemRepository.findByUuidIn(ids);

        long desativados = itemsFromDb
                .stream()
                .filter(item -> item.getStatus().equals(Status.DESATIVADO))
                .count();

        if (desativados > 0)
            throw new BusinessException("Remova os itens desativados");

        Map<UUID, Item> itemsMap = itemsFromDb
                .stream()
                .collect(Collectors.toMap(Item::getUuid, item -> item));

        List<ItemVenda> items = new ArrayList<>();

        itemsVendaRequests.forEach(itemRequest -> {
            Item item = itemsMap.get(itemRequest.itemId());

            if (item == null)
                throw new ResourceNotFoundException(
                        "Não foi encontrado item com id: " + itemRequest.itemId());

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setItem(item);
            itemVenda.setQuantidade(itemRequest.quantidade());

            items.add(itemVenda);
        });

        return items;
    }

    public Double totalDeVenda(){
        return itemVendaRepository.getTotalVendas();
    }

    public  List<TotalPorTipoResponse> getTotalVendasByTipo(){
        return itemVendaRepository.getTotalVendasByTipo();
    }

    public List<ItemTotalVendasResponse> getTotalVendasByItem(){
        return itemVendaRepository.getTotalVendasByItem();
    }

    public Long getQuantidadeTotal(){
        return itemVendaRepository.getQuantidadeTotal();
    }
}
