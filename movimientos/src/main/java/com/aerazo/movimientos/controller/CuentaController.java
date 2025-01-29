package com.aerazo.movimientos.controller;

import com.aerazo.movimientos.entity.Cuenta;
import com.aerazo.movimientos.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
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
@RequestMapping("/cuentas")
@Validated
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @GetMapping("/{id}")
    public Cuenta getCuentaById(@PathVariable Long id) {
        return cuentaService.getCuentaById(id);
    }

    @GetMapping("/cliente/{cliente_id}")
    public List<Cuenta> getCuentaByClienteId(@PathVariable Long cliente_id) {
        return cuentaService.getAllCuentasByClienteId(cliente_id);
    }

    @PostMapping
    public ResponseEntity<Cuenta> createCuenta(@Valid @RequestBody Cuenta cuenta) {
        Cuenta nuevaCuenta = cuentaService.createCuenta(cuenta);
        return ResponseEntity.created(URI.create("/cuentas/" + nuevaCuenta.getId())).body(nuevaCuenta);
    }

    @PutMapping("/{id}")
    public Cuenta updateCuenta(@PathVariable Long id, @Valid @RequestBody Cuenta cuentaDetails) {
        return cuentaService.updateCuenta(id, cuentaDetails);
    }

}
