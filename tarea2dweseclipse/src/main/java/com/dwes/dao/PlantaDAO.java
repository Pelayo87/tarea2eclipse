package com.dwes.dao;

import java.util.Set;
import com.dwes.modelo.Planta;
import com.dwes.modelo.Ejemplar;

public interface PlantaDAO {
    int insertar(Planta planta);
    
    int modificar(Planta planta);
    
    int eliminar(Planta planta);
    
    Planta findById(String codigo);
    
    Planta findByNombre(String nombrecomun);
    
    Planta findByNombreCientifico(String nombrecientifico);
    
    Set<Ejemplar> findEjemplaresByPlanta(String codigo);
    
    int contarEjemplares(String codigo);
    
    Set<Planta> find();
}

