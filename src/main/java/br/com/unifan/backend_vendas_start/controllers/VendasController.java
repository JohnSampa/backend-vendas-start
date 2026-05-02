package br.com.unifan.backend_vendas_start.controllers;

import br.com.unifan.backend_vendas_start.dtos.request.VendasRequest;
import br.com.unifan.backend_vendas_start.dtos.queryDTOs.TotalVendasResponse;
import br.com.unifan.backend_vendas_start.dtos.response.VendasResponse;
import br.com.unifan.backend_vendas_start.services.VendasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendasService vendasService;

    @GetMapping
    public ResponseEntity<List<VendasResponse>> findWithFilters(
            @RequestParam(required = false)
            UUID clienteId,
            @RequestParam(required = false)
            UUID itemId,
            @RequestParam (required = false)
            UUID tipoDeItemId

    ) {
        List<VendasResponse> responses = vendasService
                .findByFilters(clienteId, itemId, tipoDeItemId);

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/relatoriosTotais")
    public ResponseEntity<TotalVendasResponse> getTotalVendasByTipo(){
        return ResponseEntity.ok(vendasService.relatoriosTotaisVendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendasResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(vendasService.findById(id));
    }

    @PostMapping
    public ResponseEntity<VendasResponse> save(
            @RequestBody @Valid VendasRequest vendasRequest
    ){
        VendasResponse vendasResponse = vendasService.save(vendasRequest);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vendasResponse.id())
                .toUri();

        return ResponseEntity.created(uri).body(vendasResponse);
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<VendasResponse> confirmar(
            @PathVariable UUID id
    ){
        VendasResponse vendasResponse = vendasService.confirmar(id);

        return ResponseEntity.ok(vendasResponse);
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<VendasResponse> cancelar(
            @PathVariable UUID id
    ){
        VendasResponse vendasResponse = vendasService.cancelar(id);

        return ResponseEntity.ok(vendasResponse);
    }
}
