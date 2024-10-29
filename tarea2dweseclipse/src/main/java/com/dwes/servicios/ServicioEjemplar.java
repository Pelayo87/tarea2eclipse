package com.dwes.servicios;

import com.dwes.modelo.Ejemplar;

public interface ServicioEjemplar {
	
    int insertar(Ejemplar ejemplar);
    
    int modificar(Ejemplar ejemplar);
    
    int eliminar(Ejemplar ejemplar);
    
    Ejemplar findById(Long id);

}
