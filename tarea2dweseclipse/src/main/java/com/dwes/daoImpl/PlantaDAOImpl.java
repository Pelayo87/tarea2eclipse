package com.dwes.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import com.dwes.dao.PlantaDAO;
import com.dwes.modelo.Planta;

/**
 * Implementación de la interfaz PlantaDAO para la gestión de objetos Planta en la base de datos.
 */

public class PlantaDAOImpl implements PlantaDAO{
	
	private Connection con;
    PreparedStatement ps;
    private ResultSet rs;

    public PlantaDAOImpl(Connection con) {
        this.con = con;
    }
    
    /**
     * Insertar , modificar o eliminar una nueva planta en la base de datos.
     * @param planta objeto Planta a insertar, modificar o eliminar
     * @return 0 si la inserción, modificación o eliminación falla, 1 si tiene éxito
     */

    @Override
    public int insertar(Planta planta) {
        try {
            ps = con.prepareStatement("INSERT INTO planta(codigo, nombrecomun, nombrecientifico) VALUES (?, ?, ?)");
            ps.setString(1, planta.getCodigo());
            ps.setString(2, planta.getNombrecomun());
            ps.setString(3, planta.getNombrecientifico());
            ps.executeUpdate();
            System.out.println("Planta insertada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar la planta: " + e.getMessage());
        }
        return 0;
    }
	
	@Override
	public int modificar(Planta planta) {
		try {
			    ps=con.prepareStatement("UPDATE planta SET nombrecomun = ?, nombrecientifico = ? WHERE codigo = ?");
			    ps.setString(1, planta.getNombrecomun());
	            ps.setString(2, planta.getNombrecientifico());
	            ps.setString(3, planta.getCodigo());
	            ps.executeUpdate();
	            System.out.println("Planta modificada correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0;
	}

	@Override
	public int eliminar(Planta planta) {
	    try {
	        ps = con.prepareStatement("DELETE FROM planta WHERE codigo = ?");
	        ps.setString(1, planta.getCodigo());
	        ps.executeUpdate();
	        System.out.println("Planta eliminada correctamente.");
	    } catch (SQLException e) {
	        System.out.println("Error al eliminar la planta: " + e.getMessage());
	    }
	    return 0;
	}

	@Override
	public Planta findById(String codigo) {
	    Planta planta = null;
	    try {
	        ps = con.prepareStatement("SELECT * FROM planta WHERE codigo = ?");
	        ps.setString(1, codigo);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            String nombrecomun = rs.getString("nombrecomun");
	            String nombrecientifico = rs.getString("nombrecientifico");
	            planta = new Planta(codigo, nombrecomun, nombrecientifico);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar la planta: " + e.getMessage());
	    }
	    return planta;
	}


	@Override
	public Planta findByNombre(String nombrecomun) {
	    Planta planta = null;
	    try {
	        ps = con.prepareStatement("SELECT * FROM planta WHERE nombrecomun = ?");
	        ps.setString(1, nombrecomun);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            String codigo = rs.getString("codigo");
	            String nombrecientifico = rs.getString("nombrecientifico");
	            planta = new Planta(codigo, nombrecomun, nombrecientifico);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al buscar la planta por nombre común: " + e.getMessage());
	    }
	    return planta;
	}

	
	@Override
	public Set<Planta> find() {
	    Set<Planta> plantas = new HashSet<>();
	    try {
	        ps = con.prepareStatement("SELECT * FROM planta");
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            String codigo = rs.getString("codigo");
	            String nombrecomun = rs.getString("nombrecomun");
	            String nombrecientifico = rs.getString("nombrecientifico");
	            Planta planta = new Planta(codigo, nombrecomun, nombrecientifico);
	            plantas.add(planta);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al obtener las plantas: " + e.getMessage());
	    }
	    return plantas;
	}
	
	/**
     * Verifica si existe una planta con el código especificado.
     * @param codigo el código a verificar
     * @return true si existe, false si no existe
     */
	
	@Override
	public boolean ExistePlanta(String codigo) {
	    boolean exists = false;
	    try {
	        ps = con.prepareStatement("SELECT COUNT(*) FROM planta WHERE codigo = ?");
	        ps.setString(1, codigo);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            exists = rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al verificar la existencia del código: " + e.getMessage());
	    }
	    return exists;
	}
}
