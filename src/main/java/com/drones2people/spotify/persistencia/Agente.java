package com.drones2people.spotify.persistencia;

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

public class Agente {

    private static Agente mInstancia = null;
    private Connection mBD;
    private Properties properties = new Properties();
    private PreparedStatement preparedStatement = null;
    private InputStream is = Agente.class.getClassLoader().getResourceAsStream("app.properties");

    private Agente() throws SQLException, ClassNotFoundException, IOException {
        properties.load(is);
        is.close();
        mBD = DriverManager.getConnection(properties.getProperty("url"), properties);
    }

    public static Agente getAgente() throws SQLException, ClassNotFoundException, IOException {
        if (mInstancia == null)
            mInstancia = new Agente();
        return mInstancia;
    }

    public void clearInstance() {
        if (mInstancia != null)
            mInstancia = null;
    }

    public void closeConnection() throws SQLException, IOException {
        mBD.close();
    }

    public Connection getConnection() {
        return mBD;
    }

    public ResultSet read(String sql_query) throws SQLException {
        return preparedStatement.executeQuery(sql_query);
    }

    public int modify(String sql_query) throws SQLException {
        return preparedStatement.executeUpdate(sql_query);
    }

}


