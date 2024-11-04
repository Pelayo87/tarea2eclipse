package com.dwes.servicios;

import java.util.Set;

import com.dwes.modelo.Planta;

public interface ServicioPlanta {
	
    int insertar(Planta planta);
	
	int modificar(Planta planta);
	
	int eliminar(Planta planta);
	
	Planta findById(String codigo);
	
	Planta findByNombre(String nombrecomun);
	
	Set<Planta> find();
	
	 boolean ExisteCodigo(String codigo);
}
