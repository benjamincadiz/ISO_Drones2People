package com.drones2people.spotify;

import static org.junit.Assert.*;

import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.GestorUsuarios;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by ivangarrera on 16/12/17.
 */
public class TestGestorUsuarios {
    private GestorUsuarios gestorUsuarios;

    @Before
    public void setUp() {
        gestorUsuarios = new GestorUsuarios();
    }

    @Test(expected = SQLException.class)
    public void SelectByDNIGreaterThan8() {
        int DNI = 123456789;
        gestorUsuarios.selectUser_byDNI(DNI);
    }

    @Test
    public void SelectByDNILessOrEqualTo8() {
        Usuario usuario = new Usuario(55443322, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
        Usuario usuario_new = gestorUsuarios.selectUser_byDNI(usuario.getDNI());
        assertNotNull(usuario_new.getNombre());
    }

    @Test(expected = SQLException.class)
    public void SelectByDNINegative() {
        int DNI = -2132;
        gestorUsuarios.selectUser_byDNI(DNI);
    }

    @Test(expected = SQLException.class)
    public void InsertNullUser() {
        Usuario usuario = null;
        gestorUsuarios.insert(usuario);
    }

    @Test(expected = SQLException.class)
    public void SelectUserEmailGreaterThan255() {
        String email = "Loj87P3ZOBrjuF4UfQ8c7GqOhnezH23b6DRsEj4xVD67m99fKqossxxkRSqsltlHWBNznIEGNNXXgigPGuTCGpUejOVX0O" +
                "sSx5v7AlB1G8VmmIYxvBxDqgIrqThNGAEEOqHfPuEKLW4k6P6FhteqGidvB9R8gIs9pxGnQqRkEhXeHO1BZ3efFYOL4HlLM7blHX" +
                "1i7Xe2g3fClZ5tJgEPWfADTBvFXeILck00PP72ahu4SnWyavLdIcJ3eRlnqqjW";
        gestorUsuarios.selectUser(email, "pass");
    }

    @Test(expected = SQLException.class)
    public void SelectUserPasswordGreaterThan255() {
        String pass = "Loj87P3ZOBrjuF4UfQ8c7GqOhnezH23b6DRsEj4xVD67m99fKqossxxkRSqsltlHWBNznIEGNNXXgigPGuTCGpUejOVX0O" +
                "sSx5v7AlB1G8VmmIYxvBxDqgIrqThNGAEEOqHfPuEKLW4k6P6FhteqGidvB9R8gIs9pxGnQqRkEhXeHO1BZ3efFYOL4HlLM7blHX" +
                "1i7Xe2g3fClZ5tJgEPWfADTBvFXeILck00PP72ahu4SnWyavLdIcJ3eRlnqqjW";
        gestorUsuarios.selectUser("email@email.com", pass);
    }

    @Test
    public void SelectNonExistingUser() {
        Usuario usuario = gestorUsuarios.selectUser("noexist@noexist.com", "noexist");
        assertNull(usuario.getNombre());

    }

    @Test
    public void SelectExistingUser() {
        Usuario usuario = new Usuario(55443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        Usuario recovered = gestorUsuarios.selectUser(usuario.getEmail(), usuario.getPassword());
        assertNotNull(recovered.getNombre());
    }
}
