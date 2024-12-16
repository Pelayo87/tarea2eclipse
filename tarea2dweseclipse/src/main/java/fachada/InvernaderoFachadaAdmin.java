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
import com.dwes.util.Utilidades;
import servicioImpl.ServicioCredencialesImpl;
import servicioImpl.ServicioPersonaImpl;

public class InvernaderoFachadaAdmin {
	protected InvernaderoFachadaPrincipal facade;
	Scanner sc = new Scanner(System.in);

	InvernaderoServiciosFactory factoryServicios = InvernaderoServiciosFactory.getServicios();

	ServicioEjemplar S_ejemplar = factoryServicios.getServiciosEjemplar();
	ServicioPlanta S_planta = factoryServicios.getServiciosPlanta();
	ServicioMensaje S_mensaje = factoryServicios.getServiciosMensaje();
	ServicioCredenciales S_credenciales = factoryServicios.getServiciosCredenciales();
	ServicioPersona S_persona = factoryServicios.getServiciosPersona();
	
	ServicioPersonaImpl S_personaImpl;

	public InvernaderoFachadaAdmin(InvernaderoFachadaPrincipal facade) {
        this.facade = facade;
    }

	public void menuadmin() {
		int opcion = -1;
		System.out.println("\n\n\n\n\n\t\t\t\tADMINISTRADOR INVERNADERO" + " [Usuario actual:" + facade.nombreusuario + "]\n");
		System.out.println("\t\t\t\t1 - GESTIÓN DE PLANTAS");
		System.out.println("\t\t\t\t2 - GESTIÓN DE PERSONAS");
		System.out.println("\t\t\t\t3 - GESTIÓN DE EJEMPLARES");
		System.out.println("\t\t\t\t4 - GESTIÓN DE MENSAJES");
		System.out.println("\t\t\t\t5 - CERRAR SESIÓN");
		System.out.println("\t\t\t\t6 - SALIR DEL PROGRAMA");

		opcion = Utilidades.obtenerOpcionUsuario(6);

		switch (opcion) {
		case 1: {
			menuadminplantas();
			break;
		}
		case 2: {
			menuadminpersonas();
			break;
		}
		case 3: {
			facade.facadePersonal.gestionEjemplaresmenu();
		}
		case 4: {
			facade.facadePersonal.gestionMensajesmenu();
		}
		case 5: {
			facade.iniciosesion();
		}			
		case 6: {
			Utilidades.salirdelprograma();
		}
		}
	}

	public void menuadminplantas() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE PLANTAS" + " [Usuario actual:" + facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - AÑADIR PLANTA");
			System.out.println("\t\t\t\t2 - MODIFICAR PLANTA");
			System.out.println("\t\t\t\t3 - BORRAR PLANTA");
			System.out.println("\t\t\t\t4 - BUSCAR PLANTA POR ID");
			System.out.println("\t\t\t\t5 - BUSCAR PLANTA POR NOMBRE");
			System.out.println("\t\t\t\t6 - VOLVER ATRÁS");
			System.out.println("\t\t\t\t7 - CERRAR SESIÓN");
			System.out.println("\t\t\t\t8 - SALIR DEL PROGRAMA");
			

			opcion = Utilidades.obtenerOpcionUsuario(8);

