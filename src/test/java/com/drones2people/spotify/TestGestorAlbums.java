package com.drones2people.spotify;

import static org.junit.Assert.*;

import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorCanciones;
import com.drones2people.spotify.persistencia.GestorUsuarios;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivangarrera on 17/12/17.
 */
public class TestGestorAlbums {
    private GestorCanciones gestorCanciones;
    private GestorUsuarios gestorUsuarios;
    private GestorAlbums gestorAlbums;

    @Before
    public void setUp() {
        gestorCanciones = new GestorCanciones();
        gestorUsuarios = new GestorUsuarios();
        gestorAlbums = new GestorAlbums();
        gestorCanciones.deleteAll();
        gestorAlbums.deleteAll();
        gestorUsuarios.deleteAll();
    }

    @Test
    public void addAlbumWithNegativeArtist() throws SQLException {
        Exception ex = null;
        Album album = new Album(-4654650, 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorAlbums.añadirAlbum(album);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void addAlbumWithVeryLongArtist() throws SQLException {
        Exception ex = null;
        Album album = new Album(123456789, 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorAlbums.añadirAlbum(album);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void addAlbumWithNullName() throws SQLException {
        Exception ex = null;

        Usuario usuario = new Usuario(98765445, "Foo", "Foo", "foo@foo.com",
            "foopass", "666777666",
            false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, null, 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorAlbums.añadirAlbum(album);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void addAlbumWithZeroLengthName() throws SQLException {
        Exception ex = null;

        Usuario usuario = new Usuario(98746445, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorAlbums.añadirAlbum(album);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test(expected = MysqlDataTruncation.class)
    public void addAlbumWithVeryLongName() throws SQLException {
        Usuario usuario = new Usuario(98906445, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);

        String name = "Loj87P3ZOBrjuF4UfQ8c7GqOhnezH23b6DRsEj4xVD67m99fKqossxxkRSqsltlHWBNznIEGNNXXgigPGuTCGpUejOVX0O" +
                "sSx5v7AlB1G8VmmIYxvBxDqgIrqThNGAEEOqHfPuEKLW4k6P6FhteqGidvB9R8gIs9pxGnQqRkEhXeHO1BZ3efFYOL4HlLM7blHX" +
                "1i7Xe2g3fClZ5tJgEPWfADTBvFXeILck00PP72ahu4SnWyavLdIcJ3eRlnqqjW";
        Album album = new Album(usuario.getDNI(), 10, name, 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);
    }

    @Test
    public void addAlbumWithNegativeSongs() throws SQLException {
        Exception ex = null;
        Usuario usuario = new Usuario(98900445, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);

        Album album = new Album(usuario.getDNI(), -10, "name", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorAlbums.añadirAlbum(album);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void addAlbumWithVeryLongSongs() throws SQLException {
        Exception ex = null;
        Usuario usuario = new Usuario(98900045, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);

        Album album = new Album(usuario.getDNI(), 1000, "name", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorAlbums.añadirAlbum(album);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void addAlbumWithNegativeDuration() throws SQLException {
        Exception ex = null;

        Usuario usuario = new Usuario(90900045, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);

        Album album = new Album(usuario.getDNI(), 17, "name", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorAlbums.añadirAlbum(album);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void addAlbumWithNullDate() throws SQLException {
        Exception ex = null;
        Usuario usuario = new Usuario(90000045, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);

        Album album = new Album(usuario.getDNI(), 17, "name", 321.30,
                null);
        try {
            gestorAlbums.añadirAlbum(album);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void getListaAlbumsGreaterThanZero() throws SQLException {
        Usuario usuario = new Usuario(90000005, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);

        Album album = new Album(usuario.getDNI(), 17, "name", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        ArrayList<Album> albums = gestorAlbums.getListaAlbums();
        assertTrue(albums.size() > 0);
    }

    @Test
    public void selectAlbumNegativeID() throws SQLException {
        Exception ex = null;
        try {
            gestorAlbums.selectAlbum_byID(-100);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void selectAlbumWithRightID() throws SQLException {
        Usuario usuario = new Usuario(90000000, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);

        Album album = new Album(usuario.getDNI(), 17, "name", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);
        Album recovered = gestorAlbums.selectAlbum_byName(album.getNombre());
        assertNotNull(recovered.getNombre());
    }

    @Test
    public void selectAlbumWithNonExistentID() throws SQLException {
        Album recovered = gestorAlbums.selectAlbum_byID(8923);
        assertNull(recovered.getNombre());
    }
}
