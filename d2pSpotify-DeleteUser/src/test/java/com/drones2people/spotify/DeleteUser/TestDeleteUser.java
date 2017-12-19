package com.drones2people.spotify.DeleteUser;


import com.drones2people.spotify.dominio.Usuario;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by bersus96, on 18/12/17
 */

public class TestDeleteUser {


    private DeleteUser deleteUser;

    @Before
    public void setUp() {
        deleteUser = new DeleteUser();

    }
    @Test(expected = NullPointerException.class)
    public void deleteNullUser()throws SQLException {
        int dni = 0;
        deleteUser.DeleteUser(dni);
    }
    @Test
    public void DeleteRightSong() throws SQLException{
        Usuario usuario = new Usuario(66443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                true, false);
        assertEquals(0,deleteUser.DeleteUser(usuario.getDNI()));
    }

    @Test(expected = SQLException.class)
    public void deleteUserWithLongDni() throws SQLException {
        Exception ex = null;
        Usuario usuario = new Usuario(1182389238, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, true);

        try{
            deleteUser.DeleteUser(usuario.getDNI());
        }catch (Exception e){
            ex = e;
        }
        assertNull(ex);

    }




}
