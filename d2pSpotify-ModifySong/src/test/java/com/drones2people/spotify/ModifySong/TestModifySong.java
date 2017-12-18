package com.drones2people.spotify.ModifySong;

import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorCanciones;
import com.drones2people.spotify.persistencia.GestorUsuarios;

import java.sql.SQLException;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by bersus96, on 18/12/17
 */
public class TestModifySong {
    private GestorCanciones gestorCanciones;
    private GestorUsuarios gestorUsuarios;
    private GestorAlbums gestorAlbums;
    private ModifySong modifySong;

    @Before
    public void setUp() {
        gestorCanciones = new GestorCanciones();
        gestorUsuarios = new GestorUsuarios();
        gestorAlbums = new GestorAlbums();
        modifySong = new ModifySong();
    }
    @Test(expected = NullPointerException.class)
    public void modifyNullSong()throws SQLException {
        Cancion cancion = null;
        int option = 1;
        modifySong.ModifySong(cancion,option);
    }

    @Test
    public void addSongWithPrivileges()throws SQLException {
        // Preparar datos para el test
        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, false);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Name", usuario.getDNI(), 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(1, modifySong.ModifySong(cancion,1));
    }
    @Test
    public void addSongWithoutPrivileges()throws SQLException {
        // Preparar datos para el test
        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Name", usuario.getDNI(), 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0, modifySong.ModifySong(cancion,1));
    }
    @Test
    public void addSongWithArtistPrivileges()throws SQLException {
        // Preparar datos para el test
        Usuario usuario = new Usuario(62432311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Name", usuario.getDNI(), 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0, modifySong.ModifySong(cancion,1));
    }

    @Test(expected = SQLException.class)
    public void modifySongWithNullName()throws SQLException {
        Usuario usuario = new Usuario(96981311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("", usuario.getDNI(), 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        modifySong.ModifySong(cancion,1);
    }

    @Test(expected = SQLException.class)
    public void modifySongWithLogArtist() throws SQLException{
        Usuario usuario = new Usuario(96981311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("", 1232345533, 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        modifySong.ModifySong(cancion,2);
    }

    @Test(expected = MysqlDataTruncation.class)
    public void modifySongWithVeryLongName() throws SQLException {
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
        Cancion cancion = new Cancion(name, usuario.getDNI(), 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        modifySong.ModifySong(cancion,1);
    }

    @Test(expected = SQLException.class)
    public void addSongWithNullDate() throws SQLException {
        Exception ex = null;
        Usuario usuario = new Usuario(96681311, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), 1,
                312.02, null);
        try{
            modifySong.ModifySong(cancion,3);
        }catch (Exception e){
            ex = e;
        }
        assertNull(ex);

    }
    @Test
    public void addSongWithNegativeDuration() throws SQLException{
        Usuario usuario = new Usuario(96983811, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        gestorUsuarios.insert(usuario);
        Album album = new Album(usuario.getDNI(), 10, "Album", 321.30,
                java.sql.Date.valueOf("2000-01-01"));
        gestorAlbums.añadirAlbum(album);

        Cancion cancion = new Cancion("Right", usuario.getDNI(), 1,
                -312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0, modifySong.ModifySong(cancion,5));
    }




}


