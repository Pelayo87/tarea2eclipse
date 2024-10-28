package principal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

import com.dwes.conexion.ConexionBD;
import com.dwes.daoImpl.PlantaDAOImpl;
import com.dwes.modelo.Planta;

public class Principal {
	Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		Principal m = new Principal();
		m.iniciosesion();
	}
	
	//Ver Plantas para el perfil de invitado
	public void iniciosesion(){
		//MENU PRINCIPAL TRAS LOGUEARTE CORRECTAMENTE
        int opcion = 0;
		do {
			System.out.println("\n\n\n\n\n\t\t\t\tINICIO DE SESIÓN\n");
			System.out.println("\t\t\t\t1 - LOGIN");
			System.out.println("\t\t\t\t2 - ENTRAR COMO INVITADO");
			System.out.println("\t\t\t\t3 - SALIR");
			opcion = sc.nextInt();
			// Limpio el buffer después de nextInt
			sc.nextLine();
			switch (opcion) {
			case 1: {
				menu();
				break;
			}
			case 2: {
				verplantasinvitado();
				break;
			}
			}
		} while (opcion != 4);
	}
	
	public void verplantasinvitado() {
		int opcion = 0;
		do {
			System.out.println("\n\n\n\n\n\t\t\t\tPERFIL INVITADO\n");
			System.out.println("\t\t\t\t1 - VER PLANTAS");
			System.out.println("\t\t\t\t2 - SALIR");
			opcion = sc.nextInt();
			// Limpio el buffer después de nextInt
			sc.nextLine();
			switch (opcion) {
			case 1: {
				mostrarPlantas();
				break;
			}
			}
		} while (opcion != 2);
	}
	
	public void menu() {
        System.out.println("INICIO");
        
        //MENU PRINCIPAL TRAS LOGUEARTE CORRECTAMENTE
        int opcion = 0;
		do {
			System.out.println("\n\n\n\n\n\t\t\t\tINVERNADERO DWES\n");
			System.out.println("\t\t\t\t1 - AÑADIR PLANTA");
			System.out.println("\t\t\t\t2 - MODIFICAR PLANTA");
			System.out.println("\t\t\t\t3 - BORRAR PLANTA");
			System.out.println("\t\t\t\t4 - SALIR");
			opcion = sc.nextInt();
			// Limpio el buffer después de nextInt
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
			}
		} while (opcion != 4);
	}

	private void anadirPlanta() {   
        //INSERTAR PLANTA
        
        System.out.println("Dame el código de la nueva planta:");
        String codigo = sc.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre común de la planta:");
        String nombrecomun = sc.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre científico de la planta:");
        String nombrecientifico = sc.nextLine().trim().toUpperCase();

        Planta nuevaplanta = new Planta(codigo, nombrecomun, nombrecientifico);
        System.out.println(nuevaplanta);

        // Obtengo la conexión a la base de datos
        try (Connection conexion = ConexionBD.getConexion()) {
            // Paso la conexión al constructor de PlantaDAOImpl
            PlantaDAOImpl plantaDAO = new PlantaDAOImpl(conexion);
            int insertar = plantaDAO.insertar(nuevaplanta);
        } catch (SQLException | IOException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
     }
	
	private void modificarPlanta() {   
        //MODIFICAR PLANTA
        
        System.out.println("Dame el código de la planta a modificar:");
        String codigo = sc.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre común de la planta:");
        String nombrecomun = sc.nextLine().trim().toUpperCase();
        System.out.println("Dame el nombre científico de la planta:");
        String nombrecientifico = sc.nextLine().trim().toUpperCase();

        Planta cambioplanta = new Planta(codigo, nombrecomun, nombrecientifico);
        System.out.println(cambioplanta);

        // Obtengo la conexión a la base de datos
        try (Connection conexion = ConexionBD.getConexion()) {
            // Paso la conexión al constructor de PlantaDAOImpl
            PlantaDAOImpl plantaDAO = new PlantaDAOImpl(conexion);
            int insertar = plantaDAO.modificar(cambioplanta);
        } catch (SQLException | IOException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
     }
	
	private void mostrarPlantas() {   
	    // MOSTRAR PLANTAS
	    try (Connection conexion = ConexionBD.getConexion()) {
	        PlantaDAOImpl plantaDAO = new PlantaDAOImpl(conexion);
	        Set<Planta> plantas = plantaDAO.find();
	        
	        for (Planta planta : plantas) {
	           System.out.println(planta);
	        }
	        
	    } catch (SQLException | IOException e) {
	        System.out.println("Error al conectar con la base de datos: " + e.getMessage());
	    }
	}


}
