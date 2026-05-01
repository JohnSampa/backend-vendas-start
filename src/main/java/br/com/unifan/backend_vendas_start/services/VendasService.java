package br.com.unifan.backend_vendas_start.services;

import br.com.unifan.backend_vendas_start.dtos.mapstruct.VendasMapper;
import br.com.unifan.backend_vendas_start.dtos.request.VendasRequest;
import br.com.unifan.backend_vendas_start.dtos.response.VendasResponse;
import br.com.unifan.backend_vendas_start.entity.Cliente;
import br.com.unifan.backend_vendas_start.entity.Item;
import br.com.unifan.backend_vendas_start.entity.Venda;
import br.com.unifan.backend_vendas_start.exceptions.ResourceNotFoundException;
import br.com.unifan.backend_vendas_start.repository.ClienteRepository;
import br.com.unifan.backend_vendas_start.repository.ItemRepository;
import br.com.unifan.backend_vendas_start.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VendasService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private VendasMapper vendasMapper;


    public List<VendasResponse> findAll() {
        return vendasMapper.toResponseList(vendaRepository.findAll());
    }

    public VendasResponse findById(UUID id) {
        Venda venda = vendaRepository.findByUuid(id)
                .orElseThrow(()-> new ResourceNotFoundException("Venda não encontrada"));

        return vendasMapper.toResponse(venda);
    }

    public VendasResponse save(VendasRequest vendasRequest) {
        Cliente cliente = clienteRepository.findByUuid(vendasRequest.clienteId())
                .orElseThrow(()-> new ResourceNotFoundException("Cliente inválida"));

        Venda venda = new Venda();

        venda.setCliente(cliente);
        venda.setData(vendasRequest.date());
        venda.setItems(getItemsById(vendasRequest.itemsIds()));

        venda = vendaRepository.save(venda);
        return vendasMapper.toResponse(venda);
    }


    private List<Item> getItemsById(List<UUID> itemsIds) {
        List<Item> items = itemRepository.findByUuidIn(itemsIds);

        if(items.size() < itemsIds.size()) {
            throw new  ResourceNotFoundException("Items inválidos");
        }

        return items;
    }
}
