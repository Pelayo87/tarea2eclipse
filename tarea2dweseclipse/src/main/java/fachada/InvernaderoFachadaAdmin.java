package fachada;

import java.util.Scanner;
import com.dwes.modelo.Credenciales;
import com.dwes.modelo.Persona;
import com.dwes.modelo.Planta;
import com.dwes.servicios.ServicioCredenciales;
import com.dwes.servicios.ServicioEjemplar;
import com.dwes.servicios.ServicioMensaje;
import com.dwes.servicios.ServicioPersona;
import com.dwes.servicios.ServicioPlanta;
import com.dwes.util.InvernaderoServiciosFactory;

public class InvernaderoFachadaAdmin {
	private InvernaderoFachadaPrincipal facade;
	Scanner sc = new Scanner(System.in);
	String nombreusuario;
    Persona usuarioActual;
    
    InvernaderoServiciosFactory factoryServicios = InvernaderoServiciosFactory.getServicios();

    ServicioEjemplar S_ejemplar = factoryServicios.getServiciosEjemplar();
    ServicioPlanta S_planta = factoryServicios.getServiciosPlanta();
    ServicioMensaje S_mensaje = factoryServicios.getServiciosMensaje();
    ServicioCredenciales S_credenciales = factoryServicios.getServiciosCredenciales();
    ServicioPersona S_persona = factoryServicios.getServiciosPersona();
    
    public InvernaderoFachadaAdmin(InvernaderoFachadaPrincipal facade) {
        this.facade = facade;
    }
       
	
	public void menuadmin() {
        int opcion = -1;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tADMINISTRADOR INVERNADERO\n");
            System.out.println("\t\t\t\t1 - GESTIÓN DE PLANTAS");
            System.out.println("\t\t\t\t2 - GESTIÓN DE PERSONAS");
            System.out.println("\t\t\t\t3 - SALIR");

            opcion = facade.obtenerOpcionUsuario(3);

            switch (opcion) {
                case 1: {
                	menuadminplantas();
                    break;
                }
                case 2: {
                	menuadminpersonas();
                    break;
                }
            }
        } while (opcion != 3);
    }
	
	 public void menuadminplantas() {
	        int opcion = -1;
	        do {
	            System.out.println("\n\n\n\n\n\t\t\t\tADMINISTRADOR VIVERO: PLANTAS\n");
	            System.out.println("\t\t\t\t1 - AÑADIR PLANTA");
	            System.out.println("\t\t\t\t2 - MODIFICAR PLANTA");
	            System.out.println("\t\t\t\t3 - BORRAR PLANTA");
	            System.out.println("\t\t\t\t4 - CERRAR SESIÓN");
	            System.out.println("\t\t\t\t5 - VOLVER ATRÁS");

	            opcion = facade.obtenerOpcionUsuario(5);

	            switch (opcion) {
	                case 1: {
	                    anadirPlanta();
	                    break;
	                }
	                case 2: {
	                    modificarPlanta();
	                    break;
	                }
	                case 3: {
	                    eliminarPlanta();
	                    break;
	                }
	                case 4: {
	                    facade.iniciosesion();;
	                    break;
	                }
	            }
	        } while (opcion != 5);
	    }
	 
	 public void menuadminpersonas() {
	        int opcion = -1;
	        do {
	            System.out.println("\n\n\n\n\n\t\t\t\tADMINISTRADOR VIVERO: PERSONAS\n");
	            System.out.println("\t\t\t\t1 - REGISTRAR PERSONA");
	            System.out.println("\t\t\t\t2 - VOLVER ATRÁS");

	            opcion = facade.obtenerOpcionUsuario(2);

	            switch (opcion) {
	                case 1: {
	                    	registrarPersona();
	                    break;
	                }
	            }
	        } while (opcion != 2);
	    }
	 
	 
	 
	 //METODOS PARA GESTIÓN DE PLANTAS
	 private void anadirPlanta() {
	        System.out.println("Dame el código de la nueva planta:");
	        String codigo = sc.nextLine().trim().toUpperCase();
	        System.out.println("Dame el nombre común de la planta:");
	        String nombrecomun = sc.nextLine().trim().toUpperCase();
	        System.out.println("Dame el nombre científico de la planta:");
	        String nombrecientifico = sc.nextLine().trim().toUpperCase();

	        Planta nuevaplanta = new Planta(codigo, nombrecomun, nombrecientifico);

	        S_planta.insertar(nuevaplanta);
	    }

	    private void modificarPlanta() {
	        System.out.println("Dame el código de la planta a modificar:");
	        String codigo = sc.nextLine().trim().toUpperCase();
	        System.out.println("Dame el nombre común de la planta:");
	        String nombrecomun = sc.nextLine().trim().toUpperCase();
	        System.out.println("Dame el nombre científico de la planta:");
	        String nombrecientifico = sc.nextLine().trim().toUpperCase();

	        Planta cambioplanta = new Planta(codigo, nombrecomun, nombrecientifico);

	        S_planta.modificar(cambioplanta);
	    }

	    private void eliminarPlanta() {
	        System.out.println("Dame el código de la planta a eliminar:");
	        String codigo = sc.nextLine().trim().toUpperCase();

	        Planta eliminarplanta = new Planta(codigo, null, null);

	        S_planta.eliminar(eliminarplanta);
	    }
	    
	    
	    //METODÓS PARA LA GESTIÓN DE PERSONAS
	    
	    private void registrarPersona() {
	        System.out.println("Introduce tu nombre real:");
	        String nombrePersona = sc.nextLine().trim();
	        System.out.println("Introduce tu email (correo electrónico):");
	        String emailPersona = sc.nextLine().trim().toLowerCase();
	        System.out.println("Introduce un nombre de usuario:");
	        String nombreUsuario = sc.nextLine().trim();
	        System.out.println("Introduce una contraseña:");
	        String password = sc.nextLine().trim();

	        // Crear instancia de Persona
	        Persona nuevaPersona = new Persona();
	        nuevaPersona.setNombre(nombrePersona);
	        nuevaPersona.setEmail(emailPersona);
	        
	        System.out.println(nuevaPersona);

	        int resultadoPersona = S_persona.insertar(nuevaPersona);

	        if (resultadoPersona > 0) {
	            // Crear una nueva instancia de Persona solo con el ID
	            Persona personaId = new Persona();
	            personaId.setId(resultadoPersona);
	            Credenciales credenciales = new Credenciales();
	            credenciales.setUsuario(nombreUsuario);
	            credenciales.setPassword(password);
	            credenciales.setPersona(personaId);

	            // Guardar las credenciales en la base de datos
	            int resultadoCredenciales = S_credenciales.insertar(credenciales);

	            if (resultadoCredenciales > 0) {
	                System.out.println("Persona y credenciales registradas exitosamente.");
	            } else {
	                System.err.println("Ocurrió un error al registrar las credenciales.");
	            }
	        } else {
	            System.err.println("Ocurrió un error al registrar la persona.");
	        }
	    }
}
