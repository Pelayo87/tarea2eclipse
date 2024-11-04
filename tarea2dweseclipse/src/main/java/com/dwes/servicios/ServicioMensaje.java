package com.dwes.servicios;

import java.util.Set;

import com.dwes.modelo.Mensaje;

public interface ServicioMensaje {
	
    int insertar(Mensaje mensaje);
    
    int modificar(Mensaje mensaje);
    
    int eliminar(Mensaje mensaje);
    
    Mensaje findById(Long id);
    
    Set<Mensaje> findByEjemplarId(Long ejemplarId);
    
    Set<Mensaje> findAll();

}
