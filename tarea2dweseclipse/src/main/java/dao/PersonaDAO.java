package dao;

import modelo.Persona;

public interface PersonaDAO {
    int insertar(Persona persona);
	
	int modificar(Persona persona);
	
	int eliminar(Persona persona);

}
