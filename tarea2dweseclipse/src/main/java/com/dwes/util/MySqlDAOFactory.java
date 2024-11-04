package com.dwes.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.dwes.dao.CredencialesDAO;
import com.dwes.dao.EjemplarDAO;
import com.dwes.dao.MensajeDAO;
import com.dwes.dao.PersonaDAO;
import com.dwes.dao.PlantaDAO;
import com.dwes.daoImpl.CredencialesDAOImpl;
import com.dwes.daoImpl.EjemplarDAOImpl;
import com.dwes.daoImpl.MensajeDAOImpl;
import com.dwes.daoImpl.PersonaDAOImpl;
import com.dwes.daoImpl.PlantaDAOImpl;
import com.mysql.cj.jdbc.MysqlDataSource;

public class MySqlDAOFactory {

	private Connection con;
	private static MySqlDAOFactory f;

	// el patron factory realiza la conexion
	private MySqlDAOFactory() {
		Properties prop = new Properties();
		MysqlDataSource mysqlDS = new MysqlDataSource();
		FileInputStream fis;

		try {
			// Carga el archivo de propiedades
			fis = new FileInputStream("src/main/resources/db.properties");
			prop.load(fis);

			// Configuración de los parámetros de conexión
			mysqlDS.setUrl(prop.getProperty("url"));
			mysqlDS.setPassword(prop.getProperty("password"));
			mysqlDS.setUser(prop.getProperty("user"));

			// Establecer la conexión
			con = mysqlDS.getConnection();

		} catch (FileNotFoundException e) {
			System.out.println("Error: El archivo db.properties no fue encontrado. Verifica la ruta del archivo.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error: No se pudieron leer las propiedades del archivo db.properties. Verifica el contenido del archivo.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Error de conexión a la base de datos. Verifica el URL, usuario o contraseña.");
			e.printStackTrace();
		}
	}

	public PersonaDAO getPersonaDAO() {
		return new PersonaDAOImpl(con);
	}
	
	public EjemplarDAO getEjemplarDAO() {
		return new EjemplarDAOImpl(con);
	}
	
	public CredencialesDAO getCredencialesDAO() {
		return new CredencialesDAOImpl(con);
	}
	
	public PlantaDAO getPlantaDAO() {
		return new PlantaDAOImpl(con);
	}
	
	public MensajeDAO getMensajeDAO() {
		return new MensajeDAOImpl(con);
	}
	
	public static MySqlDAOFactory getConexion() {
		if (f == null)
			f = new MySqlDAOFactory();
		return f;
	}
}
