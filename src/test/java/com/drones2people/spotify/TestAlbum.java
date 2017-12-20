package com.drones2people.spotify;

import static org.junit.Assert.*;

import com.drones2people.spotify.dominio.Album;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple Album.
 */
public class TestAlbum {
    private Album album;

    @Before
    public void setUp() {
        album = new Album();
    }

    /**
     * Test if a given Album object is instance of Album class
     */
    @Test
    public void isInstanceAlbum() {
        assertTrue( album instanceof Album );
    }

    /**
     * Test if a given Album object is initialized.
     *
     */
    @Test
    public void instanceNotNull() {
        assertTrue(album != null);
    }
}
