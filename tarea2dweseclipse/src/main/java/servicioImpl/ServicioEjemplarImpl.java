package servicioImpl;

import java.util.Set;

import com.dwes.daoImpl.EjemplarDAOImpl;
import com.dwes.modelo.Ejemplar;
import com.dwes.servicios.ServicioEjemplar;
import com.dwes.util.MySqlDAOFactory;

public class ServicioEjemplarImpl implements ServicioEjemplar{
	private EjemplarDAOImpl edi;
	private MySqlDAOFactory factory;
	

	public ServicioEjemplarImpl() {
		factory=MySqlDAOFactory.getConexion();
		edi=(EjemplarDAOImpl)factory.getEjemplarDAO();
	}

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

	@Override
	public Set<Ejemplar> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ejemplar findWithPlanta(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ejemplar findWithPersonas(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ejemplar findWithMensajes(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}