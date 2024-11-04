package principal;

import fachada.InvernaderoFachada;

public class Principal {
    
	public static void main(String[] args) {
        InvernaderoFachada facade = new InvernaderoFachada();
        
        System.out.println("--Bienvenido al sistema del invernadero (DWES)--");
        
        facade.iniciosesion();
    }
}


