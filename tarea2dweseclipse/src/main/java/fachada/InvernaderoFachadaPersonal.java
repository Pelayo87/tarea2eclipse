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
			}
			case 2: {
				gestionMensajesmenu();
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
		System.out.println("\t\t\t\t2 - MODIFICAR EJEMPLAR");
		System.out.println("\t\t\t\t3 - BORRAR EJEMPLAR");
		System.out.println("\t\t\t\t4 - FILTRAR EJEMPLAR/ES");
		System.out.println("\t\t\t\t5 - VER MENSAJES DE UN EJEMPLAR");
		System.out.println("\t\t\t\t6 - REGISTRO RIEGO DE UN EJEMPLAR");
		System.out.println("\t\t\t\t7 - VOLVER ATRÁS");
		System.out.println("\t\t\t\t8 - CERRAR SESIÓN");
		System.out.println("\t\t\t\t9 - SALIR DEL PROGRAMA");

		opcion = Utilidades.obtenerOpcionUsuario(9);

		switch (opcion) {
		case 1: {
			registrarEjemplar();
		}
		case 2: {
			modificarEjemplar();
		}
		case 3: {
			borrarEjemplar();
		}
		case 4: {
			filtrarEjemplaresmenu();
		}
		case 5: {
			vermensajesEjemplar();
		}
		case 6: {
			registrarRiego();
			gestionEjemplaresmenu();
		}
		case 7: {
			if ("admin".equalsIgnoreCase(facade.nombreusuario)) {
				facade.facadeAdmin.menuadmin();
			} else {
				menu();
			}
		}
		case 8: {
			facade.iniciosesion();
		}
		case 9: {
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
				filtrarEjemplaresmenu();
			}
			case 2: {
				filtrarEjemplarestipoplanta();
				filtrarEjemplaresmenu();
			}
			case 3: {
				gestionEjemplaresmenu();
				filtrarEjemplaresmenu();
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
			System.out.println("\t\t\t\t2 - MODIFICAR ANOTACIONES/MENSAJES DE UN EJEMPLAR");
			System.out.println("\t\t\t\t2 - ELIMINAR ANOTACIONES/MENSAJES DE UN EJEMPLAR");
			System.out.println("\t\t\t\t3 - MOSTRAR TODOS LOS MENSAJES/ANOTACIONES");
			System.out.println("\t\t\t\t4 - FILTRAR ANOTACIONES/MENSAJES");
			System.out.println("\t\t\t\t5 - VOLVER ATRÁS");
			System.out.println("\t\t\t\t6 - SALIR DEL PROGRAMA");

			opcion = Utilidades.obtenerOpcionUsuario(7);

			switch (opcion) {
			case 1: {
				realizarAnotaciones();
				break;
			}
			case 2: {
				modificarAnotaciones();
				break;
			}
			case 3: {
				borrarAnotaciones();
			}
			case 4: {
				mostrarAnotaciones();
				break;
			}
			case 5: {
				filtrarAnotacionesmenu();
			}
			case 6: {
				if ("admin".equalsIgnoreCase(facade.nombreusuario)) {
					facade.facadeAdmin.menuadmin();
				} else {
					menu();
				}
			}
			case 7: {
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
	
	private void modificarEjemplar() {
		Set<Ejemplar> ejemplares = S_ejemplar.findAll();
		if (ejemplares.isEmpty()) {
			System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
			gestionEjemplaresmenu();
		}

		System.out.println("Selecciona el ejemplar que deseas modificar:");
		int index = 1;
		for (Ejemplar e : ejemplares) {
			System.out.println(index + " - " + e.getNombre());
			index++;
		}

		try {
			int seleccion = sc.nextInt();
			sc.nextLine();
			
			if (seleccion < 1 || seleccion > ejemplares.size()) {
				System.out.println("Selección no válida.");
				return;
			}

			Ejemplar ejemplarModificar = (Ejemplar) ejemplares.toArray()[seleccion - 1];
			
			Set<Planta> plantas = S_planta.find();
			System.out.println("Selecciona el nuevo tipo de planta:");
			index = 1;
			for (Planta p : plantas) {
				System.out.println(index + " - " + p.getNombrecomun());
				index++;
			}

			int seleccionPlanta = sc.nextInt();
			if (seleccionPlanta < 1 || seleccionPlanta > plantas.size()) {
				System.out.println("Selección no válida.");
				return;
			}

			Planta nuevaPlanta = (Planta) plantas.toArray()[seleccionPlanta - 1];
			String nuevoCodigo = nuevaPlanta.getCodigo();
			String nuevoNombre = nuevoCodigo + "_" + ejemplarModificar.getId();

			Ejemplar ejemplarActualizado = new Ejemplar(ejemplarModificar.getId(), nuevoNombre, nuevoCodigo);
			S_ejemplar.modificar(ejemplarActualizado);
			
			Mensaje mensajeModificacion = new Mensaje();
			mensajeModificacion.setFechahora(fechaActual);
			mensajeModificacion.setMensaje("Ejemplar modificado por " + facade.nombreusuario + " el " + fechaFormateada);
			mensajeModificacion.setId_ejemplar(ejemplarModificar.getId());
			mensajeModificacion.setId_persona(facade.id_Persona);
			S_mensaje.insertar(mensajeModificacion);

			System.out.println("Ejemplar modificado correctamente.");
			gestionEjemplaresmenu();
		} catch (InputMismatchException e) {
			System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
			sc.nextLine();
		}
	}
	
	private void borrarEjemplar() {
		Set<Ejemplar> ejemplares = S_ejemplar.findAll();
		if (ejemplares.isEmpty()) {
			System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
			gestionEjemplaresmenu();
		}
		
		System.out.println("Selecciona el ejemplar que deseas eliminar:");
		int index = 1;
		for (Ejemplar e : ejemplares) {
			System.out.println(index + " - " + e.getNombre());
			index++;
		}
		
		try {
			int seleccion = sc.nextInt();
			if (seleccion < 1 || seleccion > ejemplares.size()) {
				System.out.println("Selección no válida.");
				return;
			}
			Ejemplar ejemplarEliminar = (Ejemplar) ejemplares.toArray()[seleccion - 1];
			
			System.out.println("¿Estás seguro de que deseas eliminar el ejemplar " + ejemplarEliminar.getNombre() + "? (S/N)");
			sc.nextLine();
			String confirmacion = sc.nextLine();
			
			if (confirmacion.equalsIgnoreCase("S")) {
				S_ejemplar.eliminar(ejemplarEliminar);
				System.out.println("Ejemplar eliminado correctamente.");
			} else {
				System.out.println("Operación cancelada.");
			}		gestionEjemplaresmenu();
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
	
	private void registrarRiego() {
		Set<Ejemplar> ejemplares = S_ejemplar.findAll();
		if (ejemplares.isEmpty()) {
			System.out.println("No hay ejemplares disponibles.");
			registrarRiego();
		}

		System.out.println("Selecciona el ejemplar que has regado:");
		int index = 1;
		for (Ejemplar e : ejemplares) {
			System.out.println(index + " - " + e.getNombre());
			index++;
		}

		try {
			int seleccion = sc.nextInt();
			sc.nextLine();
			if (seleccion < 1 || seleccion > ejemplares.size()) {
				System.out.println("Selección no válida.");
				registrarRiego();
			}

			Ejemplar ejemplar = (Ejemplar) ejemplares.toArray()[seleccion - 1];

			System.out.println("Cantidad de agua utilizada (en litros):");
			double cantidadAgua = sc.nextDouble();
			sc.nextLine();

			Mensaje mensajeRiego = new Mensaje();
			mensajeRiego.setFechahora(fechaActual);
			mensajeRiego.setMensaje("Riego realizado - Cantidad: " + cantidadAgua + "L");
			mensajeRiego.setId_ejemplar(ejemplar.getId());
			mensajeRiego.setId_persona(facade.id_Persona);

			S_mensaje.insertar(mensajeRiego);
			System.out.println("Riego registrado correctamente.");
		} catch (InputMismatchException e) {
			System.out.println("Entrada no válida.");
			sc.nextLine();
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
	
	private void modificarAnotaciones() {
	    Set<Ejemplar> ejemplares = S_ejemplar.findAll();

	    if (ejemplares.isEmpty()) {
	        System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
	        gestionMensajesmenu();
	        return;
	    }

	    System.out.println("Selecciona el ejemplar del que deseas modificar una anotación:");
	    int index = 1;
	    for (Ejemplar e : ejemplares) {
	        System.out.println(index + " - " + e.getNombre());
	        index++;
	    }

	    // Validar la selección del ejemplar
	    int seleccion = -1;
	    while (seleccion < 1 || seleccion > ejemplares.size()) {
	        try {
	            seleccion = Integer.parseInt(sc.nextLine());
	            if (seleccion < 1 || seleccion > ejemplares.size()) {
	                System.out.println("Selección no válida. Por favor, elige un número entre 1 y " + ejemplares.size());
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
	        }
	    }

	    Ejemplar ejemplar = (Ejemplar) ejemplares.toArray()[seleccion - 1];
	    long idEjemplar = ejemplar.getId();

	    // Obtener las anotaciones de ese ejemplar
	    Set<Mensaje> anotaciones = S_mensaje.findByEjemplarId(idEjemplar);

	    if (anotaciones.isEmpty()) {
	        System.out.println("No hay anotaciones para este ejemplar.");
	        return;
	    }

	    System.out.println("Selecciona la anotación que deseas modificar:");
	    index = 1;
	    for (Mensaje m : anotaciones) {
	        System.out.println(index + " - " + m.getMensaje());
	        index++;
	    }

	    // Validar la selección de la anotación
	    int seleccionAnotacion = -1;
	    while (seleccionAnotacion < 1 || seleccionAnotacion > anotaciones.size()) {
	        try {
	            seleccionAnotacion = Integer.parseInt(sc.nextLine());
	            if (seleccionAnotacion < 1 || seleccionAnotacion > anotaciones.size()) {
	                System.out.println("Selección no válida. Por favor, elige un número entre 1 y " + anotaciones.size());
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
	        }
	    }

	    Mensaje anotacionSeleccionada = (Mensaje) anotaciones.toArray()[seleccionAnotacion - 1];

	    // Mostrar la anotación actual y permitir la modificación
	    System.out.println("La anotación actual es: " + anotacionSeleccionada.getMensaje());
	    System.out.println("Escribe el nuevo texto para la anotación:");

	    String nuevaAnotacion = sc.nextLine().trim();

	    if (nuevaAnotacion.isEmpty()) {
	        System.out.println("La anotación no puede estar vacía.");
	        return;
	    }

	    // Modifico la anotación y actualizo la fecha a la de su modificación
	    anotacionSeleccionada.setMensaje(nuevaAnotacion);
	    anotacionSeleccionada.setFechahora(fechaActual); 

	    try {
	        S_mensaje.modificar(anotacionSeleccionada);
	        System.out.println("La anotación ha sido modificada correctamente.");
	    } catch (Exception e) {
	        System.err.println("Hubo un error al modificar la anotación: " + e.getMessage());
	    }
	    gestionMensajesmenu();
	}
	
	private void borrarAnotaciones(){	   
	    Set<Ejemplar> ejemplares = S_ejemplar.findAll();

	    if (ejemplares.isEmpty()) {
	        System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
	        gestionMensajesmenu();
	        return;
	    }

	    System.out.println("Selecciona el ejemplar del que deseas borrar una anotación:");
	    int index = 1;
	    for (Ejemplar e : ejemplares) {
	        System.out.println(index + " - " + e.getNombre());
	        index++;
	    }

	    // Validar la selección del ejemplar
	    int seleccion = -1;
	    while (seleccion < 1 || seleccion > ejemplares.size()) {
	        try {
	            seleccion = Integer.parseInt(sc.nextLine());
	            if (seleccion < 1 || seleccion > ejemplares.size()) {
	                System.out.println("Selección no válida. Por favor, elige un número entre 1 y " + ejemplares.size());
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
	        }
	    }

	    Ejemplar ejemplar = (Ejemplar) ejemplares.toArray()[seleccion - 1];
	    long idEjemplar = ejemplar.getId();

	    // Obtener las anotaciones de ese ejemplar
	    Set<Mensaje> anotaciones = S_mensaje.findByEjemplarId(idEjemplar);

	    if (anotaciones.isEmpty()) {
	        System.out.println("No hay anotaciones para este ejemplar.");
	        return;
	    }

	    // Mostrar las anotaciones disponibles
	    System.out.println("Selecciona la anotación que deseas borrar:");
	    index = 1;
	    for (Mensaje m : anotaciones) {
	        System.out.println(index + " - " + m.getMensaje());
	        index++;
	    }

	    // Validar la selección de la anotación
	    int seleccionAnotacion = -1;
	    while (seleccionAnotacion < 1 || seleccionAnotacion > anotaciones.size()) {
	        try {
	            seleccionAnotacion = Integer.parseInt(sc.nextLine());
	            if (seleccionAnotacion < 1 || seleccionAnotacion > anotaciones.size()) {
	                System.out.println("Selección no válida. Por favor, elige un número entre 1 y " + anotaciones.size());
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
	        }
	    }

	    Mensaje anotacionSeleccionada = (Mensaje) anotaciones.toArray()[seleccionAnotacion - 1];

	    // Confirmo si el usuario está seguro de eliminar la anotación
	    System.out.println("Estás seguro de que deseas borrar la siguiente anotación?");
	    System.out.println("Anotación: " + anotacionSeleccionada.getMensaje());
	    System.out.println("1 - Sí");
	    System.out.println("2 - No");

	    int confirmacion = -1;
	    while (confirmacion != 1 && confirmacion != 2) {
	        try {
	            confirmacion = Integer.parseInt(sc.nextLine());
	            if (confirmacion == 1) {
	                try {
	                    S_mensaje.eliminar(anotacionSeleccionada);
	                } catch (Exception e) {
	                    System.err.println("Hubo un error al eliminar la anotación: " + e.getMessage());
	                }
	            } else if (confirmacion == 2) {
	                System.out.println("La eliminación de la anotación ha sido cancelada.");
	            } else {
	                System.out.println("Opción no válida. Por favor, ingresa 1 para sí o 2 para no.");
	            }
	        } catch (NumberFormatException e) {
	            System.out.println("Solo se permiten ingresar números, inténtalo de nuevo.");
	        }
	    }
	    gestionMensajesmenu();
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
	    Set<Planta> listaPlantas = S_planta.find();

	    System.out.println("Selecciona el tipo de planta del que quieres ver anotaciones:");
	    int index = 1;

	    // Mostrar la lista de plantas al usuario
	    for (Planta p : listaPlantas) {
	        System.out.println(index + " - " + p.getNombrecomun());
	        index++;
	    }

	    try {
	        int seleccion = sc.nextInt();
	        if (seleccion < 1 || seleccion > listaPlantas.size()) {
	            System.err.println("Selección no válida. Inténtalo de nuevo.");
	            filtrarAnotacionesporTipoPlanta();
	        }

	        Planta plantaSeleccionada = (Planta) listaPlantas.toArray()[seleccion - 1];
	        String codigoPlanta = plantaSeleccionada.getCodigo();

	        List<Ejemplar> ejemplaresDePlanta = S_ejemplar.findByPlanta(codigoPlanta);

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
