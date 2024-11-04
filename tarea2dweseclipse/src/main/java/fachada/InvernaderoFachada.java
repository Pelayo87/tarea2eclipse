package fachada;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.dwes.dao.CredencialesDAO;
import com.dwes.dao.PersonaDAO;
import com.dwes.modelo.Credenciales;
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
import com.dwes.util.MySqlDAOFactory;

import servicioImpl.ServicioCredencialesImpl;
import servicioImpl.ServicioPersonaImpl;
import servicioImpl.ServicioPlantaImpl;

public class InvernaderoFachada {
    private static InvernaderoFachada portal;
    Scanner sc = new Scanner(System.in);
    String nombreusuario;

    MySqlDAOFactory factoryDAO = MySqlDAOFactory.getConexion();
    InvernaderoServiciosFactory factoryServicios = InvernaderoServiciosFactory.getServicios();

    ServicioEjemplar S_ejemplar = factoryServicios.getServiciosEjemplar();
    ServicioPlanta S_planta = factoryServicios.getServiciosPlanta();
    ServicioMensaje S_mensaje = factoryServicios.getServiciosMensaje();
    ServicioCredenciales S_credenciales = factoryServicios.getServiciosCredenciales();
    ServicioPersona S_persona = factoryServicios.getServiciosPersona();

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
                    invitado();
                    break;
                }
            }
        } while (opcion != 3);
    }

    public void invitado() {
        int opcion = -1;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPERFIL INVITADO\n");
            System.out.println("\t\t\t\t1 - VER PLANTAS");
            System.out.println("\t\t\t\t2 - LOGIN");
            System.out.println("\t\t\t\t3 - SALIR");

            opcion = obtenerOpcionUsuario(3);

            switch (opcion) {
                case 1: {
                    mostrarPlantas();
                    break;
                }
                case 2: {
                    login();
                    break;
                }
            }
        } while (opcion != 3);
    }
    
    public void menuadmin() {
        int opcion = -1;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPERFIL INVITADO\n");
            System.out.println("\t\t\t\t1 - GESTIÓN DE PLANTAS");
            System.out.println("\t\t\t\t2 - GESTIÓN DE PERSONAS");
            System.out.println("\t\t\t\t3 - SALIR");

            opcion = obtenerOpcionUsuario(3);

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
    
    public void login() {
        System.out.println("Nombre de usuario:");
         nombreusuario = sc.nextLine().trim();
        System.out.println("Contraseña (password):");
        String contrasena = sc.nextLine().trim();

        Credenciales credencialesIngresadas = new Credenciales(nombreusuario, contrasena);
        ServicioCredencialesImpl servicioCredenciales = new ServicioCredencialesImpl();

        boolean autenticado = servicioCredenciales.autenticar(credencialesIngresadas);

        if (autenticado) {
            // Obtener el servicio de persona para buscar la persona actual
            ServicioPersona servicioPersona = new ServicioPersonaImpl();
            Persona usuarioActual = servicioPersona.findByNombre(nombreusuario);

            if (usuarioActual != null) {
                System.out.println("Inicio de sesión exitoso.");

                if ("admin".equalsIgnoreCase(nombreusuario) && "admin".equals(contrasena)) {
                    System.out.println("Inicio de sesión exitoso como administrador.");
                    menuadmin();
                } else {
                    menu();
                }
            } else {
                System.out.println("No se encontró el usuario en la base de datos.");
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
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

            opcion = obtenerOpcionUsuario(5);

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
                    iniciosesion();
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

            opcion = obtenerOpcionUsuario(2);

            switch (opcion) {
                case 1: {
                    	registrarPersona();
                    break;
                }
            }
        } while (opcion != 2);
    }

    public void menu() {
        int opcion = -1;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPERSONAL VIVERO\n");
            System.out.println("\t\t\t\t1 - AÑADIR NUEVO EJEMPLAR");
            System.out.println("\t\t\t\t2 - CERRAR SESIÓN");
            System.out.println("\t\t\t\t3 - SALIR");

            opcion = obtenerOpcionUsuario(3);

            switch (opcion) {
                case 1: {
                    registrarEjemplar();
                    break;
                }
                case 2: {
                    iniciosesion();
                    break;
                }
            }
        } while (opcion != 3);
    }

    private int obtenerOpcionUsuario(int maxOpcion) {
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

    private void anadirPlanta() {
        System.out.println("Dame el código de la nueva planta:");
        String codigo = sc.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre común de la planta:");
        String nombrecomun = sc.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre científico de la planta:");
        String nombrecientifico = sc.nextLine().trim().toUpperCase();

        Planta nuevaplanta = new Planta(codigo, nombrecomun, nombrecientifico);
        ServicioPlantaImpl servicioPlanta = new ServicioPlantaImpl();

        servicioPlanta.insertar(nuevaplanta);
    }

    private void modificarPlanta() {
        System.out.println("Dame el código de la planta a modificar:");
        String codigo = sc.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre común de la planta:");
        String nombrecomun = sc.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre científico de la planta:");
        String nombrecientifico = sc.nextLine().trim().toUpperCase();

        Planta cambioplanta = new Planta(codigo, nombrecomun, nombrecientifico);
        ServicioPlantaImpl servicioPlanta = new ServicioPlantaImpl();

        servicioPlanta.modificar(cambioplanta);
    }

    private void eliminarPlanta() {
        System.out.println("Dame el código de la planta a eliminar:");
        String codigo = sc.nextLine().trim().toUpperCase();

        Planta eliminarplanta = new Planta(codigo, null, null);
        ServicioPlantaImpl servicioPlanta = new ServicioPlantaImpl();

        servicioPlanta.eliminar(eliminarplanta);
    }

    private void mostrarPlantas() {
        ServicioPlantaImpl servicioPlanta = new ServicioPlantaImpl();
        
        Set<Planta> plantasSet = servicioPlanta.find();
        List<Planta> Listaplantas = new ArrayList<>(plantasSet);

        Collections.sort(Listaplantas, new Comparator<Planta>() {
            @Override
            public int compare(Planta p1, Planta p2) {
                return p1.getNombrecomun().compareToIgnoreCase(p2.getNombrecomun());
            }
        });

        // Mostrar las plantas en orden alfabético
        for (Planta planta : Listaplantas) {
            System.out.println(planta);
        }
    }

    
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


    
    private void registrarEjemplar() {
        Set<Planta> tiposPlantas = S_planta.find(); // Obtiene los tipos de plantas disponibles
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
        sc.nextLine(); // Limpiar el buffer
        if (seleccion < 1 || seleccion > tiposPlantas.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        // Obtener la planta seleccionada
        Planta plantaSeleccionada = (Planta) tiposPlantas.toArray()[seleccion - 1];
        System.out.println("Planta seleccionada: " + plantaSeleccionada.getNombrecomun());
        
        System.out.println("Introduce el nombre del nuevo ejemplar:");
        String nombreEjemplar = sc.nextLine().trim();

        // Crear un nuevo ejemplar
        Ejemplar nuevoEjemplar = new Ejemplar();
        nuevoEjemplar.setPlanta(plantaSeleccionada);
        nuevoEjemplar.setNombre(nombreEjemplar);

        // Crear un Set para las personas
        /*Set<Persona> personasSet = new HashSet<>();
        personasSet.add(nombreusuario);
        nuevoEjemplar.setPersonas(personasSet);*/

        // Crear un mensaje inicial
        Mensaje mensajeInicial = new Mensaje();
        mensajeInicial.setMensaje("Registro realizado por " + nombreusuario + " el " + new Date());
        mensajeInicial.setEjemplar(nuevoEjemplar); 
        mensajeInicial.setFechahora(new Date());

        // Guardar el mensaje en la base de datos
        S_mensaje.insertar(mensajeInicial);

        // Guardar el ejemplar en la base de datos
        int resultado = S_ejemplar.insertar(nuevoEjemplar);
        if (resultado > 0) {
            System.out.println("Ejemplar registrado exitosamente.");
        } else {
            System.err.println("Ocurrió un error al registrar el ejemplar. Inténtalo de nuevo.");
        }
    }

}
