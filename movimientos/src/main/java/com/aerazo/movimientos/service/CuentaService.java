package com.aerazo.movimientos.service;

import com.aerazo.movimientos.entity.Cuenta;
import com.aerazo.movimientos.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private ClienteService clienteService;

    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta getCuentaById(Long id) {
        return cuentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrado"));
    }

    public List<Cuenta> getAllCuentasByClienteId(Long cliente_id) {
        return cuentaRepository.findByClienteId(cliente_id);
    }

    public Cuenta createCuenta(Cuenta cuenta) {
        clienteService.getClienteById(cuenta.getClienteId());
        return cuentaRepository.save(cuenta);
    }

    public Cuenta updateCuenta(Long id, Cuenta cuentaDetails) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuenta.setNumeroCuenta(cuentaDetails.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
        cuenta.setEstado(cuentaDetails.getEstado());
        return cuentaRepository.save(cuenta);
    }

}
