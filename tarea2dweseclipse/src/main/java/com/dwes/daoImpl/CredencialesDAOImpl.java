package com.dwes.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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
        }finally {
        	try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int modificar(Credenciales credenciales) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("UPDATE credenciales SET usuario = ?, password = ? WHERE id = ?");
            ps.setString(1, credenciales.getUsuario());
            ps.setString(2, credenciales.getPassword());
            ps.setLong(3, credenciales.getId());

            resultado = ps.executeUpdate();
            System.out.println("Credenciales modificadas correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al modificar las credenciales: " + e.getMessage());
        }finally {
        	try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public int eliminar(Credenciales credenciales) {
        int resultado = 0;
        try {
            ps = con.prepareStatement("DELETE FROM credenciales WHERE id = ?");
            ps.setLong(1, credenciales.getId());

            resultado = ps.executeUpdate();
            System.out.println("Credenciales eliminadas correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al eliminar las credenciales: " + e.getMessage());
        }finally {
        	try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return resultado;
    }

    @Override
    public Credenciales findById(Long id) {
        Credenciales credenciales = null;
        try {
            ps = con.prepareStatement("SELECT * FROM credenciales WHERE id = ?");
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                credenciales = new Credenciales();
                credenciales.setId(rs.getLong("id"));
                credenciales.setUsuario(rs.getString("usuario"));
                credenciales.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar las credenciales por ID: " + e.getMessage());
        }finally {
        	try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return credenciales;
    }

    @Override
    public Credenciales findByPersonaId(Long personaId) {
        Credenciales credenciales = null;
        try {
            ps = con.prepareStatement("SELECT * FROM credenciales WHERE id_persona = ?");
            ps.setLong(1, personaId);
            rs = ps.executeQuery();

            if (rs.next()) {
                credenciales = new Credenciales();
                credenciales.setId(rs.getLong("id"));
                credenciales.setUsuario(rs.getString("usuario"));
                credenciales.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar credenciales por persona ID: " + e.getMessage());
        }finally {
        	try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return credenciales;
    }

    @Override
    public Credenciales findByUsuario(String usuario) {
        Credenciales credenciales = null;
        try {
            ps = con.prepareStatement("SELECT * FROM credenciales WHERE usuario = ?");
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                credenciales = new Credenciales();
                credenciales.setId(rs.getLong("id"));
                credenciales.setUsuario(rs.getString("usuario"));
                credenciales.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar las credenciales por usuario: " + e.getMessage());
        }finally {
        	try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return credenciales;
    }

	public Set<Credenciales> find() {
	    Set<Credenciales> credenciales = new HashSet<>();
	    
	    try {
	        ps = con.prepareStatement("SELECT usuario, password FROM credenciales");
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            String usuario = rs.getString("usuario").trim();
	            String password = rs.getString("password").trim();
	            Credenciales credencialesUsuario = new Credenciales(usuario, password);
	            credenciales.add(credencialesUsuario);
	        }
	    } catch (SQLException e) {
	        System.out.println("Error al autenticarse: " + e.getMessage());
	    }
	    return credenciales;
	}

}
