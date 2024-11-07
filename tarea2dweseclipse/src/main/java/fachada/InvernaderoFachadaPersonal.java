package fachada;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

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

public class InvernaderoFachadaPersonal {
	private InvernaderoFachadaPrincipal facade;
	Scanner sc = new Scanner(System.in);
	String nombreusuario;
    Persona usuarioActual;
    
    InvernaderoServiciosFactory factoryServicios = InvernaderoServiciosFactory.getServicios();
    
    //FECHA ACTUAL Y FORMATEADA
    Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    String fechaFormateada = formatoFecha.format(fechaActual);

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
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPERSONAL VIVERO\n");
            System.out.println("\t\t\t\t1 - AÑADIR NUEVO EJEMPLAR");
            System.out.println("\t\t\t\t2 - FILTRAR EJEMPLAR/ES");
            System.out.println("\t\t\t\t3 - VER MENSAJES DE UN EJEMPLAR");
            System.out.println("\t\t\t\t4 - GESTIÓN DE MENSAJES");
            System.out.println("\t\t\t\t5 - CERRAR SESIÓN");
            System.out.println("\t\t\t\t6 - SALIR");

            opcion = facade.obtenerOpcionUsuario(6);

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
                	gestionMensajesmenu();                    
                    break;
                }
                case 5: {
                	facade.iniciosesion();
                }
            }
        } while (opcion != 6);
    }
	
    public void filtrarEjemplaresmenu() {
    	int opcion = -1;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tFILTRAR EJEMPLARES\n");
            System.out.println("\t\t\t\t1 - MOSTRAR TODOS LOS EJEMPLARES");
            System.out.println("\t\t\t\t2 - FILTRAR EJEMPLAR/ES POR TIPO/S DE PLANTA/S");
            System.out.println("\t\t\t\t3 - FILTRAR EJEMPLAR/ES POR NOMBRE/S DE PLANTA/S");
            System.out.println("\t\t\t\t4 - SALIR");

            opcion = facade.obtenerOpcionUsuario(3);

            switch (opcion) {
                case 1: {
                	filtrarEjemplarestodos();
                    break;
                }
                case 2: {
                	filtrarEjemplarestipoplanta();
                    break;
                }
            }
        } while (opcion != 3);
        
    }
    
    public void gestionMensajesmenu() {
    	int opcion = -1;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tGESTION DE MENSAJES/ANOTACIONES\n");
            System.out.println("\t\t\t\t1 - REALIZAR ANOTACIONES EN FORMA DE MENSAJES");
            System.out.println("\t\t\t\t2 - MOSTRAR TODOS LOS MENSAJES/ANOTACIONES");
            System.out.println("\t\t\t\t3 - FILTRAR ANOTACIONES/MENSAJES");
            System.out.println("\t\t\t\t4 - SALIR");

            opcion = facade.obtenerOpcionUsuario(4);

            switch (opcion) {
                case 1: {
                	realizarAnotaciones();
                    break;
                }
                case 2: {
                	mostrarAnotaciones();
                    break;
                }
                case 3:{
                	filtrarAnotacionesmenu();
                }
            }
        } while (opcion != 4);
        
    }
    
    public void filtrarAnotacionesmenu(){
    	int opcion = -1;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tFILTRAR MENSAJES/ANOTACIONES\n");
            System.out.println("\t\t\t\t1 - FILTRAR POR PERSONA QUE LO ESCRIBIO");
            System.out.println("\t\t\t\t2 - FILTRAR POR RANGO DE FECHA");
            System.out.println("\t\t\t\t3 - FILTRAR POR TIPO DE PLANTA");
            System.out.println("\t\t\t\t4 - SALIR");

            opcion = facade.obtenerOpcionUsuario(4);

            switch (opcion) {
                case 1: {
                	filtrarAnotacionesporPersona();
                    break;
                }
                case 2: {
                	filtrarAnotacionesporRangoFecha();
                    break;
                }
                case 3:{
                	filtrarAnotacionesporTipoPlanta();
                }
            }
        } while (opcion != 4);
    }
	
	//MÉTODOS PARA LA GESTIÓN DE EJEMPLARES
	
	
	private void registrarEjemplar() {
        Set<Planta> tiposPlantas = S_planta.find();
        if (tiposPlantas.isEmpty()) {
            System.out.println("No hay tipos de plantas disponibles en el sistema.");
            return;
        }

        System.out.println("Selecciona el tipo de planta:");
        int index = 1;
        for (Planta planta : tiposPlantas) {
            System.out.println(index + " - " + planta.getNombrecomun());
            index++;
        }

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
        /*int resultadoEjemplares = */S_ejemplar.insertar(nuevoEjemplar);
        
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
            return;
        }
        
        String nombreEjemplar = codigoPlanta + "_" + idEjemplar;
        System.out.println(nombreEjemplar);
        
        Ejemplar cambioejemplar = new Ejemplar(idEjemplar, nombreEjemplar, codigoPlanta);

        //Modifico el ejemplar en la BD
        S_ejemplar.modificar(cambioejemplar);

        // Creo el mensaje inicial para el ejemplar registrado
        Mensaje mensajeInicial = new Mensaje();
        mensajeInicial.setFechahora(fechaActual);
        mensajeInicial.setMensaje("Registro realizado por " + nombreusuario + " el " + fechaFormateada);
        mensajeInicial.setId_ejemplar(idEjemplar); 
        mensajeInicial.setId_persona(usuarioActual.getId());

        // Guardo el mensaje en la BD
        int resultadoMensaje = S_mensaje.insertar(mensajeInicial);

        if (resultadoMensaje > 0) {
            System.out.println("Ejemplar y mensaje inicial registrados exitosamente.");
        } else {
            System.err.println("Ocurrió un error al registrar el mensaje.");
        }
    } 
   
    private void vermensajesEjemplar() {
        Set<Ejemplar> ejemplares = S_ejemplar.findAll();
        if (ejemplares.isEmpty()) {
            System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
            return;
        }
        
        System.out.println("Selecciona el ejemplar del que deseas ver los mensajes:");
        int index = 1;
        for (Ejemplar e : ejemplares) {
            System.out.println(index + " - " + e.getNombre());
            index++;
        }
        
        // Obtener la selección del usuario
        int seleccion = sc.nextInt();
        if (seleccion < 1 || seleccion > ejemplares.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        // Obtener el ejemplar seleccionado
        Ejemplar ejemplarElegido = (Ejemplar) ejemplares.toArray()[seleccion - 1];
        long idEjemplar = ejemplarElegido.getId();

        // Obtener los mensajes relacionados con el ejemplar seleccionado
        Set<Mensaje> mensajes = S_mensaje.findByEjemplarId(idEjemplar);

        if (mensajes.isEmpty()) {
            System.out.println("No hay mensajes para el ejemplar seleccionado.");
        } else {
            System.out.println("Mensajes para el ejemplar " + ejemplarElegido.getNombre() + ":");
            for (Mensaje mensaje : mensajes) {
                System.out.println("Fecha y hora: " + mensaje.getFechahora());
                System.out.println("Mensaje: " + mensaje.getMensaje());
                System.out.println("-------------------------");
            }
        }
    }
    
    private void filtrarEjemplarestodos() {
    	Set<Ejemplar> ejemplares = S_ejemplar.findAll();
    	
    	if (ejemplares.isEmpty()) {
            System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
            return;
        }
    	
        for (Ejemplar e : ejemplares) {
            System.out.println(e.toString());
        }
    	
    }
    
    private void filtrarEjemplarestipoplanta() {
        Set<Planta> plantas = S_planta.find();
        if (plantas.isEmpty()) {
            System.out.println("No hay plantas disponibles en el sistema.");
            return;
        }

        System.out.println("Selecciona el tipo de planta que deseas ver sus ejemplares:");
        int index = 1;
        for (Planta p : plantas) {
            System.out.println(index + " - " + p.getNombrecomun());
            index++;
        }

        // Obtener la selección del usuario
        int seleccion = sc.nextInt();
        if (seleccion < 1 || seleccion > plantas.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        // Obtener el tipo de planta seleccionado
        Planta tipoplanta = (Planta) plantas.toArray()[seleccion - 1];
        String tipo_Planta = tipoplanta.getCodigo();

        // Obtener todos los ejemplares del tipo de planta seleccionado
        List<Ejemplar> ejemplaresTipoPlanta = S_ejemplar.findByPlanta(tipo_Planta);
        if (ejemplaresTipoPlanta.isEmpty()) {
            System.out.println("No hay ejemplares para el tipo de planta seleccionado.");
        } else {
            for (Ejemplar ejemplar : ejemplaresTipoPlanta) {
                System.out.println(ejemplar.toString());
            }
        }
    }
    
    
    //MÉTODOS PARA LA GESTIÓN DE MENSAJES
    
    
   private void realizarAnotaciones() {
	   Set<Ejemplar> ejemplares = S_ejemplar.findAll();
	   
	   if (ejemplares.isEmpty()) {
           System.out.println("No hay ejemplares de plantas disponibles en el sistema.");
           return;
       }
       
       System.out.println("Selecciona el ejemplar del que deseas realizar una anotación:");
       int index = 1;
       for (Ejemplar e : ejemplares) {
           System.out.println(index + " - " + e.getNombre());
           index++;
       }
       
       // Obtener la selección del usuario
       int seleccion = sc.nextInt();
       if (seleccion < 1 || seleccion > ejemplares.size()) {
           System.out.println("Selección no válida.");
           return;
       }

       // Obtener el ejemplar seleccionado
       Ejemplar ejemplar = (Ejemplar) ejemplares.toArray()[seleccion - 1];
       long idEjemplar=ejemplar.getId();
       
       System.out.println("Escribe la anotación sobre el ejemplar:");
       String anotacionUsuario = sc.nextLine().trim();
       
       // Creo el mensaje(anotación) sobre ese ejemplar
       Mensaje anotacion = new Mensaje();
       anotacion.setFechahora(fechaActual);
       anotacion.setMensaje(anotacionUsuario);
       anotacion.setId_ejemplar(idEjemplar); 
       anotacion.setId_persona(usuarioActual.getId());
       
       // Guardo el mensaje en la BD
       int resultadoMensaje = S_mensaje.insertar(anotacion);
       
       //Seguimiento mensajeanotacion = new Seguimiento();
       
   }  
   
   private void mostrarAnotaciones(){
	   Set<Mensaje> mensajes =S_mensaje.findAll();
	   
	   if (mensajes.isEmpty()) {
           System.out.println("No hay anotaciones de ejemplares disponibles en el sistema.");
           return;
       }
	   
	   for (Mensaje m : mensajes) {
           System.out.println(m.toString());
       }  
   }
    
   private void filtrarAnotacionesporPersona() {
	   Set<Persona> listaPersonas = S_persona.findAll();
	   
	   System.out.println("Selecciona la persona de la que quieres ver las anotaciones:");
       int index = 1;
       for (Persona p : listaPersonas) {
           System.out.println(index + " - " + p.getNombre());
           index++;
       }
       
       // Obtener la selección del usuario
       int seleccion = sc.nextInt();
       if (seleccion < 1 || seleccion > listaPersonas.size()) {
           System.out.println("Selección no válida.");
           return;
       }

       // Obtener el ejemplar seleccionado
       Persona personas = (Persona) listaPersonas.toArray()[seleccion - 1];
       long idPersona=personas.getId();
       
	   Set<Mensaje> anotacionesporPersona = S_mensaje.findByPersonaId(idPersona);
	   for(Mensaje m : anotacionesporPersona) {
		   System.out.println(m.toString());
		   
	   }
   }
   
   private void filtrarAnotacionesporRangoFecha() {
	    Scanner sc = new Scanner(System.in);
	    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	    DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	    try {
	        // Ingreso y conversión de la primera fecha
	        System.out.print("Fecha 1 (dd/MM/yyyy): ");
	        Date fecha1 = formatoFecha.parse(sc.nextLine());
	        LocalDateTime fecha1Local = fecha1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

	        // Ingreso y conversión de la segunda fecha
	        System.out.print("Fecha 2 (dd/MM/yyyy): ");
	        Date fecha2 = formatoFecha.parse(sc.nextLine());
	        LocalDateTime fecha2Local = fecha2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

	        // Obtén todos los mensajes
	        Set<Mensaje> listaMensajes = S_mensaje.findAll();

	        // Filtra los mensajes en el rango de fecha1 a fecha2
	        Set<Mensaje> mensajesFiltrados = listaMensajes.stream().filter(mensaje -> {
	           LocalDateTime fechaMensaje = mensaje.getFechahora().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	           return !fechaMensaje.isBefore(fecha1Local) && !fechaMensaje.isAfter(fecha2Local);}).collect(Collectors.toSet());

	        for (Mensaje mensaje : mensajesFiltrados) {
	            System.out.println(mensaje.toString());
	        }

	    } catch (ParseException e) {
	        System.out.println("Formato de fecha incorrecto. Usa el formato dd/MM/yyyy.");
	    }
	}
   
   
   private void filtrarAnotacionesporTipoPlanta() {
	    Set<Planta> listaPlantas = S_planta.find();
	    
	    System.out.println("Selecciona el tipo de planta de la que quieres ver anotaciones:");
	    int index = 1;
	    for (Planta p : listaPlantas) {
	        System.out.println(index + " - " + p.getNombrecomun());
	        index++;
	    }
	    
	    // Obtener la selección del usuario
	    int seleccion = sc.nextInt();
	    if (seleccion < 1 || seleccion > listaPlantas.size()) {
	        System.out.println("Selección no válida.");
	        return;
	    }

	    // Obtener la planta seleccionada
	    Planta planta = (Planta) listaPlantas.toArray()[seleccion - 1];
	    String codigo_planta = planta.getCodigo();

	    // Obtener todos los ejemplares que tengan el código de la planta seleccionada
	    List<Ejemplar> codigo_plantaEjemplar = S_ejemplar.findByPlanta(codigo_planta);
	    
	    // Extraer los IDs de los ejemplares
	    Set<Mensaje> anotacionesPorTipoPlanta = new HashSet<Mensaje>();
	    for (Ejemplar ejemplar : codigo_plantaEjemplar) {
	    	anotacionesPorTipoPlanta = S_mensaje.findByEjemplarId(ejemplar.getId());
	    }
	    
	    // Mostrar las anotaciones obtenidas
	    if (anotacionesPorTipoPlanta.isEmpty()) {
	        System.out.println("No hay anotaciones para el tipo de planta seleccionado.");
	    } else {
	        for (Mensaje m : anotacionesPorTipoPlanta) {
	            System.out.println(m.toString());
	        }
	    }
	}

   
}
