package fachada;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import com.dwes.modelo.Ejemplar;
import com.dwes.modelo.Mensaje;
import com.dwes.modelo.Persona;
import com.dwes.modelo.Planta;
import com.dwes.servicios.ServicioCredenciales;
import com.dwes.servicios.ServicioEjemplar;
import com.dwes.servicios.ServicioMensaje;
import com.dwes.servicios.ServicioPersona;
import com.dwes.servicios.ServicioPlanta;
import com.dwes.util.InvernaderoServiciosFactory;
import com.dwes.util.Utilidades;

import servicioImpl.ServicioMensajeImpl;

public class InvernaderoFachadaPersonal {
	protected InvernaderoFachadaPrincipal facade;
	Scanner sc = new Scanner(System.in);
	Persona usuarioActual;

	// FECHA ACTUAL Y FORMATEADA
	Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	String fechaFormateada = formatoFecha.format(fechaActual);

	InvernaderoServiciosFactory factoryServicios = InvernaderoServiciosFactory.getServicios();

	ServicioEjemplar S_ejemplar = factoryServicios.getServiciosEjemplar();
	ServicioPlanta S_planta = factoryServicios.getServiciosPlanta();
	ServicioMensaje S_mensaje = factoryServicios.getServiciosMensaje();
	ServicioCredenciales S_credenciales = factoryServicios.getServiciosCredenciales();
	ServicioPersona S_persona = factoryServicios.getServiciosPersona();

	public InvernaderoFachadaPersonal(InvernaderoFachadaPrincipal facade) {
	    this.facade = facade;
	}

	public void menu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tPERSONAL VIVERO" + " [Usuario actual:" +facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - GESTIÓN DE EJEMPLARES");
			System.out.println("\t\t\t\t2 - GESTIÓN DE MENSAJES");
			System.out.println("\t\t\t\t3 - CERRAR SESIÓN");
			System.out.println("\t\t\t\t4 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(4);

			switch (opcion) {
			case 1: {
				gestionEjemplaresmenu();
				break;
			}
			case 2: {
				gestionMensajesmenu();
				break;
			}
			case 3: {
				facade.iniciosesion();
			}
			case 4: {
				Utilidades.salirdelprograma();
			}
			}
	}
	
	public void gestionEjemplaresmenu() {
		int opcion = -1;
		System.out.println("\n\n\n\n\n\t\t\t\tGESTIÓN DE EJEMPLARES" + " [Usuario actual:" + facade.nombreusuario + "]\n");
		System.out.println("\t\t\t\t1 - AÑADIR NUEVO EJEMPLAR");
		System.out.println("\t\t\t\t2 - FILTRAR EJEMPLAR/ES");
		System.out.println("\t\t\t\t3 - VER MENSAJES DE UN EJEMPLAR");
		System.out.println("\t\t\t\t4 - VOLVER ATRÁS");
		System.out.println("\t\t\t\t5 - CERRAR SESIÓN");
		System.out.println("\t\t\t\t6 - SALIR DEL PROGRAMA");

		opcion = Utilidades.obtenerOpcionUsuario(6);

		switch (opcion) {
		case 1: {
			registrarEjemplar();
			break;
		}
		case 2: {
			filtrarEjemplaresmenu();
			break;
		}
		case 3: {
			vermensajesEjemplar();
			break;
		}
		case 4: {
			if ("admin".equalsIgnoreCase(facade.nombreusuario)) {
				facade.facadeAdmin.menuadmin();
			} else {
				menu();
			}
		}
		case 5: {
			facade.iniciosesion();
		}
		case 6: {
			Utilidades.salirdelprograma();
		}
		}
	}

	public void filtrarEjemplaresmenu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tFILTRAR EJEMPLARES" + " [Usuario actual:" +facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - MOSTRAR TODOS LOS EJEMPLARES");
			System.out.println("\t\t\t\t2 - FILTRAR EJEMPLAR/ES POR TIPO/S DE PLANTA/S");
			System.out.println("\t\t\t\t3 - VOLVER ATRÁS");
			System.out.println("\t\t\t\t4 - SALIR DEL PROGRAMA");			

