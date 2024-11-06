package fachada;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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

public class InvernaderoFachadaPersonal {
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
    
    public InvernaderoFachadaPersonal(InvernaderoFachadaPrincipal facade) {
        this.facade = facade;
    }
	
	public void menu() {
        int opcion = -1;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPERSONAL VIVERO\n");
            System.out.println("\t\t\t\t1 - AÑADIR NUEVO EJEMPLAR");
            System.out.println("\t\t\t\t2 - FILTRAR EJEMPLAR/ES POR TIPO DE PLANTA/S");
            System.out.println("\t\t\t\t3 - VER MENSAJES DE UN EJEMPLAR");
            System.out.println("\t\t\t\t4 - CERRAR SESIÓN");
            System.out.println("\t\t\t\t5 - SALIR");

            opcion = facade.obtenerOpcionUsuario(5);

            switch (opcion) {
                case 1: {
                    registrarEjemplar();
                    break;
                }
                case 2: {
                	filtrarEjemplaresPorTipoPlanta();
                    break;
                }
                case 3: {
                	vermensajesEjemplar();
                    break;
                }
                case 4: {
                    facade.iniciosesion();
                    break;
                }
            }
        } while (opcion != 5);
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

        // Obtengo la fecha actual y la formateo
        Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = formatoFecha.format(fechaActual);

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

    
    private void filtrarEjemplaresPorTipoPlanta() {
        
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

}
