package br.com.unifan.backend_vendas_start.services;

import br.com.unifan.backend_vendas_start.dtos.mapstruct.ClienteMapper;
import br.com.unifan.backend_vendas_start.dtos.request.ClienteRequest;
import br.com.unifan.backend_vendas_start.dtos.response.ClienteResponse;
import br.com.unifan.backend_vendas_start.entity.Cliente;
import br.com.unifan.backend_vendas_start.exceptions.DataBaseException;
import br.com.unifan.backend_vendas_start.exceptions.ResourceNotFoundException;
import br.com.unifan.backend_vendas_start.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static br.com.unifan.backend_vendas_start.entity.enums.Status.ATIVADO;
import static br.com.unifan.backend_vendas_start.entity.enums.Status.DESATIVADO;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;


    public List<ClienteResponse> findAll() {
        return clienteMapper.toResponseList(clienteRepository.findAll());
    }

    public ClienteResponse findById(UUID id) {
        Cliente cliente = clienteRepository.findByUuid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cliente não encontrado com id: " + id)
                );

        return clienteMapper.toResponse(cliente);
    }

    public ClienteResponse save(ClienteRequest clienteRequest) {
        Cliente cliente = clienteMapper.toEntity(clienteRequest);

        cliente = clienteRepository.save(cliente);

        return clienteMapper.toResponse(cliente);
    }

    public ClienteResponse desativar(UUID id) {
        Cliente cliente = clienteRepository.findByUuid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cliente não encontrado com id: " + id)
                );

        cliente.setStatus(DESATIVADO);

        cliente = clienteRepository.save(cliente);

        return clienteMapper.toResponse(cliente);
    }

    public ClienteResponse ativar(UUID id) {
        Cliente cliente = clienteRepository.findByUuid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cliente não encontrado com id: " + id)
                );

        cliente.setStatus(ATIVADO);

        cliente = clienteRepository.save(cliente);

        return clienteMapper.toResponse(cliente);
    }

    public ClienteResponse update(UUID id, ClienteRequest clienteRequest) {
        Cliente cliente = clienteRepository.findByUuid(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cliente não encontrado com id: " + id)
                );

        cliente.setNome(clienteRequest.nome());
        cliente.setEmail(clienteRequest.email());
        cliente.setCep(clienteRequest.cep());

        return clienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Transactional
    public void delete(UUID id) {
        try {
            clienteRepository.deleteByUuid(id);
        }catch (DataIntegrityViolationException e) {
            throw new DataBaseException("O cliente não pode ser apagado pois está relacionado a vendas antigas");
        }
    }
}
