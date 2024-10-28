package com.dwes.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dwes.dao.CredencialesDAO;
import com.dwes.modelo.Credenciales;

public class CredencialesDAOImpl implements CredencialesDAO{
	private Connection con;
    PreparedStatement ps;
    private ResultSet rs;

    public CredencialesDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public int insertar(Credenciales credenciales) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("INSERT INTO credenciales(id, usuario, password) VALUES (?, ?, ?)");
            ps.setLong(1, credenciales.getId());         
            ps.setString(2, credenciales.getUsuario());
            ps.setString(3, credenciales.getPassword());

            resultado = ps.executeUpdate();            
            System.out.println("Credenciales insertadas correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar las credenciales: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

	@Override
	public int modificar(Credenciales credenciales) {
		
		return 0;
	}

	@Override
	public int eliminar(Credenciales credenciales) {
		
		return 0;
	}

}
