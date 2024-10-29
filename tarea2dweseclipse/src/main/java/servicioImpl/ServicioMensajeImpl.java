package servicioImpl;

import com.dwes.daoImpl.MensajeDAOImpl;
import com.dwes.modelo.Mensaje;
import com.dwes.servicios.ServicioMensaje;

public class ServicioMensajeImpl implements ServicioMensaje{
	private MensajeDAOImpl mdi;

	@Override
	public int insertar(Mensaje mensaje) {
		return mdi.insertar(mensaje);
	}

	@Override
	public int modificar(Mensaje mensaje) {
		return mdi.modificar(mensaje);
	}

	@Override
	public int eliminar(Mensaje mensaje) {
		return mdi.eliminar(mensaje);
	}

}
