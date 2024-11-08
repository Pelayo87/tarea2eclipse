package fachada;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.dwes.modelo.Credenciales;
import com.dwes.modelo.Persona;
import com.dwes.servicios.ServicioCredenciales;
import com.dwes.servicios.ServicioEjemplar;
import com.dwes.servicios.ServicioMensaje;
import com.dwes.servicios.ServicioPersona;
import com.dwes.servicios.ServicioPlanta;
import com.dwes.util.InvernaderoServiciosFactory;
import servicioImpl.ServicioCredencialesImpl;
import servicioImpl.ServicioPersonaImpl;

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
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tINICIO DE SESIÓN O REGISTRARSE\n");
            System.out.println("\t\t\t\t1 - LOGIN");
            System.out.println("\t\t\t\t2 - ENTRAR COMO INVITADO");
            System.out.println("\t\t\t\t3 - SALIR");

            opcion = obtenerOpcionUsuario(3);

            switch (opcion) {
                case 1: {
                    login();
                    break;
                }
                case 2: {
                    facadeInvitado.invitado();
                    break;
                }
            }
        } while (opcion != 3);
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
                System.out.println("Inicio de sesión exitoso.");
                
                if ("admin".equalsIgnoreCase(nombreusuario) && "admin".equals(contrasena)) {
                    System.out.println("Inicio de sesión exitoso como administrador.");
                    facadeAdmin.menuadmin();
                } else {                    
                    facadePersonal.menu();
                }
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }


    protected int obtenerOpcionUsuario(int maxOpcion) {
        int opcion = -1;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    sc.nextLine();
                    if (opcion >= 1 && opcion <= maxOpcion) {
                        entradaValida = true;
                    } else {
                        System.err.println("Por favor, ingresa un número entre 1 y " + maxOpcion + ".");
                    }
                } else {
                    System.err.println("Entrada no válida. Por favor, ingresa un número entre 1 y " + maxOpcion + ".");
                    sc.next();
                }
            } catch (InputMismatchException e) {
                System.err.println("Error de entrada. Inténtalo de nuevo.");
                sc.next();
            }
        }
        return opcion;
    }
}

