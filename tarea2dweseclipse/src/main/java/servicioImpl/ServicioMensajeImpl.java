package servicioImpl;

import java.sql.Date;
import java.util.Set;
import com.dwes.daoImpl.MensajeDAOImpl;
import com.dwes.modelo.Mensaje;
import com.dwes.servicios.ServicioMensaje;
import com.dwes.util.MySqlDAOFactory;

public class ServicioMensajeImpl implements ServicioMensaje{
	private MensajeDAOImpl mdi;
	private MySqlDAOFactory factory;
	
	public ServicioMensajeImpl() {
		factory=MySqlDAOFactory.getConexion();
		mdi=(MensajeDAOImpl)factory.getMensajeDAO();
	}

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

	@Override
	public Mensaje findById(Long id) {
		return mdi.findById(id);
	}
	
	@Override
	public Mensaje findByFecha(Date fechahora) {
		return mdi.findByFecha(fechahora);
	}

	@Override
	public Set<Mensaje> findByEjemplarId(Long ejemplarId) {
		return mdi.findByEjemplarId(ejemplarId);
	}

	@Override
	public Set<Mensaje> findAll() {
		return mdi.findAll();
	}

	@Override
	public Set<Mensaje> findByPersonaId(Long personaId) {
		return mdi.findByPersonaId(personaId);
	}

	

}
