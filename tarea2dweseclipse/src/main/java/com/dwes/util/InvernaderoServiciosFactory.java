package com.dwes.util;

import com.dwes.servicios.ServicioCredenciales;
import com.dwes.servicios.ServicioEjemplar;
import com.dwes.servicios.ServicioMensaje;
import com.dwes.servicios.ServicioPersona;
import com.dwes.servicios.ServicioPlanta;

import servicioImpl.ServicioCredencialesImpl;
import servicioImpl.ServicioEjemplarImpl;
import servicioImpl.ServicioMensajeImpl;
import servicioImpl.ServicioPersonaImpl;
import servicioImpl.ServicioPlantaImpl;

public class InvernaderoServiciosFactory {
	public static InvernaderoServiciosFactory servicios;
	
	public static InvernaderoServiciosFactory getServicios() {
		if (servicios ==null)
			servicios=new InvernaderoServiciosFactory();
		return servicios;
	}
	
	public ServicioEjemplar getServiciosEjemplar() {
		return new ServicioEjemplarImpl();
	}
	
	public ServicioPlanta getServiciosPlanta() {
		return new ServicioPlantaImpl();
	}
	
	public ServicioMensaje getServiciosMensaje() {
		return new ServicioMensajeImpl();
	}
	
	public ServicioCredenciales getServiciosCredenciales() {
		return new ServicioCredencialesImpl();
	}
	
	public ServicioPersona getServiciosPersona() {
		return new ServicioPersonaImpl();
	}

}
