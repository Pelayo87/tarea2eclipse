package fachada;

import java.util.Scanner;
import com.dwes.modelo.Credenciales;
import com.dwes.modelo.Persona;
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
				S_planta.insertar(null);
				break;
			}
			case 2: {
				S_planta.modificar(null);
				break;
			}
			case 3: {
				S_planta.eliminar(null);
				break;
			}
			case 4: {
				facade.iniciosesion();
				;
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

	// METODÓS PARA LA GESTIÓN DE PERSONAS

	private void registrarPersona() {
	    //Guardo la persona y obtengo el ID generado
	    int resultadoPersona = S_persona.insertar(null);

	    if (resultadoPersona > 0) {
	        Persona persona = new Persona(resultadoPersona);

	        Credenciales credenciales = new Credenciales(null, null, persona);

	        //Guardo las credenciales en la BD
	        int resultadoCredenciales = S_credenciales.insertar(credenciales);

	        if (resultadoCredenciales > 0) {
	            System.out.println("Persona y credenciales registradas correctamente.");
	        } else {
	            System.err.println("Error al registrar las credenciales.");
	        }
	}
  }
}
