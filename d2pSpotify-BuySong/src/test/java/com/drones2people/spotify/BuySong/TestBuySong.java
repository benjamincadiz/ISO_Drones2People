package com.drones2people.spotify.BuySong;

import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.pagospaypal.PagosPaypal;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorCanciones;
import com.drones2people.spotify.persistencia.GestorUsuarios;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by bersus96, on 18/12/17
 */
public class TestBuySong {

    private GestorCanciones gestorCanciones;
    private GestorUsuarios gestorUsuarios;
    private GestorAlbums gestorAlbums;
    private PagosPaypal pagosPaypal;
    private BuySong buySong;
    @Before
    public void setUp() {
        gestorCanciones = new GestorCanciones();
        pagosPaypal = new PagosPaypal();
        buySong = new BuySong();
        gestorAlbums = new GestorAlbums();
    }
    @Test(expected = NullPointerException.class)
    public void buySongNull() throws SQLException {
        Cancion cancion = null;
        buySong.buySong(cancion);
    }

    @Test
    public void buyRightSong() throws SQLException{
        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, false);
        Cancion cancion = new Cancion("Name", usuario.getDNI(), 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertTrue(buySong.buySong(cancion));
    }


}
