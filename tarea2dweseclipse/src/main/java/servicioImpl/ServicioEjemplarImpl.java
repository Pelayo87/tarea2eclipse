package servicioImpl;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import com.dwes.daoImpl.EjemplarDAOImpl;
import com.dwes.modelo.Ejemplar;
import com.dwes.modelo.Planta;
import com.dwes.servicios.ServicioEjemplar;
import com.dwes.servicios.ServicioPlanta;
import com.dwes.util.InvernaderoServiciosFactory;
import com.dwes.util.MySqlDAOFactory;

public class ServicioEjemplarImpl implements ServicioEjemplar{
	private EjemplarDAOImpl edi;
	private MySqlDAOFactory factory;
	String codigoPlanta;
	Scanner sc = new Scanner(System.in);
	
	InvernaderoServiciosFactory factoryServicios = InvernaderoServiciosFactory.getServicios();
	ServicioPlanta S_planta = factoryServicios.getServiciosPlanta();
	

	public ServicioEjemplarImpl() {
		factory=MySqlDAOFactory.getConexion();
		edi=(EjemplarDAOImpl)factory.getEjemplarDAO();
	}

	@Override
	public int insertar(Ejemplar ejemplar) {
    Set<Planta> tiposPlantas = S_planta.find();
    if (tiposPlantas.isEmpty()) {
        System.err.println("No hay tipos de plantas disponibles en el sistema.");
    }

    System.out.println("Selecciona el tipo de planta:");
    int index = 1;
    for (Planta planta : tiposPlantas) {
        System.out.println(index + " - " + planta.getNombrecomun());
        index++;
    }

    // Obtener la selección del usuario
    int seleccion = sc.nextInt();
    if (seleccion < 1 || seleccion > tiposPlantas.size()) {
        System.out.println("Selección no válida.");
    }

    // Obtener la planta seleccionada
    Planta plantaElegida = (Planta) tiposPlantas.toArray()[seleccion - 1];
    System.out.println("Planta seleccionada: " + plantaElegida.getNombrecomun());
    codigoPlanta = plantaElegida.getCodigo();

    // Crear instancia de Ejemplar
    Ejemplar nuevoEjemplar = new Ejemplar();
    nuevoEjemplar.setNombre("");
    nuevoEjemplar.setCodigo(codigoPlanta);

	return edi.insertar(nuevoEjemplar);
	}

	@Override
	public int modificar(Ejemplar ejemplar) {
		// Recuperar todos los ejemplares para encontrar el último ID
        Set<Ejemplar> mostrarEjemplares = edi.findAll();
        long idEjemplar = -1;

        for (Ejemplar e : mostrarEjemplares) {
            if (e.getId() > idEjemplar) {
                idEjemplar = e.getId();
            }
        }       
        
        for (Ejemplar e : mostrarEjemplares) {
            if (e.getId() > idEjemplar) {
                idEjemplar = e.getId();
            }
        }

        if (idEjemplar <= 0) {
            System.err.println("Error: No se pudo obtener el ID del ejemplar registrado.");
        }
        
        String nombreEjemplar = codigoPlanta + "_" + idEjemplar;
        
        Ejemplar cambioejemplar = new Ejemplar(idEjemplar, nombreEjemplar, codigoPlanta);
		return edi.modificar(cambioejemplar);
	}

	@Override
	public int eliminar(Ejemplar ejemplar) {
		return edi.eliminar(ejemplar);
	}

	@Override
	public Ejemplar findById(Long id) {
		return edi.findById(id);
	}
	
	@Override
	public List<Ejemplar> findByPlanta(String codigo) {
		return edi.findByPlanta(codigo);
	}

	@Override
	public Set<Ejemplar> findAll() {
		return edi.findAll();
	}		
}
