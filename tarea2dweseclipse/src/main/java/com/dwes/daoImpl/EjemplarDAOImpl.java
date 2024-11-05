package com.dwes.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dwes.dao.EjemplarDAO;
import com.dwes.modelo.Ejemplar;
import com.dwes.modelo.Planta;

public class EjemplarDAOImpl implements EjemplarDAO {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public EjemplarDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public int insertar(Ejemplar ejemplar) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("INSERT INTO ejemplar(id, nombre, codigo_planta) VALUES (?, ?, ?)");
            ps.setLong(1, ejemplar.getId());
            ps.setString(2, ejemplar.getNombre());
            ps.setString(3, ejemplar.getCodigo());
            
            resultado = ps.executeUpdate();
            System.out.println("Ejemplar insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el ejemplar: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public int modificar(Ejemplar ejemplar) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("UPDATE ejemplar SET nombre = ?, codigo_planta = ? WHERE id = ?");
            ps.setString(1, ejemplar.getNombre());
            ps.setString(2, ejemplar.getPlanta().getCodigo());
            ps.setLong(3, ejemplar.getId());
            
            resultado = ps.executeUpdate();
            System.out.println("Ejemplar modificado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar el ejemplar: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public int eliminar(Ejemplar ejemplar) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("DELETE FROM ejemplar WHERE id = ?");
            ps.setLong(1, ejemplar.getId());
            
            resultado = ps.executeUpdate();
            System.out.println("Ejemplar eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el ejemplar: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public Ejemplar findById(Long id) {
        Ejemplar ejemplar = null;
        try {
            ps = con.prepareStatement("SELECT * FROM ejemplar WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                ejemplar = new Ejemplar();
                ejemplar.setId(rs.getLong("id"));
                ejemplar.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el ejemplar: " + e.getMessage());
        }
        return ejemplar;
    }

    @Override
    public Set<Ejemplar> findAll() {
        Set<Ejemplar> ejemplares = new HashSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM ejemplar");
            rs = ps.executeQuery();
            
            while (rs.next()) {
            	long id = rs.getLong("id");
	            String nombre = rs.getString("nombre");
	            String codigo_planta = rs.getString("codigo_planta");
                Ejemplar ejemplar = new Ejemplar(id,nombre,codigo_planta);
                ejemplares.add(ejemplar);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar todos los ejemplares: " + e.getMessage());
        }
        return ejemplares;
    }
    
    @Override
    public List<Ejemplar> findByPlanta(List<String> tiposPlanta) {
        List<Ejemplar> ejemplares = new ArrayList<>();
        
        if (tiposPlanta.isEmpty()) return ejemplares;

        StringBuilder sql = new StringBuilder("SELECT * FROM ejemplar e JOIN planta p ON e.id = p.codigo WHERE p.nombrecomun IN (");
        for (int i = 0; i < tiposPlanta.size(); i++) {
            sql.append("?").append(i < tiposPlanta.size() - 1 ? ", " : "");
        }
        sql.append(")");

        try {
            ps = con.prepareStatement(sql.toString());
            for (int i = 0; i < tiposPlanta.size(); i++) {
                ps.setString(i + 1, tiposPlanta.get(i));
            }
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setId(rs.getLong("id"));
                ejemplar.setNombre(rs.getString("nombre"));
                
                Planta planta = new Planta();
                planta.setCodigo(rs.getString("codigo"));
                planta.setNombrecomun(rs.getString("nombrecomun"));
                planta.setNombrecientifico(rs.getString("nombrecientifico"));
                
                ejemplar.setPlanta(planta);
                ejemplares.add(ejemplar);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar ejemplares: " + e.getMessage());
        }
        return ejemplares;
    }


	@Override
	public Ejemplar findWithPersonas(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ejemplar findWithMensajes(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
