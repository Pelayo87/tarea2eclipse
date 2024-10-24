package dao;

import modelo.Ejemplar;

public interface EjemplarDAO {
    int insertar(Ejemplar ejemplar);
    
    int modificar(Ejemplar ejemplar);
    
    int eliminar(Ejemplar ejemplar);
    
    Ejemplar findById(Long id);
}

