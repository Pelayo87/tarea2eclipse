package servicioImpl;

import java.util.Scanner;
import java.util.Set;
import com.dwes.daoImpl.CredencialesDAOImpl;
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
		
		// Validación nombre de usuario
	    String nombreUsuario;
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
	            nombreUsuario="";
	        } else {
	            credenciales.setUsuario(nombreUsuario);
	        }
	        
	    } while (nombreUsuario == null || nombreUsuario.isEmpty() || nombreUsuario.length() < 4 || !nombreUsuario.matches("[a-zA-Z0-9]+"));
	    
		//Validación contraseña usuario
	    String contraseña;
	    do {
	    	System.out.println("Introduce un nombre de usuario:");
	    	contraseña = sc.nextLine().trim();
		    if (contraseña == null || contraseña.isEmpty()) {
		    	System.err.println("El nombre no puede ser nulo o vacío. Inténtelo de nuevo.");
		    }else if (contraseña.length() < 8) {
		    	System.err.println("La contraseña debe tener al menos 8 caracteres. Inténtelo de nuevo.");
		    }else if (!contraseña.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
		    	System.err.println("El nombre no puede ser nulo o vacío. Inténtelo de nuevo.");
		    	contraseña = ""; 
		    }else {
	            credenciales.setPassword(contraseña);
	        }	    	
	    }while(contraseña==null || contraseña.isEmpty());

	   	// Llamada a la capa DAO
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
