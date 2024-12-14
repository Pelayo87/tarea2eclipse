package principal;

import java.time.LocalDate;

import com.dwes.util.MySqlDAOFactory;

import fachada.InvernaderoFachadaPrincipal;

public class Principal {
    
    public static void main(String[] args) {
    	System.out.println("\n\n\n\n\n\t\t\t\t************************************************");
		System.out.println("\t\t\t\t* Tarea 2 DWES (Examen/Prueba Autoria)         *");
		System.out.println("\t\t\t\t* Autor: Pelayo Rodríguez Álvarez              *");
		System.out.printf("\t\t\t\t* Fecha: %-38s*\n", LocalDate.now().toString());
		System.out.println("\t\t\t\t************************************************");
		
    	MySqlDAOFactory factory = MySqlDAOFactory.getConexion();
        InvernaderoFachadaPrincipal facade = new InvernaderoFachadaPrincipal();
        
        System.out.println("\n\n\n\t\t\t\t--Bienvenido al sistema del invernadero (DWES)--");
        
        facade.iniciosesion();       
    }
}



