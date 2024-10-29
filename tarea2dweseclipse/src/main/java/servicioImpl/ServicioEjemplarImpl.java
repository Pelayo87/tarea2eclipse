package servicioImpl;

import com.dwes.daoImpl.EjemplarDAOImpl;
import com.dwes.modelo.Ejemplar;
import com.dwes.servicios.ServicioEjemplar;

public class ServicioEjemplarImpl implements ServicioEjemplar{
	private EjemplarDAOImpl edi;

	@Override
	public int insertar(Ejemplar ejemplar) {
		 return edi.insertar(ejemplar);
	}

	@Override
	public int modificar(Ejemplar ejemplar) {
		return edi.modificar(ejemplar);
	}

	@Override
	public int eliminar(Ejemplar ejemplar) {
		return edi.eliminar(ejemplar);
	}

	@Override
	public Ejemplar findById(Long id) {
		return edi.findById(id);
	}

}
