package dao;

import modelo.Credenciales;

public interface CredencialesDAO {
    int insertar(Credenciales credenciales);
	
	int modificar(Credenciales credenciales);
	
	int eliminar(Credenciales credenciales);

}
