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
		
	    // Validar nombre
	    String nombre;
	    do {
	    	nombre=persona.getNombre();
	        if (nombre.isEmpty()) {
	            System.err.println("El nombre no puede ser nulo o vacío. Inténtelo de nuevo.");
	            persona.setNombre(nombre);
	        }
	    } while (nombre.isEmpty());
	    
	    // Validar email
	    String email;
	    do {
	        System.err.println("Ingrese el email de la persona:");
	        email = sc.nextLine().trim();
	        if (email.isEmpty() || !EMAIL_PATTERN.matcher(email).matches()) {
	            System.err.println("El email es inválido. Debe tener un formato correcto. Inténtelo de nuevo.");
	            persona.setEmail(email);
	        }
	    } while (email.isEmpty() || !EMAIL_PATTERN.matcher(email).matches());
	   
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
