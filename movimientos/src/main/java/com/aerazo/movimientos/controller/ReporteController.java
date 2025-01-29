package com.aerazo.movimientos.controller;

import com.aerazo.movimientos.dto.ReporteEstadoCuentaDTO;
import com.aerazo.movimientos.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/reportes")
@Validated
public class ReporteController {

    @Autowired
    private MovimientosService movimientosService;

    @GetMapping
    public ResponseEntity<ReporteEstadoCuentaDTO> generarReporteEstadoCuenta(@RequestParam Long clienteId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        return ResponseEntity.ok(movimientosService.generarReporteEstadoCuenta(clienteId, fechaInicio, fechaFin));

    }

}
