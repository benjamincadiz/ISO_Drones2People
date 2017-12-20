package com.drones2people.spotify;

import static org.junit.Assert.*;

import com.drones2people.spotify.dominio.Usuario;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple Usuario.
 */
public class TestUsuario {
    private Usuario usuario;

    @Before
    public void setUp() {
        usuario = new Usuario();
    }

    /**
     * Test if a given Usuario object is instance of Usuario class
     */
    @Test
    public void isInstanceUsuario() {
        assertTrue( usuario instanceof Usuario );
    }

    /**
     * Test if a given Usuario object is initialized.
     *
     */
    @Test
    public void instanceNotNull() {
        assertTrue(usuario != null);
    }
}
