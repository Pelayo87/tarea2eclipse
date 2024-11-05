package com.dwes.servicios;

import java.util.List;
import java.util.Set;

import com.dwes.modelo.Ejemplar;

public interface ServicioEjemplar {
	
    int insertar(Ejemplar ejemplar);
    
    int modificar(Ejemplar ejemplar);
    
    int eliminar(Ejemplar ejemplar);
    
    Ejemplar findById(Long id);
    
    Set<Ejemplar> findAll();
    
    List<Ejemplar> findByPlanta(List<String> tiposPlanta);
    
    Ejemplar findWithPersonas(Long id);
    
    Ejemplar findWithMensajes(Long id);
}
