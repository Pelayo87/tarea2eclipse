package com.dwes.util;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class Utilidades {

    public static Date leerFecha() {
        Scanner sc = new Scanner(System.in, "ISO-8859-1");
        Date ret = null;
        boolean correcto = false;

        while (!correcto) {
            try {
                System.out.println("Introduzca un valor para el día (1...31):");
                int dia = sc.nextInt();
                
                System.out.println("Introduzca un valor para el mes (1...12):");
                int mes = sc.nextInt();
                
                System.out.println("Introduzca un valor para el año:");
                int anio = sc.nextInt();
                
                ret = Date.valueOf(LocalDate.of(anio, mes, dia));
                correcto = true;
            } catch (Exception e) {
                System.out.println("Fecha introducida incorrecta. Intente nuevamente.");
                sc.nextLine();
            }
        }

        return ret;
    }
}