			switch (opcion) {
			case 1: {
				S_planta.insertar(null);
				menuadminplantas();
			}
			case 2: {
				S_planta.modificar(null);
				menuadminplantas();
			}
			case 3: {
				S_planta.eliminar(null);
				menuadminplantas();
			}
			case 4:
                System.out.println("Introduce el código de la planta que quieres buscar:");
                String codigo = sc.nextLine().toUpperCase();
                Planta plantaById = S_planta.findById(codigo);
                if (plantaById != null) {
                    System.out.println("Planta encontrada: " + plantaById);
                } else {
                    System.out.println("No se encontró ninguna planta con el código: " + codigo);
                }
                menuadminplantas();
			case 5:
                System.out.println("Introduce el nombre común de la planta que quieres buscar:");
                String nombrecomun = sc.nextLine().toUpperCase();
                Planta plantaByNombre = S_planta.findByNombre(nombrecomun);
                if (plantaByNombre != null) {
                    System.out.println("Planta encontrada: " + plantaByNombre);
                } else {
                    System.out.println("No se encontró ninguna planta con el código: " + nombrecomun);
                }
                menuadminplantas();
			case 6: {
				menuadmin();
			}
			case 7: {
				facade.iniciosesion();
			}
			case 8: {
				Utilidades.salirdelprograma();
			}
			}
	}

	public void menuadminpersonas() {
		int opcion = -1;
		System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE PERSONAS" + " [Usuario actual:" + facade.nombreusuario + "]\n");
		System.out.println("\t\t\t\t1 - REGISTRAR PERSONA");
		System.out.println("\t\t\t\t2 - MODIFICAR PERSONA");
		System.out.println("\t\t\t\t3 - ELIMINAR PERSONA");
		System.out.println("\t\t\t\t4 - BUSCAR DATOS PERSONA POR ID");
		System.out.println("\t\t\t\t5 - BUSCAR DATOS PERSONA POR NOMBRE PERSONA");
		System.out.println("\t\t\t\t6 - VOLVER ATRÁS");
		System.out.println("\t\t\t\t7 - CERRAR SESIÓN");
		System.out.println("\t\t\t\t8 - SALIR DEL PROGRAMA");

		opcion = Utilidades.obtenerOpcionUsuario(8);

		switch (opcion) {
		case 1: {
			registrarPersona();
			menuadminpersonas();
		}
		case 2: {
			modificarPersona();
			menuadminpersonas();
		}
		case 3: {
			eliminarPersona();
			menuadminpersonas();
		}
		case 4: {
			System.out.println("Introduce el id de la persona de la que quieres ver los datos:");
            long idPersona = Long.parseLong(sc.nextLine());
            Persona personaById = S_persona.findById(idPersona);
            Credenciales credencialesById = S_credenciales.findByPersonaId(idPersona);
            if (personaById != null && credencialesById != null) {
            	System.out.println("\n--- DATOS DE LA PERSONA ---");
                System.out.println("ID:       " + personaById.getId());
                System.out.println("Nombre:   " + personaById.getNombre());
                System.out.println("Usuario:  " + credencialesById.getUsuario());
                System.out.println("Password: " + credencialesById.getPassword());
                System.out.println("Email:    " + personaById.getEmail());
            } else {
                System.out.println("No se encontró ninguna planta con el código: " + idPersona);
            }
            menuadminpersonas();
		}
		case 5: {
			System.out.println("Introduce el nombre de la persona de la que quieres ver los datos:");
            String nombrePersona = sc.nextLine().toUpperCase();
            Persona personaByNombre = S_persona.findByNombre(nombrePersona);
            long idPersona = personaByNombre.getId();
            Credenciales credencialesById = S_credenciales.findByPersonaId(idPersona);
            if (personaByNombre != null && credencialesById != null) {
            	System.out.println("\n--- DATOS DE LA PERSONA ---");
                System.out.println("ID:       " + personaByNombre.getId());
                System.out.println("Nombre:   " + personaByNombre.getNombre());
                System.out.println("Usuario:  " + credencialesById.getUsuario());
                System.out.println("Password: " + credencialesById.getPassword());
                System.out.println("Email:    " + personaByNombre.getEmail());
            } else {
                System.out.println("No se encontró ninguna planta con el código: " + idPersona);
            }
            menuadminpersonas();
		}
		case 6: {
			menuadmin();
		}
		case 7: {
			facade.iniciosesion();
		}
		case 8: {
			Utilidades.salirdelprograma();
		}
		}

	}

	// METODÓS PARA LA GESTIÓN DE PERSONAS

	private void registrarPersona() {
		// Guardo la persona y obtengo el ID generado
		int resultadoPersona = S_persona.insertar(null);

		if (resultadoPersona > 0) {
			Persona persona = new Persona(resultadoPersona);

			Credenciales credenciales = new Credenciales(null, null, persona);

			// Guardo las credenciales en la BD
			int resultadoCredenciales = S_credenciales.insertar(credenciales);

			if (resultadoCredenciales > 0) {
				System.out.println("Persona y credenciales registradas correctamente.");
			} else {
				System.err.println("Error al registrar las credenciales.");
			}
		}
	}

	private void modificarPersona() {
		//Modifico la persona y obtengo el ID generado
		int resultadomodificarPersona = S_persona.modificar(null);

		if (resultadomodificarPersona > 0) {
			Persona persona = new Persona(resultadomodificarPersona);
			Credenciales credencialesModificadas = new Credenciales(null, null, persona);
			
			//Modifico las credenciales en la BD
			int resultadomodificarCredenciales = S_credenciales.modificar(credencialesModificadas);

			if (resultadomodificarCredenciales > 0) {
				System.out.println("Persona y credenciales modificadas correctamente.");
			} else {
				System.err.println("Error al modificar la persona o sus credenciales.");
			}
		}else {
			System.err.println("Error al modificar la persona.");
		}
	}


	private void eliminarPersona() {
	    System.out.println("Introduce el id de la persona que quieres eliminar:");
	    try {
	        long idPersona = Long.parseLong(sc.nextLine());
	        Persona personaById = S_persona.findById(idPersona);

	        if (personaById != null) {
	            System.out.println("¿Estás seguro de que quieres eliminar a la persona? (S/N):");
	            String confirmacion = sc.nextLine().toUpperCase();

	            if (confirmacion.equals("S")) {
	                // Eliminar primero las credenciales si existen
	                Credenciales credencialesById = S_credenciales.findByPersonaId(idPersona);
	                if (credencialesById != null) {
	                    S_credenciales.eliminar(credencialesById);
	                }
	                // Eliminar la persona
	                int resultado = S_persona.eliminar(personaById);

	                if (resultado > 0) {
	                    System.out.println("Persona eliminada correctamente.");
	                } else {
	                    System.err.println("Error al eliminar la persona.");
	                }
	            } else {
	                System.out.println("Eliminación cancelada.");
	            }
	        } else {
	            System.out.println("No se encontró ninguna persona con el ID: " + idPersona);
	        }
	    } catch (NumberFormatException e) {
	        System.out.println("Error: ID inválido. Introduce un número válido.");
	    }
	}
	
	/*private void buscarPersonaPorInicial() {
		System.out.println("Introduce la inicial por la que quieres buscar personas:");
		String inicial = sc.nextLine().toUpperCase();
		if(inicial.length() == 1) {
			java.util.List<Persona> personas = S_persona.findByInicial(inicial);
			if(personas != null && !personas.isEmpty()) {
				System.out.println("\nPersonas encontradas que empiezan por '" + inicial + "':");
				for(Persona p : personas) {
					System.out.println("ID: " + p.getId() + " - Nombre: " + p.getNombre() + " - Email: " + p.getEmail());
				}
			} else {
				System.out.println("No se encontraron personas que empiecen por la letra '" + inicial + "'");
			}
		} else {
			System.out.println("Por favor, introduce una única letra.");
		}
	}*/
}
