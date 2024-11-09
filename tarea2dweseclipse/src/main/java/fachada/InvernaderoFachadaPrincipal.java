package fachada;

import java.util.Scanner;
import com.dwes.modelo.Credenciales;
import com.dwes.servicios.ServicioCredenciales;
import com.dwes.servicios.ServicioEjemplar;
import com.dwes.servicios.ServicioMensaje;
import com.dwes.servicios.ServicioPersona;
import com.dwes.servicios.ServicioPlanta;
import com.dwes.util.InvernaderoServiciosFactory;
import com.dwes.util.Utilidades;
import servicioImpl.ServicioCredencialesImpl;

public class InvernaderoFachadaPrincipal {
    private InvernaderoFachadaAdmin facadeAdmin;
    private InvernaderoFachadaPersonal facadePersonal;
    private InvernaderoFachadaInvitado facadeInvitado;
    Scanner sc = new Scanner(System.in);
    String nombreusuario;
    public long id_Persona;
    
    InvernaderoServiciosFactory factoryServicios = InvernaderoServiciosFactory.getServicios();

    ServicioEjemplar S_ejemplar = factoryServicios.getServiciosEjemplar();
    ServicioPlanta S_planta = factoryServicios.getServiciosPlanta();
    ServicioMensaje S_mensaje = factoryServicios.getServiciosMensaje();
    ServicioCredenciales S_credenciales = factoryServicios.getServiciosCredenciales();
    ServicioPersona S_persona = factoryServicios.getServiciosPersona();

    public InvernaderoFachadaPrincipal() {
    	this.facadeAdmin = new InvernaderoFachadaAdmin(this);
        this.facadePersonal = new InvernaderoFachadaPersonal(this);
        this.facadeInvitado = new InvernaderoFachadaInvitado(this);
    }

	public void iniciosesion() {
		int opcion = -1;
		System.out.println("\n\n\n\n\n\t\t\t\tINICIO DE SESIÓN O REGISTRARSE\n");
		System.out.println("\t\t\t\t1 - LOGIN");
		System.out.println("\t\t\t\t2 - ENTRAR COMO INVITADO");
		System.out.println("\t\t\t\t3 - SALIR");

		opcion = Utilidades.obtenerOpcionUsuario(3);

		switch (opcion) {
		case 1: {
			login();
			break;
		}
		case 2: {
			facadeInvitado.invitado();
			break;
		}
		case 3: {
			Utilidades.salirdelprograma();
		}
		}
	}
    
    public void login() {
        System.out.println("Nombre de usuario:");
        nombreusuario = sc.nextLine().trim();
        System.out.println("Contraseña (password):");
        String contrasena = sc.nextLine().trim();
        
        Credenciales credencialesIngresadas = new Credenciales(nombreusuario, contrasena);
        ServicioCredencialesImpl servicioCredenciales = new ServicioCredencialesImpl();
        boolean autenticado = servicioCredenciales.autenticar(credencialesIngresadas);
        
        
        if (autenticado) {
            Credenciales credencialesAutenticadas = servicioCredenciales.findByUsuario(nombreusuario);
            id_Persona = credencialesAutenticadas.getId_persona();
            if (credencialesAutenticadas != null) {
                if ("admin".equalsIgnoreCase(nombreusuario) && "admin".equals(contrasena)) {
                    System.out.println("Inicio de sesión exitoso como administrador.");
                    facadeAdmin.menuadmin();
                } else {     
                	System.out.println("Inicio de sesión exitoso.");
                    facadePersonal.menu();
                }
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
            iniciosesion();
        }
    }
}