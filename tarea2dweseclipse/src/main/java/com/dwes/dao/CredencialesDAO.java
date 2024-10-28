package com.dwes.dao;

import com.dwes.modelo.Credenciales;

public interface CredencialesDAO {
    int insertar(Credenciales credenciales);
	
	int modificar(Credenciales credenciales);
	
	int eliminar(Credenciales credenciales);

}
