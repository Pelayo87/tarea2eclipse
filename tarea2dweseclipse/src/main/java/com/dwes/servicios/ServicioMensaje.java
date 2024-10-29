package com.dwes.servicios;

import com.dwes.modelo.Mensaje;

public interface ServicioMensaje {
	
    int insertar(Mensaje mensaje);
	
	int modificar(Mensaje mensaje);
	
	int eliminar(Mensaje mensaje);

}
