package com.dwes.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dwes.dao.PersonaDAO;
import com.dwes.modelo.Persona;

public class PersonaDAOImpl implements PersonaDAO{
	
	private Connection con;
    PreparedStatement ps;
    private ResultSet rs;

	@Override
	public int insertar(Persona persona) {
		try {
			ps = con.prepareStatement("INSERT INTO persona(id, nombre, email) VALUES (?, ?, ?)");
			ps.setLong(1, persona.getId());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getEmail());
            ps.execute();
            System.out.println("Planta insertada correctamente.");
		} catch (SQLException e) {
			System.out.println("Error al insertar la planta");
		}
		return 0;
	}

	@Override
	public int modificar(Persona persona) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminar(Persona persona) {
		// TODO Auto-generated method stub
		return 0;
	}
}
