package com.aerazo.movimientos.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;


@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de cuenta es obligatorio")
    @Column(unique = true)
    private String numeroCuenta;

    @NotBlank(message = "El tipo de cuenta es obligatorio")
    @Pattern(regexp = "Ahorros|Corriente", message = "El tipo de cuenta debe ser 'Ahorros' o 'Corriente'")
    private String tipoCuenta;

    @NotNull(message = "El saldo inicial es obligatorio")
    @Min(value = 0, message = "El saldo inicial no puede ser negativo")
    @Digits(integer = 10, fraction = 2, message = "El valor debe tener máximo 10 dígitos enteros y 2 decimales")
    private BigDecimal saldoInicial;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long cliente_id) {
        this.clienteId = cliente_id;
    }
}
