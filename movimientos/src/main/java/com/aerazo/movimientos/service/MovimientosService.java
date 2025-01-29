package com.aerazo.movimientos.service;

import com.aerazo.movimientos.dto.CuentaReporteDTO;
import com.aerazo.movimientos.dto.MovimientoReporteDTO;
import com.aerazo.movimientos.dto.ReporteEstadoCuentaDTO;
import com.aerazo.movimientos.entity.Cliente;
import com.aerazo.movimientos.entity.Cuenta;
import com.aerazo.movimientos.entity.Movimiento;
import com.aerazo.movimientos.handlers.SaldoInsuficienteException;
import com.aerazo.movimientos.repository.MovimientosRepository;
import com.aerazo.movimientos.utils.StringExt;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientosService {

    @Autowired
    private MovimientosRepository movimientosRepository;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private ClienteService clienteService;

    public List<Movimiento> getAllMovimientos() {
        return movimientosRepository.findAll();
    }

    public Movimiento createMovimiento(Movimiento movimiento) {

        BigDecimal valorMovimiento = movimiento.getValor();
        Cuenta cuenta = cuentaService.getCuentaById(movimiento.getCuentaId());

        BigDecimal saldoAActualizar = cuenta.getSaldoInicial().add(valorMovimiento);

        if (valorMovimiento.compareTo(BigDecimal.ZERO) < 0 && saldoAActualizar.compareTo(BigDecimal.ZERO) < 0) {
            throw new SaldoInsuficienteException();
        }

        cuenta.setSaldoInicial(saldoAActualizar);
        cuentaService.updateCuenta(movimiento.getCuentaId(), cuenta);

        movimiento.setSaldo(cuenta.getSaldoInicial());
        movimiento.setTipoMovimiento(valorMovimiento.compareTo(BigDecimal.ZERO) > 0 ? "Crédito" : "Débito");

        return movimientosRepository.save(movimiento);

    }

    public Movimiento getMovimientoById(Long id) {
        return movimientosRepository.findById(id).orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
    }

    public List<Movimiento> getAllMovimientosByCuentaId(Long cuenta_id) {
        return movimientosRepository.findByCuentaId(cuenta_id);
    }

    public Movimiento updateMovimiento(Long id, Movimiento movimientoDetails) {
        Movimiento movimiento = movimientosRepository.findById(id).orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        movimiento.setFecha(movimientoDetails.getFecha());
        movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
        movimiento.setValor(movimientoDetails.getValor());
        movimiento.setSaldo(movimientoDetails.getSaldo());
        return movimientosRepository.save(movimiento);
    }

    @Transactional
    public ReporteEstadoCuentaDTO generarReporteEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {

        Cliente cliente = clienteService.getClienteById(clienteId);

        List<Cuenta> cuentas = cuentaService.getAllCuentasByClienteId(clienteId);

        ReporteEstadoCuentaDTO reporte = new ReporteEstadoCuentaDTO();
        reporte.setCliente(cliente.getNombre());

        List<CuentaReporteDTO> cuentasDTO = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {

            CuentaReporteDTO cuentaDTO = new CuentaReporteDTO();
            cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaDTO.setSaldo(cuenta.getSaldoInicial());
            cuentaDTO.setEstado(cuenta.getEstado() ? "Activa" : "Inactiva");
            cuentaDTO.setTipo(cuenta.getTipoCuenta());

            List<Movimiento> movimientos = movimientosRepository.findByCuentaIdAndFechaBetweenOrderByFechaDesc(cuenta.getId(), fechaInicio.atStartOfDay(), fechaFin.atTime(23, 59, 59));

            List<MovimientoReporteDTO> movimientosDTO = movimientos.stream().map(mov -> {

                MovimientoReporteDTO movimientoDTO = new MovimientoReporteDTO();
                movimientoDTO.setTipoMovimiento(mov.getTipoMovimiento());
                movimientoDTO.setValor(mov.getValor());
                movimientoDTO.setSaldo(mov.getSaldo());
                movimientoDTO.setFechaMovimiento(StringExt.convertirFecha(mov.getFecha()));

                return movimientoDTO;

            }).collect(Collectors.toList());

            cuentaDTO.setMovimientos(movimientosDTO);
            cuentasDTO.add(cuentaDTO);
        }

        reporte.setCuentas(cuentasDTO);
        return reporte;

    }

}
