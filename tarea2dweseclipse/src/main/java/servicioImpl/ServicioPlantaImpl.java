package servicioImpl;

import java.util.Set;

import com.dwes.daoImpl.PlantaDAOImpl;
import com.dwes.modelo.Planta;
import com.dwes.servicios.ServicioPlanta;

public class ServicioPlantaImpl implements ServicioPlanta{
	private PlantaDAOImpl pdi;

	@Override
	public int insertar(Planta planta) {
		return pdi.insertar(planta);
	}

	@Override
	public int modificar(Planta planta) {
		return pdi.modificar(planta);
	}

	@Override
	public int eliminar(Planta planta) {
		return pdi.eliminar(planta);
	}

	@Override
	public Planta findById(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Planta findByNombre(String nombrecomun) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Planta> find() {
		// TODO Auto-generated method stub
		return null;
	}

}
