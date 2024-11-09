package servicioImpl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import com.dwes.daoImpl.MensajeDAOImpl;
import com.dwes.modelo.Mensaje;
import com.dwes.servicios.ServicioMensaje;
import com.dwes.util.MySqlDAOFactory;

public class ServicioMensajeImpl implements ServicioMensaje{
	private static MensajeDAOImpl mdi;
	private MySqlDAOFactory factory;
	static Scanner sc = new Scanner(System.in);
	
	// FECHA ACTUAL Y FORMATEADA
	java.util.Date fechaActual = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
	DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	String fechaFormateada = formatoFecha.format(fechaActual);
	
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
	
	public static void filtrarAnotacionesporRangoFecha() {
		try {
			// Ingreso y conversión de la primera fecha
			System.out.print("Fecha 1 (dd/MM/yyyy): ");
			java.util.Date fecha1 = formatoFecha.parse(sc.nextLine());
			LocalDateTime fecha1Local = fecha1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

			// Ingreso y conversión de la segunda fecha
			System.out.print("Fecha 2 (dd/MM/yyyy): ");
			java.util.Date fecha2 = formatoFecha.parse(sc.nextLine());
			LocalDateTime fecha2Local = fecha2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().withHour(23).withMinute(59).withSecond(59);

			// Obtengo todos los mensajes
			Set<Mensaje> listaMensajes = mdi.findAll();

			// Filtra los mensajes en el rango de fecha1 a fecha2
			Set<Mensaje> mensajesFiltrados = listaMensajes.stream().filter(mensaje -> {
				LocalDateTime fechaMensaje = mensaje.getFechahora().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDateTime();
				return !fechaMensaje.isBefore(fecha1Local) && !fechaMensaje.isAfter(fecha2Local);
			}).collect(Collectors.toSet());

			for (Mensaje m : mensajesFiltrados) {
				System.out.println("El dia " + m.getFechahora() + "fue realizado el siguiente mensaje/anotación:" + m.getMensaje());
			}
		} catch (ParseException e) {
			System.err.println("Formato de fecha incorrecto. Usa el formato dd/MM/yyyy.");
			sc.nextLine();
		}
	}
	
	

	

}
