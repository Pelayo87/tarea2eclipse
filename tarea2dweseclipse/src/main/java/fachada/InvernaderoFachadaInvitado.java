package fachada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.dwes.modelo.Persona;
import com.dwes.modelo.Planta;
import com.dwes.servicios.ServicioCredenciales;
import com.dwes.servicios.ServicioEjemplar;
import com.dwes.servicios.ServicioMensaje;
import com.dwes.servicios.ServicioPersona;
import com.dwes.servicios.ServicioPlanta;
import com.dwes.util.InvernaderoServiciosFactory;
import com.dwes.util.Utilidades;

public class InvernaderoFachadaInvitado {
	protected InvernaderoFachadaPrincipal facade;
	Scanner sc = new Scanner(System.in);
    String nombreusuario;
    Persona usuarioActual;    
    
    InvernaderoServiciosFactory factoryServicios = InvernaderoServiciosFactory.getServicios();
    

    ServicioEjemplar S_ejemplar = factoryServicios.getServiciosEjemplar();
    ServicioPlanta S_planta = factoryServicios.getServiciosPlanta();
    ServicioMensaje S_mensaje = factoryServicios.getServiciosMensaje();
    ServicioCredenciales S_credenciales = factoryServicios.getServiciosCredenciales();
    ServicioPersona S_persona = factoryServicios.getServiciosPersona();
    
    public InvernaderoFachadaInvitado(InvernaderoFachadaPrincipal facade) {
        this.facade = facade;
    }
       
    public void invitado() {
        int opcion = -1;
        do {
            System.out.println("\n\n\n\n\n\t\t\t\tPERFIL INVITADO\n");
            System.out.println("\t\t\t\t1 - VER PLANTAS");
            System.out.println("\t\t\t\t2 - LOGIN");
            System.out.println("\t\t\t\t3 - SALIR DEL PROGRAMA");
            System.out.println("\t\t\t\t4 - VOLVER ATRAS");

            opcion = Utilidades.obtenerOpcionUsuario(4);

            switch (opcion) {
                case 1: {
                    mostrarPlantas();
                    break;
                }
                case 2: {
                    facade.login();
                    break;
                }
                case 3: {
                	Utilidades.salirdelprograma();
                }
            }
        } while (opcion != 3);
    }
    
    
    
    //MÉTODOS PARA LA GESTIÓN DE PLANTAS
	
    private void mostrarPlantas() {        
        Set<Planta> plantasSet = S_planta.find();
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

}
