package br.com.unifan.backend_vendas_start.services;

import br.com.unifan.backend_vendas_start.dtos.mapstruct.TipoItemMapper;
import br.com.unifan.backend_vendas_start.dtos.request.TipoItemRequest;
import br.com.unifan.backend_vendas_start.dtos.response.TipoItemResponse;
import br.com.unifan.backend_vendas_start.entity.TipoItem;
import br.com.unifan.backend_vendas_start.exceptions.BusinessException;
import br.com.unifan.backend_vendas_start.exceptions.ResourceNotFoundException;
import br.com.unifan.backend_vendas_start.repository.TipoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TipoItemService {

    @Autowired
    private TipoItemRepository tipoItemRepository;

    @Autowired
    private TipoItemMapper tipoItemMapper;

    public List<TipoItemResponse> findAll(){
        return  tipoItemMapper.toResponseList(tipoItemRepository.findAll());
    }

    public TipoItemResponse findById(UUID id){
        TipoItem tipoItem = tipoItemRepository.findByUuid(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tipo não encontrado"));

        return tipoItemMapper.toResponse(tipoItem);
    }

    public TipoItemResponse save(TipoItemRequest tipoItemRequest){
        TipoItem tipoItem = tipoItemMapper.toEntity(tipoItemRequest);

        tipoItemRepository.save(tipoItem);

        return tipoItemMapper.toResponse(tipoItem);
    }

    public TipoItemResponse update(UUID id,TipoItemRequest tipoItemRequest){
        TipoItem tipoItem = tipoItemRepository.findByUuid(id)
                .orElseThrow(()-> new ResourceNotFoundException("Tipo não encontrado"));

        tipoItem.setNome(tipoItemRequest.nome());
        tipoItem.setDescricao(tipoItemRequest.descricao());
        tipoItemRepository.save(tipoItem);
        return tipoItemMapper.toResponse(tipoItem);
    }

    public void delete(UUID id){
        try{
            TipoItem tipoItem = tipoItemRepository.findByUuid(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Tipo não encontrado"));

            tipoItemRepository.delete(tipoItem);
        }catch (DataIntegrityViolationException ex){
            throw new BusinessException("Existem items vinculados para esse tipo");
        }
    }
}
