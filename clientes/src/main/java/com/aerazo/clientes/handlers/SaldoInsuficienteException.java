package com.aerazo.clientes.handlers;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException() {
        super("Saldo no disponible");
    }

}

