package servicioImpl;

import java.util.Scanner;
import java.util.Set;
import com.dwes.daoImpl.CredencialesDAOImpl;
import com.dwes.daoImpl.PersonaDAOImpl;
import com.dwes.modelo.Credenciales;
import com.dwes.servicios.ServicioCredenciales;
import com.dwes.util.MySqlDAOFactory;

public class ServicioCredencialesImpl implements ServicioCredenciales{
	private CredencialesDAOImpl cdi;
	private MySqlDAOFactory factory;
	private Scanner sc = new Scanner(System.in);
	

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
	    String nombreUsuario;
	    String password;

	    // Validación nombre de usuario
	    boolean nombreCorrecto = false;
	    do {
	        System.out.println("Introduce un nombre de usuario:");
	        nombreUsuario = sc.nextLine().trim();

	        if (nombreUsuario == null || nombreUsuario.isEmpty()) {
	            System.err.println("El nombre de usuario no puede ser nulo o vacío. Inténtelo de nuevo.");
	        } else if (nombreUsuario.length() < 4) {
	            System.err.println("El nombre de usuario debe tener al menos 4 caracteres. Inténtelo de nuevo.");
	        } else if (!nombreUsuario.matches("[a-zA-Z0-9]+")) {
	            System.err.println("El nombre de usuario solo debe contener letras y números, sin espacios. Inténtelo de nuevo.");
	        } else if (!nombreUsuario.matches(".*[a-zA-Z].*")) {
	            System.err.println("El nombre de usuario debe contener al menos una letra. Inténtelo de nuevo.");
	        } else if (!nombreUsuario.matches(".*[0-9].*")) {
	            System.err.println("El nombre de usuario debe contener al menos un número. Inténtelo de nuevo.");
	        } else {
	            nombreCorrecto = true;
	        }

	    } while (!nombreCorrecto);

	    // Validación contraseña
	    boolean passwordCorrecto = false;
	    do {
	        System.out.println("Introduce una contraseña:");
	        password = sc.nextLine().trim();

	        if (password == null || password.isEmpty()) {
	            System.err.println("La contraseña no puede ser nula o vacía. Inténtelo de nuevo.");
	        } else if (password.length() < 8) {
	            System.err.println("La contraseña debe tener al menos 8 caracteres. Inténtelo de nuevo.");
	        } else if (password.contains(" ")) {
	            System.err.println("La contraseña no puede contener espacios en blanco. Inténtelo de nuevo.");
	        } else if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
	            System.err.println("La contraseña debe contener al menos un carácter especial. Inténtelo de nuevo.");
	        } else {
	            passwordCorrecto = true;
	        }

	    } while (!passwordCorrecto);

	        credenciales.setUsuario(nombreUsuario);
	        credenciales.setPassword(password);
	        credenciales.setId_persona(PersonaDAOImpl.personaId);
	        //Guardo las credenciales en la BD
	        return cdi.insertar(credenciales);
	}



	@Override
	public int modificar(Credenciales credenciales) {
		String nuevonombreUsuario;
		String nuevaPassword;
		
		// Validación nombre de usuario
	    boolean nombreCorrecto = false;
	    do {
	        System.out.println("Introduce un nuevo nombre de usuario:");
	        nuevonombreUsuario = sc.nextLine().trim();

	        if (nuevonombreUsuario == null || nuevonombreUsuario.isEmpty()) {
	            System.err.println("El nombre de usuario no puede ser nulo o vacío. Inténtelo de nuevo.");
	        } else if (nuevonombreUsuario.length() < 4) {
	            System.err.println("El nombre de usuario debe tener al menos 4 caracteres. Inténtelo de nuevo.");
	        } else if (!nuevonombreUsuario.matches("[a-zA-Z0-9]+")) {
	            System.err.println("El nombre de usuario solo debe contener letras y números, sin espacios. Inténtelo de nuevo.");
	        } else if (!nuevonombreUsuario.matches(".*[a-zA-Z].*")) {
	            System.err.println("El nombre de usuario debe contener al menos una letra. Inténtelo de nuevo.");
	        } else if (!nuevonombreUsuario.matches(".*[0-9].*")) {
	            System.err.println("El nombre de usuario debe contener al menos un número. Inténtelo de nuevo.");
	        } else {
	            nombreCorrecto = true;
	        }

	    } while (!nombreCorrecto);

	    // Validación contraseña
	    boolean passwordCorrecto = false;
	    do {
	        System.out.println("Introduce una nueva contraseña:");
	        nuevaPassword = sc.nextLine().trim();

	        if (nuevaPassword == null || nuevaPassword.isEmpty()) {
	            System.err.println("La contraseña no puede ser nula o vacía. Inténtelo de nuevo.");
	        } else if (nuevaPassword.length() < 8) {
	            System.err.println("La contraseña debe tener al menos 8 caracteres. Inténtelo de nuevo.");
	        } else if (nuevaPassword.contains(" ")) {
	            System.err.println("La contraseña no puede contener espacios en blanco. Inténtelo de nuevo.");
	        } else if (!nuevaPassword.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
	            System.err.println("La contraseña debe contener al menos un carácter especial. Inténtelo de nuevo.");
	        } else {
	            passwordCorrecto = true;
	        }

	    } while (!passwordCorrecto);
	    credenciales.setUsuario(nuevonombreUsuario);
        credenciales.setPassword(nuevaPassword);
        credenciales.setId_persona(ServicioPersonaImpl.idPersona);
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
