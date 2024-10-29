package servicioImpl;

import com.dwes.daoImpl.CredencialesDAOImpl;
import com.dwes.modelo.Credenciales;
import com.dwes.servicios.ServicioCredenciales;

public class ServicioCredencialesImpl implements ServicioCredenciales{
	private CredencialesDAOImpl cdi;

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

}