			opcion = Utilidades.obtenerOpcionUsuario(4);

			switch (opcion) {
			case 1: {
				filtrarEjemplarestodos();
				break;
			}
			case 2: {
				filtrarEjemplarestipoplanta();
				break;
			}
			case 3: {
				gestionEjemplaresmenu();
			}
			case 4: {
				Utilidades.salirdelprograma();
			}
			}
	}

	public void gestionMensajesmenu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tGESTION DE MENSAJES/ANOTACIONES" + " [Usuario actual:" +facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - REALIZAR ANOTACIONES EN FORMA DE MENSAJES");
			System.out.println("\t\t\t\t2 - MOSTRAR TODOS LOS MENSAJES/ANOTACIONES");
			System.out.println("\t\t\t\t3 - FILTRAR ANOTACIONES/MENSAJES");
			System.out.println("\t\t\t\t4 - VOLVER ATRÁS");
			System.out.println("\t\t\t\t5 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(5);

			switch (opcion) {
			case 1: {
				realizarAnotaciones();
				break;
			}
			case 2: {
				mostrarAnotaciones();
				break;
			}
			case 3: {
				filtrarAnotacionesmenu();
			}
			case 4: {
				if ("admin".equalsIgnoreCase(facade.nombreusuario)) {
					facade.facadeAdmin.menuadmin();
				} else {
					menu();
				}
			}
			case 5: {
				Utilidades.salirdelprograma();
			}
			}
	}

	public void filtrarAnotacionesmenu() {
		int opcion = -1;
			System.out.println("\n\n\n\n\n\t\t\t\tFILTRAR MENSAJES/ANOTACIONES" + " [Usuario actual:" +facade.nombreusuario + "]\n");
			System.out.println("\t\t\t\t1 - FILTRAR POR PERSONA QUE LO ESCRIBIO");
			System.out.println("\t\t\t\t2 - FILTRAR POR RANGO DE FECHA");
			System.out.println("\t\t\t\t3 - FILTRAR POR TIPO DE PLANTA");
			System.out.println("\t\t\t\t4 - VOLVER ATRÁS");
			System.out.println("\t\t\t\t5 - SALIR DEL PROGRAMA");
			

			opcion = Utilidades.obtenerOpcionUsuario(5);

			switch (opcion) {
			case 1: {
				filtrarAnotacionesporPersona();
				break;
			}
			case 2: {
				ServicioMensajeImpl.filtrarAnotacionesporRangoFecha();
				break;
			}
			case 3: {
				filtrarAnotacionesporTipoPlanta();
			}
			case 4: {
				gestionMensajesmenu();
			}
			case 5: {
				Utilidades.salirdelprograma();
			}			
			}
	}

	// MÉTODOS PARA LA GESTIÓN DE EJEMPLARES

	private void registrarEjemplar() {
		Set<Planta> tiposPlantas = S_planta.find();
		if (tiposPlantas.isEmpty()) {
			System.out.println("No hay tipos de plantas disponibles en el sistema.");
			gestionEjemplaresmenu();
		}
		System.out.println("Selecciona el tipo de planta:");
		int index = 1;
		for (Planta planta : tiposPlantas) {
			System.out.println(index + " - " + planta.getNombrecomun());
			index++;
		}
		try {
			// Obtener la selección del usuario
			int seleccion = sc.nextInt();
			if (seleccion < 1 || seleccion > tiposPlantas.size()) {
				System.out.println("Selección no válida.");
				return;
			}
			// Obtener la planta seleccionada
			Planta plantaElegida = (Planta) tiposPlantas.toArray()[seleccion - 1];
			System.out.println("Planta seleccionada: " + plantaElegida.getNombrecomun());
			String codigoPlanta = plantaElegida.getCodigo();
			// Crear instancia de Ejemplar
			Ejemplar nuevoEjemplar = new Ejemplar();
			nuevoEjemplar.setNombre("");
			nuevoEjemplar.setCodigo(codigoPlanta);
			// Guardo el ejemplar en la BD
			S_ejemplar.insertar(nuevoEjemplar);

			// Recuperar todos los ejemplares para encontrar el último ID
			Set<Ejemplar> mostrarEjemplares = S_ejemplar.findAll();
			long idEjemplar = -1;
			for (Ejemplar ejemplar : mostrarEjemplares) {
				if (ejemplar.getId() > idEjemplar) {
					idEjemplar = ejemplar.getId();
				}
			}
			if (idEjemplar <= 0) {
				System.err.println("Error: No se pudo obtener el ID del ejemplar registrado.");
				gestionEjemplaresmenu();
			}

			String nombreEjemplar = codigoPlanta + "_" + idEjemplar;
			//System.out.println(idEjemplar);

			Ejemplar cambioejemplar = new Ejemplar(idEjemplar, nombreEjemplar, codigoPlanta);
			// Modifico el ejemplar en la BD
			S_ejemplar.modificar(cambioejemplar);

			// Creo el mensaje inicial para el ejemplar registrado
			Mensaje mensajeInicial = new Mensaje();
			mensajeInicial.setFechahora(fechaActual);
			mensajeInicial.setMensaje("Registro realizado por " + facade.nombreusuario + " el " + fechaFormateada);
			mensajeInicial.setId_ejemplar(idEjemplar);
			mensajeInicial.setId_persona(facade.id_Persona);
			// Guardo el mensaje en la BD
			int resultadoMensaje = S_mensaje.insertar(mensajeInicial);
			if (resultadoMensaje > 0) {
				System.out.println("Ejemplar y mensaje inicial registrados exitosamente.");
				gestionEjemplaresmenu();
			} else {
				System.err.println("Ocurrió un error al registrar el mensaje.");
			}
		} catch (InputMismatchException e) {
			System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}

	private void vermensajesEjemplar() {
		Set<Ejemplar> ejemplares = S_ejemplar.findAll();
		if (ejemplares.isEmpty()) {
			System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
			gestionEjemplaresmenu();
		}

		System.out.println("Selecciona el ejemplar del que deseas ver los mensajes:");
		int index = 1;
		for (Ejemplar e : ejemplares) {
			System.out.println(index + " - " + e.getNombre());
			index++;
		}
		try {
			// Obtener la selección del usuario
			int seleccion = sc.nextInt();
			if (seleccion < 1 || seleccion > ejemplares.size()) {
				System.err.println("Selección no válida.");
				return;
			}

			// Obtener el ejemplar seleccionado
			Ejemplar ejemplarElegido = (Ejemplar) ejemplares.toArray()[seleccion - 1];
			long idEjemplar = ejemplarElegido.getId();

			// Obtener los mensajes relacionados con el ejemplar seleccionado
			Set<Mensaje> mensajes = S_mensaje.findByEjemplarId(idEjemplar);

			if (mensajes.isEmpty()) {
				System.out.println("No hay mensajes para el ejemplar seleccionado.");
				gestionEjemplaresmenu();
			} else {
				System.out.println("Mensajes para el ejemplar " + ejemplarElegido.getNombre() + ":");
				for (Mensaje mensaje : mensajes) {
					System.out.println("Fecha y hora: " + mensaje.getFechahora());
					System.out.println("Mensaje: " + mensaje.getMensaje());
					System.out.println("-------------------------");
				}
				gestionEjemplaresmenu();
			}
		} catch (InputMismatchException e) {
			System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}

	private void filtrarEjemplarestodos() {
		Set<Ejemplar> ejemplares = S_ejemplar.findAll();

		if (ejemplares.isEmpty()) {
			System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
			filtrarEjemplaresmenu();
		}

		for (Ejemplar e : ejemplares) {
			System.out.println(e.toString());
		}
		
		filtrarEjemplaresmenu();

	}

	private void filtrarEjemplarestipoplanta() {
	    Set<Planta> plantas = S_planta.find();
	    if (plantas.isEmpty()) {
	        System.out.println("No hay plantas disponibles en el sistema.");
	        return;
	    }

	    System.out.println("Selecciona el tipo de planta que deseas ver sus ejemplares (puedes seleccionar varias separando con comas):");
	    int index = 1;

	    // Mostrar lista de plantas disponibles
	    for (Planta p : plantas) {
	        System.out.println(index + " - " + p.getNombrecomun());
	        index++;
	    }

	    try {
	        String input = sc.nextLine();
	        String[] selecciones = input.split(",");

	        Set<String> codigosPlantaSeleccionados = new HashSet<>();

	        // Validar y obtener los códigos de planta seleccionados
	        for (String sel : selecciones) {
	            int seleccion = Integer.parseInt(sel.trim());
	            if (seleccion < 1 || seleccion > plantas.size()) {
	                System.out.println("Selección no válida. Inténtalo de nuevo.");
	                filtrarEjemplarestipoplanta();
	            }
	            Planta plantaSeleccionada = (Planta) plantas.toArray()[seleccion - 1];
	            codigosPlantaSeleccionados.add(plantaSeleccionada.getCodigo());
	        }

	        // Buscar ejemplares de todos los tipos de plantas seleccionados
	        List<Ejemplar> ejemplaresFiltrados = new ArrayList<>();
	        for (String codigoPlanta : codigosPlantaSeleccionados) {
	            ejemplaresFiltrados.addAll(S_ejemplar.findByPlanta(codigoPlanta));
	        }

	        // Mostrar resultados
	        if (ejemplaresFiltrados.isEmpty()) {
	            System.out.println("No hay ejemplares para los tipos de plantas seleccionados.");
	        } else {
	            System.out.println("\nEjemplares encontrados:");
	            for (Ejemplar ejemplar : ejemplaresFiltrados) {
	                System.out.println(ejemplar.toString());
	            }
	        }

	        filtrarEjemplaresmenu(); 
	    } catch (NumberFormatException e) {
	        System.err.println("Error: Entrada inválida. Solo se permiten números separados por comas.");
	        filtrarEjemplarestipoplanta();
	    } catch (InputMismatchException e) {
	        System.err.println("Error: Entrada inválida. Inténtalo de nuevo.");
	        filtrarEjemplarestipoplanta();
	    }
	}


	// MÉTODOS PARA LA GESTIÓN DE MENSAJES

	private void realizarAnotaciones() {
		Set<Ejemplar> ejemplares = S_ejemplar.findAll();

		if (ejemplares.isEmpty()) {
			System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
			gestionMensajesmenu();
		}

		System.out.println("Selecciona el ejemplar del que deseas realizar una anotación:");
		int index = 1;
		for (Ejemplar e : ejemplares) {
			System.out.println(index + " - " + e.getNombre());
			index++;
		}

		try {
			// Obtener la selección del usuario
			int seleccion = sc.nextInt();
			sc.nextLine();

			if (seleccion < 1 || seleccion > ejemplares.size()) {
				System.out.println("Selección no válida.");
				return;
			}

			// Obtener el ejemplar seleccionado
			Ejemplar ejemplar = (Ejemplar) ejemplares.toArray()[seleccion - 1];
			long idEjemplar = ejemplar.getId();

			System.out.println("Escribe la anotación sobre el ejemplar:");
			String anotacionUsuario = sc.nextLine().trim();

			// Crear el mensaje (anotación) sobre ese ejemplar
			Mensaje anotacion = new Mensaje();
			anotacion.setFechahora(fechaActual);
			anotacion.setMensaje(anotacionUsuario);
			anotacion.setId_ejemplar(idEjemplar);
			anotacion.setId_persona(facade.id_Persona);

			// Guardo el mensaje en la BD
			S_mensaje.insertar(anotacion);
			gestionMensajesmenu();
		} catch (InputMismatchException e) {
			System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
		
	}

	private void mostrarAnotaciones() {
		Set<Mensaje> mensajes = S_mensaje.findAll();

		if (mensajes.isEmpty()) {
			System.out.println("No hay anotaciones de ejemplares disponibles en el sistema.");
			gestionMensajesmenu();
		}

		for (Mensaje m : mensajes) {
			System.out.println(m.toString());
		}
		
		gestionMensajesmenu();
	}
	
	//FILTROS PARA MOSTRAR ANOTACIONES EN ESPECÍFICO

	private void filtrarAnotacionesporPersona() {
		Set<Persona> listaPersonas = S_persona.findAll();

		System.out.println("Selecciona la persona de la que quieres ver las anotaciones:");
		int index = 1;
		for (Persona p : listaPersonas) {
			System.out.println(index + " - " + p.getNombre());
			index++;
		}

		// Obtener la selección del usuario
		try {
			int seleccion = sc.nextInt();
			if (seleccion < 1 || seleccion > listaPersonas.size()) {
				System.err.println("Selección no válida. Por favor, elige un número entre 1 y " + listaPersonas.size() + ".");
				filtrarAnotacionesmenu();
			}

			// Obtener el ejemplar seleccionado
			Persona personas = (Persona) listaPersonas.toArray()[seleccion - 1];
			long idPersona = personas.getId();

			Set<Mensaje> anotacionesporPersona = S_mensaje.findByPersonaId(idPersona);
			for (Mensaje m : anotacionesporPersona) {
				System.out.println(m.toString());
			}
			filtrarAnotacionesmenu();

		} catch (InputMismatchException e) {
			System.err.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}

	private void filtrarAnotacionesporTipoPlanta() {
	    Set<Planta> listaPlantas = S_planta.find(); // Obtener todas las plantas disponibles

	    System.out.println("Selecciona el tipo de planta del que quieres ver anotaciones:");
	    int index = 1;

	    // Mostrar la lista de plantas al usuario
	    for (Planta p : listaPlantas) {
	        System.out.println(index + " - " + p.getNombrecomun());
	        index++;
	    }

	    try {
	        // Obtener la selección del usuario
	        int seleccion = sc.nextInt();
	        if (seleccion < 1 || seleccion > listaPlantas.size()) {
	            System.err.println("Selección no válida. Inténtalo de nuevo.");
	            filtrarAnotacionesporTipoPlanta();
	        }

	        // Obtener la planta seleccionada
	        Planta plantaSeleccionada = (Planta) listaPlantas.toArray()[seleccion - 1];
	        String codigoPlanta = plantaSeleccionada.getCodigo();

	        // Buscar los ejemplares asociados al código de planta seleccionado
	        List<Ejemplar> ejemplaresDePlanta = S_ejemplar.findByPlanta(codigoPlanta);

	        // Obtener y mostrar todas las anotaciones relacionadas con esos ejemplares
	        Set<Mensaje> anotacionesPorPlanta = new HashSet<>();
	        for (Ejemplar ejemplar : ejemplaresDePlanta) {
	            anotacionesPorPlanta.addAll(S_mensaje.findByEjemplarId(ejemplar.getId()));
	        }

	        // Mostrar las anotaciones obtenidas
	        if (anotacionesPorPlanta.isEmpty()) {
	            System.out.println("No hay anotaciones para el tipo de planta seleccionado.");
	        } else {
	            System.out.println("\nAnotaciones para la planta '" + plantaSeleccionada.getNombrecomun() + "':");
	            for (Mensaje mensaje : anotacionesPorPlanta) {
	                System.out.println("Fecha: " + mensaje.getFechahora() + " | Mensaje: " + mensaje.getMensaje());
	            }
	        }

	        filtrarAnotacionesmenu();
	    } catch (InputMismatchException e) {
	        System.err.println("Error: Solo se permiten números. Inténtalo de nuevo.");
	        sc.nextLine();
	        filtrarAnotacionesporTipoPlanta();
	    }
	}

}
