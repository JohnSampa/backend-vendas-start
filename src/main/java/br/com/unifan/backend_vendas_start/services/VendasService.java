package br.com.unifan.backend_vendas_start.services;

import br.com.unifan.backend_vendas_start.dtos.mapstruct.VendasMapper;
import br.com.unifan.backend_vendas_start.dtos.queryDTOs.ItemTotalVendasResponse;
import br.com.unifan.backend_vendas_start.dtos.request.VendasRequest;
import br.com.unifan.backend_vendas_start.dtos.queryDTOs.TotalVendasResponse;
import br.com.unifan.backend_vendas_start.dtos.response.VendasResponse;
import br.com.unifan.backend_vendas_start.entity.Cliente;
import br.com.unifan.backend_vendas_start.entity.ItemVenda;
import br.com.unifan.backend_vendas_start.entity.Venda;
import br.com.unifan.backend_vendas_start.exceptions.BusinessException;
import br.com.unifan.backend_vendas_start.exceptions.ResourceNotFoundException;
import br.com.unifan.backend_vendas_start.repository.ClienteRepository;
import br.com.unifan.backend_vendas_start.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static br.com.unifan.backend_vendas_start.entity.enums.VendaStatus.CANCELADA;
import static br.com.unifan.backend_vendas_start.entity.enums.VendaStatus.CONFIRMADA;

@Service
public class VendasService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemVendaService itemVendaService;

    @Autowired
    private VendasMapper vendasMapper;


    public List<VendasResponse> findByFilters(
            UUID clienteId,
            UUID itemId,
            UUID tipoDeItemId
    ){
        return vendasMapper.toResponseList(
                vendaRepository.findWithFilters(clienteId, itemId, tipoDeItemId)
        );
    }

    public TotalVendasResponse relatoriosTotaisVendas() {
        Double totalGlobal = itemVendaService.totalDeVenda();
        var totalItemsList = itemVendaService.getTotalVendasByItem();
        var totalTiposList = itemVendaService.getTotalVendasByTipo();
        long quantidadeTotal = itemVendaService.getQuantidadeTotal();

        return new TotalVendasResponse(
                totalGlobal,
                quantidadeTotal,
                totalItemsList,
                totalTiposList
        );
    }

    public VendasResponse findById(UUID id) {
        Venda venda = vendaRepository.findByUuid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));

        return vendasMapper.toResponse(venda);
    }

    public VendasResponse save(VendasRequest vendasRequest) {
        Cliente cliente = clienteRepository.findByUuid(vendasRequest.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente inválida"));

        Venda venda = new Venda();


        venda.setCliente(cliente);
        venda.setData(vendasRequest.date());

        List<ItemVenda> itemsVenda = itemVendaService
                .getItemsVendasByRequest(vendasRequest.items());

        itemsVenda.forEach(venda::addItem);

        venda = vendaRepository.save(venda);
        return vendasMapper.toResponse(venda);
    }

    public VendasResponse cancelar(UUID id) {
        Venda venda = vendaRepository.findByUuid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));

        if (venda.getStatus().equals(CANCELADA)) {
            throw new BusinessException("A venda ja foi cancelada");
        }

        venda.setStatus(CANCELADA);

        venda = vendaRepository.save(venda);
        return vendasMapper.toResponse(venda);
    }

    public VendasResponse confirmar(UUID id) {
        Venda venda = vendaRepository.findByUuid(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));

        if (venda.getStatus().equals(CONFIRMADA)) {
            throw new BusinessException("A venda ja foi confirmada");
        }

        if (venda.getStatus().equals(CANCELADA)) {
            throw new BusinessException("A venda não pode ser confirmada pois ja foi cancelada");
        }

        venda.setStatus(CONFIRMADA);

        venda = vendaRepository.save(venda);

        return vendasMapper.toResponse(venda);
    }



}
