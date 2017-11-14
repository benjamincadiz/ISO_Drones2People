package com.drones2people.spotify;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by @ivangarrera on 14/11/17.
 */
public class Conexion {
    public static void main (String [] args) throws IOException {
        Connection conn = null;
        Properties properties = new Properties();
        ResultSet rs = null;

        // InputStream para leer el fichero de configuracion
        InputStream is = Conexion.class.getClassLoader().getResourceAsStream("app.properties");

        PreparedStatement ps = null;
        properties.load(is);
        is.close();

        try {
            conn = DriverManager.getConnection(properties.getProperty("url"), properties);
            ps = conn.prepareStatement("SELECT * FROM Cancion;");
            rs = ps.executeQuery();

            if (rs.next()){
                System.out.println(rs.getString(1));
            }

            System.out.println("Conectado a la Base de Datos");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
