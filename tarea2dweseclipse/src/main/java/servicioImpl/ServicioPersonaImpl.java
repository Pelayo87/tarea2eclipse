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
    
    private Scanner sc = new Scanner(System.in);
	

	public ServicioPersonaImpl() {
		factory=MySqlDAOFactory.getConexion();
		pedi=(PersonaDAOImpl)factory.getPersonaDAO();
	}

	@Override
	public int insertar(Persona persona) {
		// Validación nombre real
	    String nombre;
	    do {
	        System.out.println("Introduce tu nombre real:");
	        nombre = sc.nextLine().trim();
	        
	        if (nombre.isEmpty()) {
	            System.err.println("El nombre no puede estar vacío. Inténtelo de nuevo.");
	        } else if (!nombre.matches("[a-zA-Z]+")) {
	            System.err.println("El nombre solo debe contener letras. Inténtelo de nuevo.");
	            nombre = "";
	        } else {
	            persona.setNombre(nombre);
	        }
	        
	    } while (nombre.isEmpty() || !nombre.matches("[a-zA-Z]+"));
	    
	    // Validación email
	    String email;
	    do {
	        System.out.println("Introduce tu email (correo electrónico):");
	        email = sc.nextLine().trim();
	        
	        if (email.isEmpty()) {
	            System.err.println("El email no puede estar vacío. Inténtelo de nuevo.");
	        } else if (!EMAIL_PATTERN.matcher(email).matches()) {
	            System.err.println("El email es inválido. Debe tener un formato correcto. Inténtelo de nuevo.");
	            email = "";
	        } else {
	            persona.setEmail(email);
	        }
	        
	    } while (email.isEmpty() || !EMAIL_PATTERN.matcher(email).matches());
	    
	    // Llamada a la capa DAO
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
}
