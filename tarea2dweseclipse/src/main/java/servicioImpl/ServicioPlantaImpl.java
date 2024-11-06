package servicioImpl;

import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import com.dwes.daoImpl.PlantaDAOImpl;
import com.dwes.modelo.Planta;
import com.dwes.servicios.ServicioPlanta;
import com.dwes.util.MySqlDAOFactory;

public class ServicioPlantaImpl implements ServicioPlanta{
	private PlantaDAOImpl pdi;
	private MySqlDAOFactory factory;
	
	private static final Pattern LETTERS_ONLY_PATTERN = Pattern.compile("^[a-zA-Z]+$");
	Scanner sc = new Scanner(System.in);
	

	public ServicioPlantaImpl() {
		factory=MySqlDAOFactory.getConexion();
		pdi=(PlantaDAOImpl)factory.getPlantaDAO();
	}

	@Override
	public int insertar(Planta planta) {
		// Validación del código
		String codigo;
		do {
		    codigo = planta.getCodigo();
		    
		    if (codigo == null || codigo.trim().isEmpty()) {
		        System.out.println("Ingrese el código de la planta (solo letras):");
		        codigo = sc.nextLine().trim().toUpperCase();
		        
		        // Valido que el código solo contenga letras
		        if (!codigo.matches("[a-zA-Z]+")) {
		            System.err.println("El código solo puede contener letras. Inténtelo de nuevo.");
		            codigo = "";
		        } else {
		            planta.setCodigo(codigo);
		        }
		    } else if (pdi.ExisteCodigo(codigo)) {
		        System.err.println("El código '" + codigo + "' ya está en uso. Por favor, ingrese un código diferente:");
		        codigo = sc.nextLine().trim().toUpperCase();
		        planta.setCodigo(codigo);
		    }
		} while (codigo == null || codigo.trim().isEmpty() || pdi.ExisteCodigo(codigo) || !codigo.matches("[a-zA-Z]+"));


	    // Validación del nombre común
	    String nombreComun;
	    do {
	        nombreComun = planta.getNombrecomun();
	        if (nombreComun == null || nombreComun.trim().isEmpty()) {
	            System.out.println("El nombre común de la planta no puede ser nulo o vacío. Por favor, ingrese un nombre válido:");
	            nombreComun = sc.nextLine().trim().toUpperCase();
	            planta.setNombrecomun(nombreComun);
	        } else if (!LETTERS_ONLY_PATTERN.matcher(nombreComun).matches()) {
	            System.err.println("El nombre común de la planta solo puede contener letras. Por favor, ingrese un nombre válido:");
	            nombreComun = sc.nextLine().trim().toUpperCase();
	            planta.setNombrecomun(nombreComun);
	        }
	    } while (nombreComun == null || nombreComun.trim().isEmpty() || !LETTERS_ONLY_PATTERN.matcher(nombreComun).matches());

	    // Validación del nombre científico
	    String nombreCientifico;
	    do {
	        nombreCientifico = planta.getNombrecientifico();
	        if (nombreCientifico == null || nombreCientifico.trim().isEmpty()) {
	            System.err.println("El nombre científico de la planta no puede ser nulo o vacío. Por favor, ingrese un nombre válido:");
	            nombreCientifico = sc.nextLine().trim().toUpperCase();
	            planta.setNombrecientifico(nombreCientifico);
	        } else if (!LETTERS_ONLY_PATTERN.matcher(nombreCientifico).matches()) {
	            System.err.println("El nombre científico de la planta solo puede contener letras. Por favor, ingrese un nombre válido:");
	            nombreCientifico = sc.nextLine().trim().toUpperCase();
	            planta.setNombrecientifico(nombreCientifico);
	        }
	    } while (nombreCientifico == null || nombreCientifico.trim().isEmpty() || !LETTERS_ONLY_PATTERN.matcher(nombreCientifico).matches());

	    try {
	        return pdi.insertar(planta);
	    } catch (Exception e) {
	        System.err.println("Error al insertar la planta: " + e.getMessage());
	        return -1; 
	    }
	}


    @Override
    public int modificar(Planta planta) {
    	// Validación del nombre común
	    String nombreComun;
	    do {
	        nombreComun = planta.getNombrecomun();
	        if (nombreComun == null || nombreComun.trim().isEmpty()) {
	            System.out.println("El nombre común de la planta no puede ser nulo o vacío. Por favor, ingrese un nombre válido:");
	            nombreComun = sc.nextLine().trim().toUpperCase();
	            planta.setNombrecomun(nombreComun);
	        } else if (!LETTERS_ONLY_PATTERN.matcher(nombreComun).matches()) {
	            System.err.println("El nombre común de la planta solo puede contener letras. Por favor, ingrese un nombre válido:");
	            nombreComun = sc.nextLine().trim().toUpperCase();
	            planta.setNombrecomun(nombreComun);
	        }
	    } while (nombreComun == null || nombreComun.trim().isEmpty() || !LETTERS_ONLY_PATTERN.matcher(nombreComun).matches());

	    // Validación del nombre científico
	    String nombreCientifico;
	    do {
	        nombreCientifico = planta.getNombrecientifico();
	        if (nombreCientifico == null || nombreCientifico.trim().isEmpty()) {
	            System.err.println("El nombre científico de la planta no puede ser nulo o vacío. Por favor, ingrese un nombre válido:");
	            nombreCientifico = sc.nextLine().trim().toUpperCase();
	            planta.setNombrecientifico(nombreCientifico);
	        } else if (!LETTERS_ONLY_PATTERN.matcher(nombreCientifico).matches()) {
	            System.err.println("El nombre científico de la planta solo puede contener letras. Por favor, ingrese un nombre válido:");
	            nombreCientifico = sc.nextLine().trim().toUpperCase();
	            planta.setNombrecientifico(nombreCientifico);
	        }
	    } while (nombreCientifico == null || nombreCientifico.trim().isEmpty() || !LETTERS_ONLY_PATTERN.matcher(nombreCientifico).matches());

	    try {
	        return pdi.insertar(planta);
	    } catch (Exception e) {
	        System.err.println("Error al insertar la planta: " + e.getMessage());
	        return -1; 
	    }
    }

    @Override
    public int eliminar(Planta planta) {
        if (planta == null || planta.getCodigo() == null || planta.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("La planta y su código no pueden ser null o vacíos.");
        }
        return pdi.eliminar(planta);
    }

    @Override
    public Planta findById(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede ser null o vacío.");
        }
        return pdi.findById(codigo);
    }

    @Override
    public Planta findByNombre(String nombrecomun) {
        if (nombrecomun == null || nombrecomun.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre común no puede ser null o vacío.");
        }
        return pdi.findByNombre(nombrecomun);
    }

	@Override
	public Set<Planta> find() {
		return pdi.find();
	}

	@Override
	public boolean ExisteCodigo(String codigo) {
		return pdi.ExisteCodigo(codigo);
	}

}
