package com.aerazo.movimientos.handlers;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException() {
        super("Saldo no disponible");
    }

}

