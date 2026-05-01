package br.com.unifan.backend_vendas_start.controllers;

import br.com.unifan.backend_vendas_start.dtos.request.ItemRequest;
import br.com.unifan.backend_vendas_start.dtos.response.ItemResponse;
import br.com.unifan.backend_vendas_start.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<ItemResponse>> findAll() {
        return ResponseEntity.ok(itemService.findAllItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(itemService.findItemById(id));
    }

    @PostMapping
    public ResponseEntity<ItemResponse> save(
            @RequestBody @Valid ItemRequest itemRequest
    ){
        ItemResponse itemResponse = itemService.save(itemRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(itemResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(itemResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> update(
            @PathVariable UUID id,
            @RequestBody @Valid ItemRequest itemRequest
    ){
        ItemResponse itemResponse = itemService.update(id, itemRequest);

        return ResponseEntity.ok(itemResponse);
    }

    @PostMapping("/{id}/ativar")
    public ResponseEntity<ItemResponse> ativar(
            @PathVariable UUID id
    ){
        ItemResponse response = itemService.ativarItem(id);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/desativar")
    public ResponseEntity<ItemResponse> desativar(
            @PathVariable UUID id
    ){
        ItemResponse response = itemService.desativarItem(id);

        return ResponseEntity.ok(response);
    }
}
