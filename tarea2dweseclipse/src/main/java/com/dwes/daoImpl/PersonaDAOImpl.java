package com.dwes.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import com.dwes.dao.PersonaDAO;
import com.dwes.modelo.Persona;

/**
 * Implementación de la interfaz PersonaDAO para la gestión de objetos Persona en la base de datos.
 */

public class PersonaDAOImpl implements PersonaDAO{
	
	private Connection con;
    PreparedStatement ps;
    private ResultSet rs;
    public static int personaId;
    
    // Constructor que recibe la conexión
    public PersonaDAOImpl(Connection con) {
        this.con = con;
    }
    
    /**
     * Inserta, modifica o elimina una nueva persona en la base de datos.
     * @param persona objeto Persona a insertar, modificar o eliminar
     * @return 1 si la inserción, modificación o eliminación es exitosa, 0 si falla
     */

    @Override
    public int insertar(Persona persona) {
        int resultado = 0;
        try {
            // Usamos Statement.RETURN_GENERATED_KEYS para obtener el ID generado automáticamente
            ps = con.prepareStatement("INSERT INTO persona(nombre, email) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getEmail());

            resultado = ps.executeUpdate();
            
            // Obtener el ID generado
            if (resultado > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    personaId = rs.getInt(1);
                    persona.setId(personaId);
                    System.out.println("Persona insertada correctamente con ID: " + personaId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar la persona: " + e.getMessage());
        }
        return resultado;
    }


    @Override
    public int modificar(Persona persona) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("UPDATE persona SET nombre = ?, email = ? WHERE id = ?");
            ps.setString(1, persona.getNombre());
            ps.setString(2, persona.getEmail());
            ps.setLong(3, persona.getId());
            
            resultado = ps.executeUpdate();
            System.out.println("Persona modificada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar la persona: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public int eliminar(Persona persona) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("DELETE FROM persona WHERE id = ?");
            ps.setLong(1, persona.getId());
            
            resultado = ps.executeUpdate();
            System.out.println("Persona eliminada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar la persona: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public Persona findById(Long id) {
        Persona persona = null;
        try {
            ps = con.prepareStatement("SELECT * FROM persona WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                persona = new Persona();
                persona.setId(rs.getLong("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar la persona por ID: " + e.getMessage());
        }
        return persona;
    }

    @Override
    public Persona findByNombre(String nombre) {
        Persona persona = null;
        try {
            ps = con.prepareStatement("SELECT * FROM persona WHERE nombre = ?");
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                persona = new Persona();
                persona.setId(rs.getLong("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar la persona por nombre: " + e.getMessage());
        }
        return persona;
    }
    
    /**
     * Obtiene todas las personas almacenadas en la base de datos.
     * @return un conjunto de objetos Persona
     */

	@Override
    public Set<Persona> findAll() {
        Set<Persona> personas = new HashSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM persona");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setId(rs.getLong("id"));
                persona.setNombre(rs.getString("nombre"));
                persona.setEmail(rs.getString("email"));
                personas.add(persona);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar todas las personas: " + e.getMessage());
        }
        return personas;
    }

	@Override
	public boolean ExistePersonaNombre(String nombre) {
		boolean exists = false;
	    try {
	        ps = con.prepareStatement("SELECT COUNT(*) FROM persona WHERE nombre = ?");
	        ps.setString(1, nombre);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            exists = rs.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al verificar la existencia del código: " + e.getMessage());
	    }
	    return exists;
	}

	@Override
	public boolean ExistePersonaCorreo(String email) {
		boolean exists = false;
	    try {
	        ps = con.prepareStatement("SELECT COUNT(*) FROM persona WHERE email = ?");
	        ps.setString(1, email);
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
