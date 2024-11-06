package principal;

import fachada.InvernaderoFachadaPrincipal;

public class Principal {
    
    public static void main(String[] args) {
        InvernaderoFachadaPrincipal facade = new InvernaderoFachadaPrincipal();
        
        System.out.println("--Bienvenido al sistema del invernadero (DWES)--");
        
        facade.iniciosesion();
    }
}



