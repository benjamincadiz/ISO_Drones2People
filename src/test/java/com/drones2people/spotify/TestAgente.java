package com.drones2people.spotify;

import static org.junit.Assert.*;

import com.drones2people.spotify.persistencia.Agente;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ivangarrera on 16/12/17.
 */
public class TestAgente {
    private Agente agente;

    @Before
    public void setUp() {
        try {
            agente = Agente.getAgente();
        } catch (SQLException sqlexc) {
            sqlexc.printStackTrace();
        } catch (ClassNotFoundException cnfexc) {
            cnfexc.printStackTrace();
        } catch (IOException ioexc) {
            ioexc.printStackTrace();
        }
    }

    @Test
    public void isInstanceOfAgente() {
        assertTrue(agente instanceof Agente);
    }

    @Test
    public void dbConnected() {
        assertTrue(agente.getConnection() instanceof Connection);
    }

    @Test
    public void canCloseConnection() {
        try {
            agente.closeConnection();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
        assertTrue(agente.getConnection() == null);
    }

    @Test
    public void readProperly() {
        String sql = "SELECT * FROM Usuario";
        try {
            ResultSet resultSet = agente.read(sql);
            assertTrue(resultSet != null);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

}
