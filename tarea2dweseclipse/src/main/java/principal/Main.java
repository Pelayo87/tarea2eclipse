package principal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlDataSource;

import modelo.Planta;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        System.out.println("INICIO");
        
        Scanner in = new Scanner(System.in);
        System.out.println("Dame el codigo de la nueva planta:");
        String codigo = in.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre común de la planta:");
        String nombrecomun = in.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre científico de la planta:");
        String nombrecientifico = in.nextLine().trim().toUpperCase();
        
        Planta nuevaplanta = new Planta(codigo,nombrecomun,nombrecientifico);
        
        Connection con;
        MysqlDataSource msds=new MysqlDataSource();
        Properties prop = null;
        FileInputStream fis;
        
        String url;
        String user;
        String password;
        
        try {
            fis = new FileInputStream("src/main/resources/db.properties");
            prop.load(fis);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            msds.setUrl(url);
            msds.setUser(user);
            msds.setPassword(password);
            
            con = msds.getConnection();
            
            String sql = "insert into PLANTAS(codigo,nombrecomun,nombrecientifico) VALUES('" 
                         + nuevaplanta.getCodigo() + "', '" 
                         + nuevaplanta.getNombrecomun() + "', '" 
                         + nuevaplanta.getNombrecientifico() + "')";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            
            con.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo de configuración 'db.properties'.");
            e.printStackTrace();
            
        } catch (IOException e) {
            System.out.println("Error: Ocurrió un problema al leer el archivo de configuración.");
            e.printStackTrace();
            
        } catch (SQLException e) {
            System.out.println("Error: No se pudo establecer la conexión con la base de datos o se produjo un error en la ejecución de la consulta.");
            e.printStackTrace();
        }

        
        
        //String url = "jdbc:mysql://localhost:3306/tarea2dwes";
        // String user="root";
 	    //String password="";
 	    
        
        System.out.println("FIN");
        
    }
}
