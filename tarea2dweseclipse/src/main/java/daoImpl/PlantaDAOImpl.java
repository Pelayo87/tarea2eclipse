package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

import dao.PlantaDAO;
import modelo.Planta;

public class PlantaDAOImpl implements PlantaDAO{
	private Connection con;
	
	PreparedStatement ps;
	
	public PlantaDAOImpl (Connection con) {
		this.con = con;
	}

	@Override
	public int insertar(Planta planta) {
		try {
			ps=con.prepareStatement("INSERT INTO planta(codigo,nombrecomun,nombrecientifico) VALUES (?,?,?)");
			ps.setString(1, planta.getCodigo());
            ps.setString(2, planta.getNombrecomun());
            ps.setString(3, planta.getNombrecientifico());
            ps.execute();
            System.out.println("Planta insertada correctamente.");
		} catch (SQLException e) {
			System.out.println("Error al insertar la planta");
		}
		return 0;
	}
	

	@Override
	public int modificar(Planta planta) {
		return 0;
	}

	@Override
	public int eliminar(Planta planta) {
		return 0;
	}

	@Override
	public Planta findById(String codigo) {
		return null;
	}

	@Override
	public Planta findByNombre(String nombrecomun) {
		return null;
	}

	@Override
	public Set<Planta> find() {
		return null;
	}

}
