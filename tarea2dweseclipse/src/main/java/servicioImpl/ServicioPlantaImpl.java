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
		String codigo;
		String nombrecomun;
		String nombrecientifico;

		// Validación del código de la planta
		boolean codigoCorrecto = false;
		do {
			System.out.println("Dame el código de la nueva planta:");
			codigo = sc.nextLine().trim().toUpperCase();

			if (codigo == null || codigo.isEmpty()) {
				System.err.println("El código no puede ser nulo o vacío. Inténtelo de nuevo.");
			} else if (!codigo.matches("[a-zA-Z]+")) {
				System.err.println("El código solo puede contener letras. Inténtelo de nuevo.");
			} else if (pdi.ExistePlanta(codigo)) {
				System.err.println("El código '" + codigo + "' ya está en uso. Inténtelo de nuevo.");
			} else {
				codigoCorrecto = true;
			}
		} while (!codigoCorrecto);

		// Validación del nombre común
		boolean nombreComunCorrecto = false;
		do {
			System.out.println("Dame el nombre común de la planta:");
			nombrecomun = sc.nextLine().trim().toUpperCase();
			if (nombrecomun == null || nombrecomun.trim().isEmpty()) {
				System.err.println("El nombre común de la planta no puede ser nulo o vacío.");
			} else if (!LETTERS_ONLY_PATTERN.matcher(nombrecomun).matches()) {
				System.err.println("El nombre común de la planta solo puede contener letras.");
			} else {
				nombreComunCorrecto = true;
			}
		} while (!nombreComunCorrecto);

		// Validación del nombre científico
		boolean nombreCientificoCorrecto = false;
		do {
			System.out.println("Dame el nombre científico de la planta:");
			nombrecientifico = sc.nextLine().trim().toUpperCase();
			if (nombrecientifico == null || nombrecientifico.trim().isEmpty()) {
				System.err.println("El nombre científico de la planta no puede ser nulo o vacío.");
			} else if (!LETTERS_ONLY_PATTERN.matcher(nombrecientifico).matches()) {
				System.err.println("El nombre científico de la planta solo puede contener letras.");
			} else {
				nombreCientificoCorrecto = true;
			}
		} while (!nombreCientificoCorrecto);

		Planta nuevaplanta = new Planta(codigo, nombrecomun, nombrecientifico);

		return pdi.insertar(nuevaplanta);
	}


    @Override
    public int modificar(Planta planta) {
    	
    	String codigo;
		String nombrecomun;
		String nombrecientifico;

		// Validación del código de la planta
		boolean codigoCorrecto = false;
		do {
			System.out.println("Dame el código de la nueva planta:");
			codigo = sc.nextLine().trim().toUpperCase();

			if (codigo == null || codigo.isEmpty()) {
				System.err.println("El código no puede ser nulo o vacío. Inténtelo de nuevo.");
			} else if (!codigo.matches("[a-zA-Z]+")) {
				System.err.println("El código solo puede contener letras. Inténtelo de nuevo.");
			} else if (!pdi.ExistePlanta(codigo)) {
				System.err.println("El código '" + codigo + "' no existe. Inténtelo de nuevo.");
			} else {
				codigoCorrecto = true;
			}
		} while (!codigoCorrecto);

		// Validación del nombre común
		boolean nombreComunCorrecto = false;
		do {
			System.out.println("Dame el nombre común de la planta:");
			nombrecomun = sc.nextLine().trim().toUpperCase();
			if (nombrecomun == null || nombrecomun.trim().isEmpty()) {
				System.err.println("El nombre común de la planta no puede ser nulo o vacío.");
			} else if (!LETTERS_ONLY_PATTERN.matcher(nombrecomun).matches()) {
				System.err.println("El nombre común de la planta solo puede contener letras.");
			} else {
				nombreComunCorrecto = true;
			}
		} while (!nombreComunCorrecto);

		// Validación del nombre científico
		boolean nombreCientificoCorrecto = false;
		do {
			System.out.println("Dame el nombre científico de la planta:");
			nombrecientifico = sc.nextLine().trim().toUpperCase();
			if (nombrecientifico == null || nombrecientifico.trim().isEmpty()) {
				System.err.println("El nombre científico de la planta no puede ser nulo o vacío.");
			} else if (!LETTERS_ONLY_PATTERN.matcher(nombrecientifico).matches()) {
				System.err.println("El nombre científico de la planta solo puede contener letras.");
			} else {
				nombreCientificoCorrecto = true;
			}
		} while (!nombreCientificoCorrecto);

		Planta cambioplanta = new Planta(codigo, nombrecomun, nombrecientifico);
		
	    return pdi.modificar(cambioplanta);
    }

	@Override
	public int eliminar(Planta planta) {

		String codigo;

		// Validación del código de la planta
		boolean codigoCorrecto = false;
		do {
			System.out.println("Dame el código de la nueva planta:");
			codigo = sc.nextLine().trim().toUpperCase();

			if (codigo == null || codigo.isEmpty()) {
				System.err.println("El código no puede ser nulo o vacío. Inténtelo de nuevo.");
			} else if (!codigo.matches("[a-zA-Z]+")) {
				System.err.println("El código solo puede contener letras. Inténtelo de nuevo.");
			} else if (!pdi.ExistePlanta(codigo)) {
				System.err.println("El código '" + codigo + "' no existe. Inténtelo de nuevo.");
			} else {
				codigoCorrecto = true;
			}
		} while (!codigoCorrecto);
		
		Planta eliminarplanta = new Planta(codigo, null, null);
		
		return pdi.eliminar(eliminarplanta);
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
		return pdi.ExistePlanta(codigo);
	}

}
