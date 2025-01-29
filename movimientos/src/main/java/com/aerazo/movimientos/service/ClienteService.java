package com.aerazo.movimientos.service;

import com.aerazo.movimientos.clients.ClientesClient;
import com.aerazo.movimientos.entity.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClientesClient clientesClient;

    public ClienteService(ClientesClient clientesClient) {
        this.clientesClient = clientesClient;
    }

    public Cliente getClienteById(Long id) {
        return clientesClient.getClienteById(id);
    }
}
