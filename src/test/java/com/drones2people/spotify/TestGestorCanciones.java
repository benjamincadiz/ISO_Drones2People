package com.drones2people.spotify;

import static org.junit.Assert.*;

import com.drones2people.spotify.persistencia.GestorCanciones;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by ivangarrera on 16/12/17.
 */
public class TestGestorCanciones {
    private GestorCanciones gestorCanciones;

    @Before
    public void setUp() {
        gestorCanciones = new GestorCanciones();
    }

}
