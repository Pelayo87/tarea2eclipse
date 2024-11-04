package servicioImpl;

import java.util.Set;
import com.dwes.daoImpl.PlantaDAOImpl;
import com.dwes.modelo.Planta;
import com.dwes.servicios.ServicioPlanta;
import com.dwes.util.MySqlDAOFactory;

public class ServicioPlantaImpl implements ServicioPlanta{
	private PlantaDAOImpl pdi;
	private MySqlDAOFactory factory;
	

	public ServicioPlantaImpl() {
		factory=MySqlDAOFactory.getConexion();
		pdi=(PlantaDAOImpl)factory.getPlantaDAO();
	}


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
		return pdi.findById(codigo);
	}

	@Override
	public Planta findByNombre(String nombrecomun) {
		return pdi.findByNombre(nombrecomun);
	}

	@Override
	public Set<Planta> find() {
		return pdi.find();
	}

}
