package com.drones2people.spotify.AddSongPlaylist;

import com.drones2people.spotify.AddSongPlaylist.Persistencia.AddSongPlaylist;
import com.drones2people.spotify.crearplaylist.dominio.PlayList;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static junit.framework.Assert.assertNull;


/**
 * Created by bersus96, on 18/12/17
 */
public class TestAddSongPlaylist {



    private Cancion cancion;
    private Usuario usuario;
    private PlayList playList;
    private AddSongPlaylist addSongPlaylist;

    @Before
    public void setUp() {
        cancion = new Cancion();
        usuario = new Usuario();
        playList = new PlayList();
        addSongPlaylist = new AddSongPlaylist();

    }


    @Test(expected = NullPointerException.class)
    public void addSongPlaylistNull()throws SQLException {
        cancion  = null;
        usuario = null;
        playList = null;
        addSongPlaylist.AddSongPlaylist(cancion.getNombre(),usuario.getDNI(),playList.getNombre());
    }


    @Test(expected = SQLException.class)
    public void addSongPlaylistWithUserNull() throws SQLException{
        Exception ex = null;
        cancion = null;
        playList = new PlayList(usuario,java.sql.Date.valueOf("2002-05-10"),"rock");

        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, false);
        try{
            addSongPlaylist.AddSongPlaylist(cancion.getNombre(),usuario.getDNI(),playList.getNombre());
        }catch (Exception e){
            ex = e;
        }
        assertNull(ex);
    }

    @Test(expected = SQLException.class)
    public void addSongPlaylistWithSongNull() throws SQLException {
        Exception ex = null;
        cancion = new Cancion("Name", usuario.getDNI(), 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        playList = new PlayList(usuario,java.sql.Date.valueOf("2002-05-10"),"rock");
        usuario = null;
        try{
            addSongPlaylist.AddSongPlaylist(cancion.getNombre(),usuario.getDNI(),playList.getNombre());
        }catch (Exception e){
            ex = e;
        }
        assertNull(ex);
    }
    @Test(expected = SQLException.class)
    public void addSongPlaylistWithPlaylistNull() throws SQLException {
        Exception ex = null;
        cancion = new Cancion("Name", usuario.getDNI(), 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        playList = null;

        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, false);
        try{
            addSongPlaylist.AddSongPlaylist(cancion.getNombre(),usuario.getDNI(),playList.getNombre());
        }catch (Exception e){
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void addSongPlaylistRight() throws SQLException{
        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, false);
        Cancion cancion = new Cancion("Name", usuario.getDNI(), 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        playList = new PlayList(usuario,java.sql.Date.valueOf("2002-05-10"),"rock");
        addSongPlaylist.AddSongPlaylist(cancion.getNombre(),usuario.getDNI(),playList.getNombre());
    }
}
