package com.dwes.dao;

import com.dwes.modelo.Mensaje;

public interface MensajeDAO {
    public int insertar(Mensaje mensaje);
	
	public int modificar(Mensaje mensaje);
	
	public int eliminar(Mensaje mensaje);

}
