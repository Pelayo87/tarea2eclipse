package com.dwes.servicios;

import java.util.Set;

import com.dwes.modelo.Credenciales;

public interface ServicioCredenciales {
	
    int insertar(Credenciales credenciales);
    
    int modificar(Credenciales credenciales);
    
    int eliminar(Credenciales credenciales);
    
    Credenciales findById(Long id);
    
    Credenciales findByPersonaId(Long personaId);
    
    Credenciales findByUsuario(String usuario);
    
    Set<Credenciales> find();

}
