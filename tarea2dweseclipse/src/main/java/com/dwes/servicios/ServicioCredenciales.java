package com.dwes.servicios;

import com.dwes.modelo.Credenciales;

public interface ServicioCredenciales {
	
    int insertar(Credenciales credenciales);
	
	int modificar(Credenciales credenciales);
	
	int eliminar(Credenciales credenciales);

}
