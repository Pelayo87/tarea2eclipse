package com.dwes.dao;

import com.dwes.modelo.Ejemplar;

public interface EjemplarDAO {
    int insertar(Ejemplar ejemplar);
    
    int modificar(Ejemplar ejemplar);
    
    int eliminar(Ejemplar ejemplar);
    
    Ejemplar findById(Long id);
}

