package com.dwes.dao;

import java.util.List;
import java.util.Set;
import com.dwes.modelo.Ejemplar;

public interface EjemplarDAO {
    int insertar(Ejemplar ejemplar);
    
    int modificar(Ejemplar ejemplar);
    
    int eliminar(Ejemplar ejemplar);
    
    Ejemplar findById(Long id);
    
    List<Ejemplar> findByPlanta(String codigo);
    
    Set<Ejemplar> findAll();
    
}


