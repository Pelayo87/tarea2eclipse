package com.dwes.dao;

import com.dwes.modelo.Persona;

public interface PersonaDAO {
	
    public int insertar(Persona persona);
	
	public int modificar(Persona persona);
	
	public int eliminar(Persona persona);

}
