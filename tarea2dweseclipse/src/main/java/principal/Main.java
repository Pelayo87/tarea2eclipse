package principal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import conexion.ConexionBD;
import modelo.Planta;

public class Main {
    public static void main(String[] args) {
        System.out.println("INICIO");
        
        // Recoger datos de entrada del usuario
        Scanner in = new Scanner(System.in);
        System.out.println("Dame el código de la nueva planta:");
        String codigo = in.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre común de la planta:");
        String nombrecomun = in.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre científico de la planta:");
        String nombrecientifico = in.nextLine().trim().toUpperCase();
        
        Planta nuevaplanta = new Planta(codigo, nombrecomun, nombrecientifico);
        
        // Conexión a la base de datos
        try (Connection con = ConexionBD.getConexion()) {
            String sql2 = "INSERT INTO plantas (codigo, nombrecomun, nombrecientifico) VALUES (?, ?, ?)";
            
            try (PreparedStatement ps = con.prepareStatement(sql2)) {
                ps.setString(1, nuevaplanta.getCodigo());
                ps.setString(2, nuevaplanta.getNombrecomun());
                ps.setString(3, nuevaplanta.getNombrecientifico());
                ps.execute();
                System.out.println("Planta insertada correctamente.");
            }
        } catch (IOException e) {
            System.out.println("Error: Problema al leer el archivo de configuración.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: Problema al conectar o insertar en la base de datos.");
            e.printStackTrace();
        }
        
        System.out.println("FIN");
    }
}
