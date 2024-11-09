package principal;

import com.dwes.util.MySqlDAOFactory;

import fachada.InvernaderoFachadaPrincipal;

public class Principal {
    
    public static void main(String[] args) {
    	MySqlDAOFactory factory = MySqlDAOFactory.getConexion();
        InvernaderoFachadaPrincipal facade = new InvernaderoFachadaPrincipal();
        
        System.out.println("--Bienvenido al sistema del invernadero (DWES)--");
        
        facade.iniciosesion();       
    }
}



