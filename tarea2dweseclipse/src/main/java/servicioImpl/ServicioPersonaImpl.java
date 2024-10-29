package servicioImpl;

import com.dwes.daoImpl.PersonaDAOImpl;
import com.dwes.modelo.Persona;
import com.dwes.servicios.ServicioPersona;

public class ServicioPersonaImpl implements ServicioPersona{
	private PersonaDAOImpl pdi;

	@Override
	public int insertar(Persona persona) {
		return pdi.insertar(persona);
	}

	@Override
	public int modificar(Persona persona) {
		return pdi.modificar(persona);
	}

	@Override
	public int eliminar(Persona persona) {
		return pdi.eliminar(persona);
	}

}
