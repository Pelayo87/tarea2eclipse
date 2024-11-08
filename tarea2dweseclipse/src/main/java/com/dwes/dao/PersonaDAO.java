package com.dwes.dao;

import java.util.Set;
import com.dwes.modelo.Persona;

public interface PersonaDAO {

    int insertar(Persona persona);
    
    int modificar(Persona persona);
    
    int eliminar(Persona persona);
    
    Persona findById(Long id);
    
    Persona findByNombre(String nombre);
    
    Set<Persona> findAll();
    
    boolean ExistePersonaNombre(String nombre);
    
    boolean ExistePersonaCorreo(String email);
}
