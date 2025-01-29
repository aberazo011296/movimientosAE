package com.aerazo.movimientos.dto;

import java.util.List;

public class ReporteEstadoCuentaDTO {

    private String cliente;
    private List<CuentaReporteDTO> cuentas;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<CuentaReporteDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaReporteDTO> cuentas) {
        this.cuentas = cuentas;
    }

}

