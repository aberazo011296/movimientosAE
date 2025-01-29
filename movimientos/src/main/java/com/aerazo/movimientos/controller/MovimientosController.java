package com.aerazo.movimientos.controller;

import com.aerazo.movimientos.entity.Movimiento;
import com.aerazo.movimientos.service.MovimientosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
@Validated
public class MovimientosController {

    @Autowired
    private MovimientosService movimientosService;

    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientosService.getAllMovimientos();
    }

    @PostMapping
    public Movimiento createMovimiento(@Valid @RequestBody Movimiento movimiento) {
        return movimientosService.createMovimiento(movimiento);
    }

    @GetMapping("/{id}")
    public Movimiento getMovimientoById(@PathVariable Long id) {
        return movimientosService.getMovimientoById(id);
    }

    @GetMapping("/cuenta/{cuenta_id}")
    public List<Movimiento> getMovimientosByCuentaId(@PathVariable Long cuenta_id) {
        return movimientosService.getAllMovimientosByCuentaId(cuenta_id);
    }

    @PutMapping("/{id}")
    public Movimiento updateMovimiento(@PathVariable Long id, @Valid @RequestBody Movimiento movimientoDetails) {
        return movimientosService.updateMovimiento(id, movimientoDetails);
    }

}
