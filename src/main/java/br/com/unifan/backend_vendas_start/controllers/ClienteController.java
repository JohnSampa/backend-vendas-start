package br.com.unifan.backend_vendas_start.controllers;

import br.com.unifan.backend_vendas_start.dtos.request.ClienteRequest;
import br.com.unifan.backend_vendas_start.dtos.response.ClienteResponse;
import br.com.unifan.backend_vendas_start.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> save(
            @RequestBody @Valid ClienteRequest clienteRequest
    ){
       ClienteResponse clienteResponse = clienteService.save(clienteRequest);

       URI uri =  ServletUriComponentsBuilder
               .fromCurrentRequest()
               .path("/{id}")
               .buildAndExpand(clienteResponse.id())
               .toUri();

       return ResponseEntity.created(uri).body(clienteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        clienteService.safeDelete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<ClienteResponse> ativar(@PathVariable UUID id){
        return  ResponseEntity.ok(clienteService.ativar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> update(
            @RequestBody @Valid ClienteRequest clienteRequest,
            @PathVariable UUID id
    ){
        ClienteResponse clienteResponse = clienteService.save(clienteRequest);
        return ResponseEntity.ok(clienteResponse);
    }
}
