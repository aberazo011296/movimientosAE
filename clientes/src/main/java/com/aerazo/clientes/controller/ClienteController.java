package com.aerazo.clientes.controller;

import com.aerazo.clientes.entity.Cliente;
import com.aerazo.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@Validated
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Long id) {
        return clienteService.getClienteById(id);
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cuenta) {
        Cliente nuevoCliente = clienteService.createCliente(cuenta);
        return ResponseEntity.created(URI.create("/clientes/" + nuevoCliente.getId())).body(nuevoCliente);
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @Valid @RequestBody Cliente clienteDetails) {
        return clienteService.updateCliente(id, clienteDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMovimiento(@PathVariable Long id) {
        clienteService.deleteMovimiento(id);
    }

}
