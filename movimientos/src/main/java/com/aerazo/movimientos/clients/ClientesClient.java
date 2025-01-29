package com.aerazo.movimientos.clients;


import com.aerazo.movimientos.entity.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENTES", url = "${clientes.url}")
public interface ClientesClient {

    @GetMapping("/{id}")
    Cliente getClienteById(@PathVariable Long id);

}
