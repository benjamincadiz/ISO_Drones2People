package com.drones2people.spotify.PlaySong;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;
/**
 * Created by bersus96, on 18/12/17
 */
public class TestPlaySong {

    private Cancion cancion;
    private Usuario usuario;
    private PlaySong playSong;
    @Before
    public void setUp() {
        cancion = new Cancion();
        usuario = new Usuario();
        playSong = new PlaySong();

    }


    @Test(expected = NullPointerException.class)
    public void playSongNull()throws SQLException {
        cancion  = null;
        usuario = null;
        playSong.PlaySong(cancion.getNombre(),usuario.getDNI());
    }
    @Test(expected = SQLException.class)
    public void playSongWithArtistNull() throws SQLException{
        Exception ex = null;
        cancion = new Cancion("Name", usuario.getDNI(), 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        usuario = null;
        try{
            playSong.PlaySong(cancion.getNombre(),usuario.getDNI());
        }catch (Exception e){
            ex = e;
        }
        assertNull(ex);
    }

    @Test(expected = SQLException.class)
    public void playSongWithSongNull() throws SQLException {
        Exception ex = null;
        cancion = null;
        Usuario usuario = new Usuario(1182389238, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);
        try{
            playSong.PlaySong(cancion.getNombre(),usuario.getDNI());
        }catch (Exception e){
            ex = e;
        }
        assertNull(ex);
    }
    @Test
    public void playSongRight() throws SQLException{
        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, false);
        Cancion cancion = new Cancion("Name", usuario.getDNI(), 1,
                312.02, java.sql.Date.valueOf("2000-01-01"));
        assertEquals(0,playSong.PlaySong(cancion.getNombre(),usuario.getDNI()));
    }


}
