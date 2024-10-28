package com.dwes.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dwes.dao.EjemplarDAO;
import com.dwes.modelo.Ejemplar;

public class EjemplarDAOImpl implements EjemplarDAO {
    private Connection con;
    PreparedStatement ps;
    private ResultSet rs;

    public EjemplarDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public int insertar(Ejemplar ejemplar) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("INSERT INTO ejemplar(id, nombre) VALUES (?, ?)");
            ps.setLong(1, ejemplar.getId());
            ps.setString(2, ejemplar.getNombre());
            
            resultado = ps.executeUpdate();
            System.out.println("Ejemplar insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el ejemplar: " + e.getMessage());
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
    public int modificar(Ejemplar ejemplar) {
		return 0;
     
    }

	@Override
	public int eliminar(Ejemplar ejemplar) {
		return 0;
	}

	@Override
	public Ejemplar findById(Long id) {
		return null;
	}
    }
