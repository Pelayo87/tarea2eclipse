package com.dwes.servicios;

import java.util.Set;

import com.dwes.modelo.Persona;

public interface ServicioPersona {
	
    int insertar(Persona persona);
    
    int modificar(Persona persona);
    
    int eliminar(Persona persona);
    
    Persona findById(Long id);
    
    Persona findByNombre(String nombre);
    
    Set<Persona> findAll();
    
    Persona findWithCredenciales(Long id);
    
    Persona findWithEjemplares(Long id);

}
