package principal;

import java.time.LocalDate;

import com.dwes.util.MySqlDAOFactory;

import fachada.InvernaderoFachadaPrincipal;

public class Principal {
    
    public static void main(String[] args) {
    	String borde = "\033[1;35m************************************************\033[0m";
    	String titulo = "\033[1;36mTarea 2 DWES (Examen/Prueba Autoria)\033[0m";
        String autor = "\033[1;33mAutor: Pelayo Rodríguez Álvarez\033[0m";
        String fecha = String.format("\033[1;32mFecha: %-38s\033[0m", LocalDate.now().toString());
        String portadatitulo = "\033[1;36m-- Bienvenido al Sistema del Invernadero (DWES) --\033[0m";

        System.out.println("\n\n\n\n\n\t\t\t\t" + borde);
        System.out.println("\t\t\t\t* " + titulo + "         *");
        System.out.println("\t\t\t\t* " + autor + "              *");
        System.out.println("\t\t\t\t* " + fecha + "*");
        System.out.println("\t\t\t\t" + borde);
        System.out.println("\n\n\t\t\t\t" + borde);
        System.out.println("\t\t\t\t" + portadatitulo);
        System.out.println("\t\t\t\t" + borde);
		
    	MySqlDAOFactory.getConexion();
        InvernaderoFachadaPrincipal facade = new InvernaderoFachadaPrincipal();
        
        facade.iniciosesion();       
    }
}



