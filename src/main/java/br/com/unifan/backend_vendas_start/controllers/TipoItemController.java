package br.com.unifan.backend_vendas_start.controllers;

import br.com.unifan.backend_vendas_start.dtos.request.TipoItemRequest;
import br.com.unifan.backend_vendas_start.dtos.response.TipoItemResponse;
import br.com.unifan.backend_vendas_start.services.TipoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tipos-de-item")
public class TipoItemController {

    @Autowired
    private TipoItemService tipoItemService;

    @GetMapping
    public ResponseEntity<List<TipoItemResponse>> findAll() {
        return ResponseEntity.ok(tipoItemService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<TipoItemResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(tipoItemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipoItemResponse> save(
            @RequestBody @Valid TipoItemRequest tipoItemRequest
    ) {
        TipoItemResponse tipoItemResponse = tipoItemService.save(tipoItemRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tipoItemResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(tipoItemResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoItemResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid TipoItemRequest tipoItemRequest
    ){
        TipoItemResponse tipoItemResponse = tipoItemService.update(id, tipoItemRequest);
        return ResponseEntity.ok(tipoItemResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        tipoItemService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
