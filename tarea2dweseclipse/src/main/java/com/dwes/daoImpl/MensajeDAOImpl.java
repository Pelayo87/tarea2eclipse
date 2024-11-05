package com.dwes.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.dwes.dao.MensajeDAO;
import com.dwes.modelo.Ejemplar;
import com.dwes.modelo.Mensaje;
import com.dwes.modelo.Persona;

public class MensajeDAOImpl implements MensajeDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    public MensajeDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public int insertar(Mensaje mensaje) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("INSERT INTO mensaje(id, fechahora, mensaje, id_ejemplar, id_persona) VALUES (?, ?, ?, ?, ?)");
            ps.setLong(1, mensaje.getId());
            ps.setTimestamp(2, new Timestamp(mensaje.getFechahora().getTime()));
            ps.setString(3, mensaje.getMensaje());
            ps.setLong(4, mensaje.getId_ejemplar());
            ps.setLong(5, mensaje.getId_persona());
            resultado = ps.executeUpdate();
            System.out.println("Mensaje insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar el mensaje: " + e.getMessage());
        }
        return resultado;
    }


    @Override
    public int modificar(Mensaje mensaje) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("UPDATE mensaje SET fechahora = ?, mensaje = ?, id_ejemplar = ?, id_persona = ? WHERE id = ?");
            ps.setTimestamp(1, new Timestamp(mensaje.getFechahora().getTime()));
            ps.setString(2, mensaje.getMensaje());
            ps.setLong(3,  mensaje.getEjemplar().getId());
            ps.setLong(4, mensaje.getPersona().getId());
            ps.setLong(5, mensaje.getId());

            resultado = ps.executeUpdate();
            System.out.println("Mensaje modificado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar el mensaje: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public int eliminar(Mensaje mensaje) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("DELETE FROM mensaje WHERE id = ?");
            ps.setLong(1, mensaje.getId());
            resultado = ps.executeUpdate();
            System.out.println("Mensaje eliminado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar el mensaje: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public Mensaje findById(Long id) {
        Mensaje mensaje = null;
        try {
            ps = con.prepareStatement("SELECT * FROM mensaje WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                mensaje = new Mensaje();
                mensaje.setId(rs.getLong("id"));
                mensaje.setFechahora(rs.getTimestamp("fechahora"));
                mensaje.setMensaje(rs.getString("mensaje"));
                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setId(rs.getLong("id_ejemplar"));
                mensaje.setEjemplar(ejemplar);
                Persona persona = new Persona();
                persona.setId(rs.getLong("id_persona"));
                mensaje.setPersona(persona);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el mensaje por ID: " + e.getMessage());
        }
        return mensaje;
    }

    @Override
    public Set<Mensaje> findByEjemplarId(Long ejemplarId) {
        Set<Mensaje> mensajes = new HashSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM mensaje WHERE id_ejemplar = ?");
            ps.setLong(1, ejemplarId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Mensaje mensaje = new Mensaje();
                mensaje.setId(rs.getLong("id"));
                mensaje.setFechahora(rs.getTimestamp("fechahora"));
                mensaje.setMensaje(rs.getString("mensaje"));
                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setId(rs.getLong("id_ejemplar"));
                mensaje.setEjemplar(ejemplar);
                Persona persona = new Persona();
                persona.setId(rs.getLong("id_persona"));
                mensaje.setPersona(persona);
                mensajes.add(mensaje);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar mensajes por ejemplar ID: " + e.getMessage());
        }
        return mensajes;
    }

    @Override
    public Set<Mensaje> findAll() {
        Set<Mensaje> mensajes = new HashSet<>();
        try {
            ps = con.prepareStatement("SELECT * FROM mensaje");
            rs = ps.executeQuery();

            while (rs.next()) {
                Mensaje mensaje = new Mensaje();
                mensaje.setId(rs.getLong("id"));
                mensaje.setFechahora(rs.getTimestamp("fechahora"));
                mensaje.setMensaje(rs.getString("mensaje"));
                Ejemplar ejemplar = new Ejemplar();
                ejemplar.setId(rs.getLong("id_ejemplar"));
                mensaje.setEjemplar(ejemplar);
                Persona persona = new Persona();
                persona.setId(rs.getLong("id_persona"));
                mensaje.setPersona(persona);
                mensajes.add(mensaje);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar todos los mensajes: " + e.getMessage());
        }
        return mensajes;
    }
}


