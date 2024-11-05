package com.dwes.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.dwes.dao.PersonaDAO;
import com.dwes.modelo.Persona;

public class PersonaDAOImpl implements PersonaDAO{
	
	private Connection con;
    PreparedStatement ps;
    private ResultSet rs;
    
    // Constructor que recibe la conexión
    public PersonaDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public int insertar(Persona persona) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("INSERT INTO persona(id, nombre, email) VALUES (?, ?, ?)");
            ps.setLong(1, persona.getId());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getEmail());
            
            resultado = ps.executeUpdate();
            System.out.println("Persona insertada correctamente.");
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
}
