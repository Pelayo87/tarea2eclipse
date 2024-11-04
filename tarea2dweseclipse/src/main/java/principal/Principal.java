package principal;

import java.util.Scanner;
import java.util.Set;
import com.dwes.modelo.Credenciales;
import com.dwes.modelo.Planta;
import servicioImpl.ServicioCredencialesImpl;
import servicioImpl.ServicioPlantaImpl;

public class Principal {
    Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        Principal m = new Principal();
        System.out.println("--GESTIÓN DE INVERNADERO (DWES)--");
        m.iniciosesion();
    }
    
    public void iniciosesion() {
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tINICIO DE SESIÓN O REGISTRARSE\n");
            System.out.println("\t\t\t\t1 - LOGIN");        
            System.out.println("\t\t\t\t2 - ENTRAR COMO INVITADO");
            System.out.println("\t\t\t\t3 - SALIR");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpio el buffer después de nextInt
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
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPERFIL INVITADO\n");
            System.out.println("\t\t\t\t1 - VER PLANTAS");
            System.out.println("\t\t\t\t2 - LOGIN");
            System.out.println("\t\t\t\t3 - SALIR");
            opcion = sc.nextInt();
            sc.nextLine();
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
    
    public void login() {
        System.out.println("Nombre de usuario:");
        String nombreusuario = sc.nextLine().trim();
        System.out.println("Contraseña (password):");
        String contrasena = sc.nextLine().trim();

        Credenciales credencialesIngresadas = new Credenciales(nombreusuario, contrasena);
        ServicioCredencialesImpl servicioCredenciales = new ServicioCredencialesImpl();

        boolean autenticado = servicioCredenciales.autenticar(credencialesIngresadas);

        if (autenticado) {
            if ("admin".equalsIgnoreCase(nombreusuario) && "admin".equals(contrasena)) {
                System.out.println("Inicio de sesión exitoso como administrador.");
                menuadmin();
            } else {
                System.out.println("Inicio de sesión exitoso.");
                menu();
            }
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectos.");
        }
    }





    public void menuadmin() {
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tADMINISTRADOR VIVERO\n");
            System.out.println("\t\t\t\t1 - AÑADIR PLANTA");
            System.out.println("\t\t\t\t2 - MODIFICAR PLANTA");
            System.out.println("\t\t\t\t3 - BORRAR PLANTA");
            System.out.println("\t\t\t\t4 - CERRAR SESIÓN");
            System.out.println("\t\t\t\t5 - VOLVER ATRÁS");
            opcion = sc.nextInt();
            sc.nextLine();
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

    public void menu() {
        int opcion = 0;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPERSONAL VIVERO\n");
            System.out.println("\t\t\t\t1 - AÑADIR NUEVO EJEMPLAR");
            System.out.println("\t\t\t\t2 - MOSTRAR EJEMPLARES");
            System.out.println("\t\t\t\t3 - BORRAR PLANTA");
            System.out.println("\t\t\t\t4 - CERRAR SESIÓN");
            System.out.println("\t\t\t\t5 - VOLVER ATRÁS");
            opcion = sc.nextInt();
            sc.nextLine();
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
        Set<Planta> plantas = servicioPlanta.find();
        
        for (Planta planta : plantas) {
            System.out.println(planta);
        }
    }
}

