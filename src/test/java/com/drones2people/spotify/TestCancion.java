package com.drones2people.spotify;

import static org.junit.Assert.*;

import com.drones2people.spotify.dominio.Cancion;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple Cancion.
 */
public class TestCancion {
    private Cancion cancion;

    @Before
    public void setUp() {
        cancion = new Cancion();
    }

    /**
     * Test if a given Cancion object is instance of Cancion class
     */
    @Test
    public void isInstanceCancion() {
        assertTrue( cancion instanceof Cancion );
    }

    /**
     * Test if a given Cancion object is initialized.
     *
     */
    @Test
    public void instanceNotNull() {
        assertTrue(cancion != null);
    }
}
