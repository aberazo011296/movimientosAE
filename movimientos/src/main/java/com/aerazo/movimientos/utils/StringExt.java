package com.aerazo.movimientos.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringExt {

    private static final DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static String convertirFecha(LocalDateTime fecha){
        return fecha.format(formatoSalida);
    }

}
