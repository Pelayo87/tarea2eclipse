package servicioImpl;

import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import com.dwes.daoImpl.PersonaDAOImpl;
import com.dwes.modelo.Persona;
import com.dwes.servicios.ServicioPersona;
import com.dwes.util.MySqlDAOFactory;

public class ServicioPersonaImpl implements ServicioPersona{
	private PersonaDAOImpl pedi;
	private MySqlDAOFactory factory;
	
	// Expresión regular para validar el email
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@[\\w-]+\\.[a-zA-Z]{2,4}$");
    public static long idPersona;
    
    private Scanner sc = new Scanner(System.in);
	

	public ServicioPersonaImpl() {
		factory=MySqlDAOFactory.getConexion();
		pedi=(PersonaDAOImpl)factory.getPersonaDAO();
	}

	@Override
	public int insertar(Persona persona) {

		String nombrePersona;
		String emailPersona;

		// Validación nombre real
		boolean nombreCorrecto = false;
		do {
			System.out.println("Introduce tu nombre real:");
			nombrePersona = sc.nextLine().trim();

			if (nombrePersona.isEmpty()) {
				System.err.println("El nombre no puede estar vacío. Inténtelo de nuevo.");
			} else if (!nombrePersona.matches("[a-zA-Z]+")) {
				System.err.println("El nombre solo debe contener letras. Inténtelo de nuevo.");
			} else if (pedi.ExistePersonaNombre(nombrePersona)) {
				System.err.println("Ya hay un'" + nombrePersona + "' en uso. Inténtelo de nuevo.");
			} else {
				nombreCorrecto = true;
			}
		} while (!nombreCorrecto);

		// Validación email
		boolean emailCorrecto = false;
		do {
			System.out.println("Introduce tu email (correo electrónico):");
			emailPersona = sc.nextLine().trim().toLowerCase();

			if (emailPersona.isEmpty()) {
				System.err.println("El email no puede estar vacío. Inténtelo de nuevo.");
			} else if (!EMAIL_PATTERN.matcher(emailPersona).matches()) {
				System.err.println("El email es inválido. Debe tener un formato correcto. Inténtelo de nuevo.");
			} else if (pedi.ExistePersonaCorreo(emailPersona)) {
				System.err.println("El correo '" + emailPersona + "' ya esta en uso. Inténtelo de nuevo.");
			} else {
				emailCorrecto = true;
			}
		} while (!emailCorrecto);

		// Crear instancia de Persona
		Persona nuevaPersona = new Persona();
		nuevaPersona.setNombre(nombrePersona);
		nuevaPersona.setEmail(emailPersona);

		// Llamada a la capa DAO
		return pedi.insertar(nuevaPersona);
	}

	@Override
	public int modificar(Persona persona) {
		String nuevoNombrePersona;
		String nuevoEmailPersona;
		System.out.println("Introduce el id de la persona que quieres modificar:");
		idPersona = Long.parseLong(sc.nextLine());
		Persona personaById = pedi.findById(idPersona);

		if (personaById != null) {
			boolean nuevonombreCorrecto = false;
			do {
				System.out.println("Introduce el nuevo nombre de la persona (actual: " + personaById.getNombre() + "):");
				nuevoNombrePersona = sc.nextLine();
				if (nuevoNombrePersona.isEmpty()) {
					System.err.println("El nombre no puede estar vacío. Inténtelo de nuevo.");
				} else if (!nuevoNombrePersona.matches("[a-zA-Z]+")) {
					System.err.println("El nombre solo debe contener letras. Inténtelo de nuevo.");
				} else if (pedi.ExistePersonaNombre(nuevoNombrePersona)) {
					System.err.println("Ya hay un'" + nuevoNombrePersona + "' en uso. Inténtelo de nuevo.");
				} else {
					nuevonombreCorrecto = true;
				}
			} while (!nuevonombreCorrecto);
			
			boolean nuevoemailCorrecto = false;
			do {
			System.out.println("Introduce el nuevo email de la persona (actual: " + personaById.getEmail() + "):");
			nuevoEmailPersona = sc.nextLine();
			if (nuevoEmailPersona.isEmpty()) {
				System.err.println("El email no puede estar vacío. Inténtelo de nuevo.");
			} else if (!EMAIL_PATTERN.matcher(nuevoEmailPersona).matches()) {
				System.err.println("El email es inválido. Debe tener un formato correcto. Inténtelo de nuevo.");
			} else if (pedi.ExistePersonaCorreo(nuevoEmailPersona)) {
				System.err.println("El correo '" + nuevoEmailPersona + "' ya esta en uso. Inténtelo de nuevo.");
			} else {
				nuevoemailCorrecto = true;
			}
		} while (!nuevoemailCorrecto);

			personaById.setNombre(nuevoNombrePersona);
			personaById.setEmail(nuevoEmailPersona);
			return pedi.modificar(personaById);
		} else {
			System.out.println("No se encontró ninguna persona con el ID: " + idPersona);
			return 0;
		}
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
	public boolean ExistePersonaNombre(String nombre) {
		return pedi.ExistePersonaNombre(nombre);
	}

	@Override
	public boolean ExistePersonaCorreo(String email) {
		return pedi.ExistePersonaCorreo(email);
	}
}
