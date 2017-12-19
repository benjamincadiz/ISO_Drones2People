package com.drones2people.spotify;

import static org.junit.Assert.*;

import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.Cancion;
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
 * Created by ivangarrera on 16/12/17.
 */
public class TestGestorCanciones {
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

    @Test(expected = NullPointerException.class)
    public void addNullCancionTest() throws SQLException {
        Cancion cancion = null;
        gestorCanciones.añadirCancion(cancion);
    }

    @Test(expected = NullPointerException.class)
    public void addSongNullFieldsTest() throws SQLException {
        Usuario usuario = null;
        Album album = null;
        Cancion cancion = new Cancion(null, usuario.getDNI(), album.getNombre(),
                0, null);
        gestorCanciones.añadirCancion(cancion);
    }

    @Test
    public void addSongArtistWrongArtistZeroLengthFieldsTest() throws SQLException {
        Exception ex = null;
        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("", -231428, album.getNombre(),
                -3232.03, java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorCanciones.añadirCancion(cancion);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test(expected = MysqlDataTruncation.class)
    public void addSongAdministratorVeryLongFieldsTest() throws SQLException {
        String string = "AA23fZCVYQENmvJYCYsrXi3eYboZRCJVoy4HOUU9RdKVrqjpooCt5NC3Bq3cXW5k2T1V5cOVXlGPGcYqxIrfS7TcHO" +
                "ZysdpQXziWZiNp2P50miS9xRk78kPBidhfTvvvtlrziE7rrt8Q5zEmGkyvRtMilqOQDbA5YSpfv0cl79qqyDkecqerA6xrNg8" +
                "e9GXwrTwDQjwmVbOa6LwXnDlE4AcgeNQ4zSPSI4LbFCpkislCXfY4A5uT3kLNa50yeyi8";
        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, false);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, string, 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion(string, 12345678, album.getNombre(),
                232.23, java.sql.Date.valueOf("This is not a date"));
        gestorCanciones.añadirCancion(cancion);
    }

    @Test(expected = NullPointerException.class)
    public void addSongWithoutPrivilegesNullFieldsTest() throws SQLException {
        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        Album album = null;

        Cancion cancion = new Cancion(null, usuario.getDNI(), album.getNombre(),
                0, null);
        gestorCanciones.añadirCancion(cancion);
    }

    @Test
    public void addSongWithoutPrivilegesAndCorrectFieldsTest() throws SQLException {
        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Name", usuario.getDNI(), album.getNombre(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(1, gestorCanciones.añadirCancion(cancion));
    }

    @Test
    public void addSongWithAdminPrivilegesAndCorrectFieldsTest() throws SQLException {
        // Preparar datos para el test
        Usuario usuario = new Usuario(66432321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, false);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Name", usuario.getDNI(), album.getNombre(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0, gestorCanciones.añadirCancion(cancion));
    }

    @Test
    public void addSongWithArtistPrivilegesAndCorrectFieldsTest() throws SQLException {
        // Preparar datos para el test
        Usuario usuario = new Usuario(62432311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Name", usuario.getDNI(), album.getNombre(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0, gestorCanciones.añadirCancion(cancion));
    }

    @Test(expected = SQLException.class)
    public void addSongWithoutNameAndCorrectFieldsTest() throws SQLException {
        Usuario usuario = new Usuario(62182311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion(null, usuario.getDNI(), album.getNombre(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        gestorCanciones.añadirCancion(cancion);
    }

    @Test
    public void addSongWithEmptyNameAndCorrectFieldsTest() throws SQLException {
        Exception ex = null;

        Usuario usuario = new Usuario(62189311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("", usuario.getDNI(), album.getNombre(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorCanciones.añadirCancion(cancion);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test(expected = MysqlDataTruncation.class)
    public void addSongWithVeryLongNameAndCorrectFieldsTest() throws SQLException {
        Usuario usuario = new Usuario(67189311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        String name = "Loj87P3ZOBrjuF4UfQ8c7GqOhnezH23b6DRsEj4xVD67m99fKqossxxkRSqsltlHWBNznIEGNNXXgigPGuTCGpUejOVX0O" +
                "sSx5v7AlB1G8VmmIYxvBxDqgIrqThNGAEEOqHfPuEKLW4k6P6FhteqGidvB9R8gIs9pxGnQqRkEhXeHO1BZ3efFYOL4HlLM7blHX" +
                "1i7Xe2g3fClZ5tJgEPWfADTBvFXeILck00PP72ahu4SnWyavLdIcJ3eRlnqqjW";
        Cancion cancion = new Cancion(name, usuario.getDNI(), album.getNombre(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        gestorCanciones.añadirCancion(cancion);
    }

    @Test
    public void addSongWithRightValuesTest() throws SQLException {
        Usuario usuario = new Usuario(87171311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), album.getNombre(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0, gestorCanciones.añadirCancion(cancion));
    }

    @Test
    public void addSongWithNegativeArtistAndCorrectFieldsTest() throws SQLException {
        Exception ex = null;

        Usuario usuario = new Usuario(97181311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", -77777, album.getNombre(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorCanciones.añadirCancion(cancion);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void addSongWithLongArtistAndCorrectFieldsTest() throws SQLException {
        Usuario usuario = new Usuario(961841311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", 123456789, album.getNombre(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(1, gestorCanciones.añadirCancion(cancion));
    }

    @Test
    public void addSongWithNullDateAndCorrectFieldsTest() throws SQLException {
        Exception ex = null;

        Usuario usuario = new Usuario(12345678, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), album.getNombre(),
                312.02, null);
        try {
            gestorCanciones.añadirCancion(cancion);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void addSongWithNullAlbumAndCorrectFieldsTest() throws SQLException {
        Exception ex = null;

        Usuario usuario = new Usuario(12345678, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), null,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorCanciones.añadirCancion(cancion);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void addSongWithZeroAlbumAndCorrectFieldsTest() throws SQLException {
        Exception ex = null;

        Usuario usuario = new Usuario(96980611, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), "",
                312.02, java.sql.Date.valueOf("2000-01-01"));
        try {
            gestorCanciones.añadirCancion(cancion);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void addSongWithNegativeDurationAndCorrectFieldsTest() throws SQLException {
        Usuario usuario = new Usuario(12345678, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), album.getNombre(),
                -312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0, gestorCanciones.añadirCancion(cancion));
    }

    @Test
    public void getListaCancionesGreaterThanZero() throws SQLException {
        Usuario usuario = new Usuario(96983181, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), album.getNombre(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        gestorCanciones.añadirCancion(cancion);

        ArrayList<Cancion> canciones = gestorCanciones.getListaCanciones();
        assertTrue(canciones.size() > 0);
    }
}
