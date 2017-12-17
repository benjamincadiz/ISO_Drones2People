package com.drones2people.spotify;

import static org.junit.Assert.*;

import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorCanciones;
import com.drones2people.spotify.persistencia.GestorUsuarios;
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
    }

    @Test(expected = SQLException.class)
    public void addNullCancion() {
        Cancion cancion = null;
        gestorCanciones.añadirCancion(cancion);
    }

    @Test
    public void addSongWithoutPrivileges() {
        // Preparar datos para el test
        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
        Album album = new Album(203, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Name", usuario.getDNI(), album.getID(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(1, gestorCanciones.añadirCancion(cancion));
    }

    @Test
    public void addSongWithAdminPrivileges() {
        // Preparar datos para el test
        Usuario usuario = new Usuario(66432321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, false);
        gestorUsuarios.insert(usuario);
        Album album = new Album(213, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Name", usuario.getDNI(), album.getID(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0, gestorCanciones.añadirCancion(cancion));
    }

    @Test
    public void addSongWithArtistPrivileges() {
        // Preparar datos para el test
        Usuario usuario = new Usuario(62432311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(223, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Name", usuario.getDNI(), album.getID(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0, gestorCanciones.añadirCancion(cancion));
    }

    @Test(expected = SQLException.class)
    public void addSongWithoutName() {
        Usuario usuario = new Usuario(62182311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(233, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion(null, usuario.getDNI(), album.getID(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        gestorCanciones.añadirCancion(cancion);
    }

    @Test(expected = SQLException.class)
    public void addSongWithEmptyName() {
        Usuario usuario = new Usuario(62189311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(243, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("", usuario.getDNI(), album.getID(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        gestorCanciones.añadirCancion(cancion);
    }

    @Test(expected = SQLException.class)
    public void addSongWithVeryLongName() {
        Usuario usuario = new Usuario(67189311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(253, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        String name = "Loj87P3ZOBrjuF4UfQ8c7GqOhnezH23b6DRsEj4xVD67m99fKqossxxkRSqsltlHWBNznIEGNNXXgigPGuTCGpUejOVX0O" +
                "sSx5v7AlB1G8VmmIYxvBxDqgIrqThNGAEEOqHfPuEKLW4k6P6FhteqGidvB9R8gIs9pxGnQqRkEhXeHO1BZ3efFYOL4HlLM7blHX" +
                "1i7Xe2g3fClZ5tJgEPWfADTBvFXeILck00PP72ahu4SnWyavLdIcJ3eRlnqqjW";
        Cancion cancion = new Cancion(name, usuario.getDNI(), album.getID(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        gestorCanciones.añadirCancion(cancion);
    }

    @Test
    public void addSongWithRightName() {
        Usuario usuario = new Usuario(67181311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(263, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), album.getID(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0, gestorCanciones.añadirCancion(cancion));
    }

    @Test(expected = SQLException.class)
    public void addSongWithNegativeArtist() {
        Usuario usuario = new Usuario(97181311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(273, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", -77777, album.getID(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        gestorCanciones.añadirCancion(cancion);
    }

    @Test(expected = SQLException.class)
    public void addSongWithLongArtist() {
        Usuario usuario = new Usuario(96181311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(283, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", 123456789, album.getID(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        gestorCanciones.añadirCancion(cancion);
    }

    @Test(expected = SQLException.class)
    public void addSongWithNullDate() {
        Usuario usuario = new Usuario(96681311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(293, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), album.getID(),
                312.02, null);
        gestorCanciones.añadirCancion(cancion);
    }

    @Test(expected = SQLException.class)
    public void addSongWithNegativeAlbum() {
        Usuario usuario = new Usuario(96981311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(303, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), -2340123,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        gestorCanciones.añadirCancion(cancion);
    }

    @Test(expected = SQLException.class)
    public void addSongWithZeroAlbum() {
        Usuario usuario = new Usuario(96980611, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(313, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), 0,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        gestorCanciones.añadirCancion(cancion);
    }

    @Test
    public void addSongWithNegativeDuration() {
        Usuario usuario = new Usuario(96983811, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(323, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), album.getID(),
                -312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0, gestorCanciones.añadirCancion(cancion));
    }

    @Test
    public void getListaCancionesGreaterThanZero() {
        Usuario usuario = new Usuario(96983181, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(333, usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), album.getID(),
                312.02, java.sql.Date.valueOf("2000-01-01"));
        gestorCanciones.añadirCancion(cancion);

        ArrayList<Cancion> canciones = gestorCanciones.getListaCanciones();
        assertTrue(canciones.size() > 0);
    }
}
