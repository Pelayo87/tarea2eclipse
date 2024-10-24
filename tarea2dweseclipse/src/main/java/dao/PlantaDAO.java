package dao;

import java.util.Set;
import modelo.Planta;

public interface PlantaDAO {
	int insertar(Planta planta);
	
	int modificar(Planta planta);
	
	int eliminar(Planta planta);
	
	Planta findById(String codigo);
	Planta findByNombre(String nombrecomun);
	
	Set<Planta> find();
	
	

}
