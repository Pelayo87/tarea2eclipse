package com.dwes.dao;

import com.dwes.modelo.Mensaje;

public interface MensajeDAO {
    int insertar(Mensaje mensaje);
	
	int modificar(Mensaje mensaje);
	
	int eliminar(Mensaje mensaje);

}
