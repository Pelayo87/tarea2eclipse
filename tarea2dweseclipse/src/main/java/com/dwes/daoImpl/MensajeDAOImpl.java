package com.dwes.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.dwes.dao.MensajeDAO;
import com.dwes.modelo.Mensaje;

public class MensajeDAOImpl implements MensajeDAO{

	private Connection con;
    PreparedStatement ps;
    private ResultSet rs;

    public MensajeDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public int insertar(Mensaje mensaje) {
        int resultado = 0;
        try {
            
            ps = con.prepareStatement("INSERT INTO mensaje(id, fechahora, mensaje) VALUES (?, ?, ?)");
            ps.setLong(1, mensaje.getId());            
            ps.setTimestamp(2, new Timestamp(mensaje.getFechahora().getTime()));  
            ps.setString(3, mensaje.getMensaje());     
            resultado = ps.executeUpdate();       
            System.out.println("Mensaje insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el mensaje: " + e.getMessage());
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
	public int modificar(Mensaje mensaje) {
		
		return 0;
	}

	@Override
	public int eliminar(Mensaje mensaje) {
		
		return 0;
	}

}
