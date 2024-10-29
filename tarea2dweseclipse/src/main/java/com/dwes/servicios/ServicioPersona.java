package com.dwes.servicios;

import com.dwes.modelo.Persona;

public interface ServicioPersona {
	
    int insertar(Persona persona);
	
	int modificar(Persona persona);
	
	int eliminar(Persona persona);

}
