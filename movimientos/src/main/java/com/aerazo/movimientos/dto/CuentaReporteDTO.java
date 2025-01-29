package com.aerazo.movimientos.dto;

import java.math.BigDecimal;
import java.util.List;

public class CuentaReporteDTO {

    private String numeroCuenta;
    private BigDecimal saldo;
    private String estado;
    private String tipo;
    private List<MovimientoReporteDTO> movimientos;

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<MovimientoReporteDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoReporteDTO> movimientos) {
        this.movimientos = movimientos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
