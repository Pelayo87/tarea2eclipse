package com.dwes.dao;

import java.sql.Date;
import java.util.Set;
import com.dwes.modelo.Mensaje;

public interface MensajeDAO {
    int insertar(Mensaje mensaje);
    
    int modificar(Mensaje mensaje);
    
    int eliminar(Mensaje mensaje);
    
    Mensaje findById(Long id);
    
    Mensaje findByFecha(Date fechahora);
    
    Set<Mensaje> findByEjemplarId(Long ejemplarId);
    
    Set<Mensaje> findByPersonaId(Long personaId);
    
    Set<Mensaje> findAll();
}

