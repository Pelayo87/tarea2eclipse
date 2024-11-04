package servicioImpl;

import java.util.Set;
import com.dwes.daoImpl.CredencialesDAOImpl;
import com.dwes.modelo.Credenciales;
import com.dwes.servicios.ServicioCredenciales;
import com.dwes.util.MySqlDAOFactory;

public class ServicioCredencialesImpl implements ServicioCredenciales{
	private CredencialesDAOImpl cdi;
	private MySqlDAOFactory factory;
	

	public ServicioCredencialesImpl() {
		factory=MySqlDAOFactory.getConexion();
		cdi=(CredencialesDAOImpl) factory.getCredencialesDAO();
	}
	
	public boolean autenticar(Credenciales credencialesIngresadas) {
        Set<Credenciales> credenciales = cdi.find();
        return credenciales.stream().anyMatch(c -> 
            c.getUsuario().equalsIgnoreCase(credencialesIngresadas.getUsuario()) &&
            c.getPassword().equals(credencialesIngresadas.getPassword()));
    }

	@Override
	public int insertar(Credenciales credenciales) {
		return cdi.insertar(credenciales);
	}

	@Override
	public int modificar(Credenciales credenciales) {
		return cdi.modificar(credenciales);
	}

	@Override
	public int eliminar(Credenciales credenciales) {
		return cdi.eliminar(credenciales);
	}

	@Override
	public Credenciales findById(Long id) {
		return cdi.findById(id);
	}

	@Override
	public Credenciales findByPersonaId(Long personaId) {
		return cdi.findByPersonaId(personaId);
	}

	@Override
	public Credenciales findByUsuario(String usuario) {
		return cdi.findByUsuario(usuario);
	}

	@Override
	public Set<Credenciales> find() {
		return cdi.find();
	}

}
