package com.dwes.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MySqlDAOFactory {
	
    private static MysqlDataSource mysqlDS = new MysqlDataSource();
    
    public static Connection getConexion() throws IOException, SQLException {
        Properties prop = new Properties();
        FileInputStream fis = null;
        
        try {
            fis = new FileInputStream("src/main/resources/db.properties");
            prop.load(fis);

            mysqlDS.setUrl(prop.getProperty("url"));
            mysqlDS.setUser(prop.getProperty("user"));
            mysqlDS.setPassword(prop.getProperty("password"));
            
        } catch (IOException e) {
            System.out.println("Error: No se pudo leer el archivo de propiedades.");
            throw e;
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        
        return mysqlDS.getConnection();
    }
}
