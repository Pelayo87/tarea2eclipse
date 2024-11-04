package fachada;

import com.dwes.servicios.ServicioCredenciales;
import com.dwes.servicios.ServicioEjemplar;
import com.dwes.servicios.ServicioMensaje;
import com.dwes.servicios.ServicioPersona;
import com.dwes.servicios.ServicioPlanta;

import com.dwes.util.InvernaderoServiciosFactory;
import com.dwes.util.MySqlDAOFactory;

public class InvernaderoFachada {
    private static InvernaderoFachada portal;
	
	MySqlDAOFactory factoryDAO = MySqlDAOFactory.getConexion();
	InvernaderoServiciosFactory factoryServicios = InvernaderoServiciosFactory.getServicios(); 
		
	ServicioEjemplar S_ejemplar = factoryServicios.getServiciosEjemplar();
	ServicioPlanta S_planta = factoryServicios.getServiciosPlanta();
	ServicioMensaje S_mensaje = factoryServicios.getServiciosMensaje();
	ServicioCredenciales S_credenciales = factoryServicios.getServiciosCredenciales();
	ServicioPersona S_persona = factoryServicios.getServiciosPersona();

}
