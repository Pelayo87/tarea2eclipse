package servicioImpl;

import java.util.Set;

import com.dwes.daoImpl.PersonaDAOImpl;
import com.dwes.modelo.Persona;
import com.dwes.servicios.ServicioPersona;
import com.dwes.util.MySqlDAOFactory;

public class ServicioPersonaImpl implements ServicioPersona{
	private PersonaDAOImpl pedi;
	private MySqlDAOFactory factory;
	

	public ServicioPersonaImpl() {
		factory=MySqlDAOFactory.getConexion();
		pedi=(PersonaDAOImpl)factory.getPersonaDAO();
	}

	@Override
	public int insertar(Persona persona) {
		return pedi.insertar(persona);
	}

	@Override
	public int modificar(Persona persona) {
		return pedi.modificar(persona);
	}

	@Override
	public int eliminar(Persona persona) {
		return pedi.eliminar(persona);
	}

	@Override
	public Persona findById(Long id) {
		return pedi.findById(id);
	}

	@Override
	public Persona findByNombre(String nombre) {
		return pedi.findByNombre(nombre);
	}

	@Override
	public Set<Persona> findAll() {
		return pedi.findAll();
	}

	@Override
	public Persona findWithCredenciales(Long id) {
		return pedi.findWithCredenciales(id);
	}

	@Override
	public Persona findWithEjemplares(Long id) {
		return pedi.findWithEjemplares(id);
	}

}
