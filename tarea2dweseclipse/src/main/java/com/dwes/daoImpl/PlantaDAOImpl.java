package com.dwes.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import com.dwes.dao.PlantaDAO;
import com.dwes.modelo.Ejemplar;
import com.dwes.modelo.Planta;

public class PlantaDAOImpl implements PlantaDAO{
	
	private Connection con;
    PreparedStatement ps;
    private ResultSet rs;

    // Constructor que recibe la conexión
    public PlantaDAOImpl(Connection con) {
        this.con = con;
    }

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
        }finally {
        	try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
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
	            ps.execute();
	            System.out.println("Planta modificada correctamente.");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
	        	try {
	                con.close();
	            } catch (SQLException e) {
	                System.out.println("Error al cerrar la conexión: " + e.getMessage());
	            }
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
	    }finally {
        	try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
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
	    }finally {
        	try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
	    return planta;
	}


	@Override
	public Planta findByNombre(String nombrecomun) {
		return null;
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
	    }finally {
        	try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
	    return plantas;
	}

	@Override
	public Planta findByNombreCientifico(String nombrecientifico) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Ejemplar> findEjemplaresByPlanta(String codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int contarEjemplares(String codigo) {
		// TODO Auto-generated method stub
		return 0;
	}
}
