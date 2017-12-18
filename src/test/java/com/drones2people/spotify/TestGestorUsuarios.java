package com.drones2people.spotify;

import static org.junit.Assert.*;

import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorCanciones;
import com.drones2people.spotify.persistencia.GestorUsuarios;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by ivangarrera on 16/12/17.
 */
public class TestGestorUsuarios {
    private GestorUsuarios gestorUsuarios;
    private GestorAlbums gestorAlbums;
    private GestorCanciones gestorCanciones;

    @Before
    public void setUp() {
        gestorUsuarios = new GestorUsuarios();
        gestorAlbums = new GestorAlbums();
        gestorCanciones = new GestorCanciones();
        gestorCanciones.deleteAll();
        gestorAlbums.deleteAll();
        gestorUsuarios.deleteAll();
    }

    @Test
    public void SelectByDNIGreaterThan8() throws SQLException {
        Exception ex = null;
        int DNI = 123456789;
        try {
            gestorUsuarios.selectUser_byDNI(DNI);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void SelectByDNILessOrEqualTo8() throws SQLException {
        Usuario usuario = new Usuario(55443322, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
        Usuario usuario_new = gestorUsuarios.selectUser_byDNI(usuario.getDNI());
        assertNotNull(usuario_new.getNombre());
    }

    @Test
    public void SelectByDNINegative() throws SQLException {
        Exception ex = null;
        int DNI = -2132;
        try {
            gestorUsuarios.selectUser_byDNI(DNI);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test(expected = NullPointerException.class)
    public void InsertNullUser() throws SQLException {
        Usuario usuario = null;
        gestorUsuarios.insert(usuario);
    }

    @Test
    public void SelectUserEmailGreaterThan255() throws SQLException {
        Exception ex = null;

        String email = "Loj87P3ZOBrjuF4UfQ8c7GqOhnezH23b6DRsEj4xVD67m99fKqossxxkRSqsltlHWBNznIEGNNXXgigPGuTCGpUejOVX0O" +
                "sSx5v7AlB1G8VmmIYxvBxDqgIrqThNGAEEOqHfPuEKLW4k6P6FhteqGidvB9R8gIs9pxGnQqRkEhXeHO1BZ3efFYOL4HlLM7blHX" +
                "1i7Xe2g3fClZ5tJgEPWfADTBvFXeILck00PP72ahu4SnWyavLdIcJ3eRlnqqjW";
        try {
            gestorUsuarios.selectUser(email, "pass");
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void SelectUserPasswordGreaterThan255() throws SQLException {
        Exception ex = null;

        String pass = "Loj87P3ZOBrjuF4UfQ8c7GqOhnezH23b6DRsEj4xVD67m99fKqossxxkRSqsltlHWBNznIEGNNXXgigPGuTCGpUejOVX0O" +
                "sSx5v7AlB1G8VmmIYxvBxDqgIrqThNGAEEOqHfPuEKLW4k6P6FhteqGidvB9R8gIs9pxGnQqRkEhXeHO1BZ3efFYOL4HlLM7blHX" +
                "1i7Xe2g3fClZ5tJgEPWfADTBvFXeILck00PP72ahu4SnWyavLdIcJ3eRlnqqjW";
        try {
            gestorUsuarios.selectUser("email@email.com", pass);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void SelectNonExistingUser() throws SQLException {
        Usuario usuario = gestorUsuarios.selectUser("noexist@noexist.com", "noexist");
        assertNull(usuario.getNombre());
    }

    @Test
    public void SelectExistingUser() throws SQLException {
        Usuario usuario = new Usuario(55443321, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
        Usuario recovered = gestorUsuarios.selectUser(usuario.getEmail(), usuario.getPassword());
        assertNotNull(recovered.getNombre());
    }

    @Test
    public void InsertUserNegativeDNI() throws SQLException {
        Exception ex = null;
        Usuario usuario = new Usuario(-99545455, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        try {
            gestorUsuarios.insert(usuario);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void InsertUserVeryLongDNI() throws SQLException {
        Exception ex = null;
        Usuario usuario = new Usuario(999545455, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        try {
            gestorUsuarios.insert(usuario);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test
    public void InsertUserWithCorrectDNI() {
        Exception ex = null;
        Usuario usuario = new Usuario(78945632, "Foo", "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        try {
            gestorUsuarios.insert(usuario);
        } catch (Exception e) {
            ex = e;
        }
        assertNull(ex);
    }

    @Test(expected = SQLException.class)
    public void InsertUserNullName() throws SQLException {
        Usuario usuario = new Usuario(78935632, null, "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
    }

    @Test(expected = MysqlDataTruncation.class)
    public void InsertUserVeryLongName() throws SQLException {
        String name = "Ji9CromUIMvm2QhBF5PHpEXszoKSyUH0hNKKZDmiU";
        Usuario usuario = new Usuario(78935642, name, "Foo", "foo@foo.com",
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
    }

    @Test(expected = SQLException.class)
    public void InsertUserNullSurname() throws SQLException {
        Usuario usuario = new Usuario(72935632, "foo", null, "foo@foo.com",
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
    }

    @Test(expected = MysqlDataTruncation.class)
    public void InsertUserVeryLongSurname() throws SQLException {
        String surname = "ePTQPVx008Z6OljerI8RlYVIlbQyfX9tC2nhmj5W9nHQciTJt2E";
        Usuario usuario = new Usuario(72915632, "foo", surname, "foo@foo.com",
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
    }

    @Test(expected = SQLException.class)
    public void InsertUserNullEmail() throws SQLException {
        Usuario usuario = new Usuario(72915322, "foo", "foo", null,
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
    }

    @Test(expected = MysqlDataTruncation.class)
    public void InsertUserVeryLongEmail() throws SQLException {
        String email = "AA23fZCVYQENmvJYCYsrXi3eYboZRCJVoy4HOUU9RdKVrqjpooCt5NC3Bq3cXW5k2T1V5cOVXlGPGcYqxIrfS7TcHO" +
                "ZysdpQXziWZiNp2P50miS9xRk78kPBidhfTvvvtlrziE7rrt8Q5zEmGkyvRtMilqOQDbA5YSpfv0cl79qqyDkecqerA6xrNg8" +
                "e9GXwrTwDQjwmVbOa6LwXnDlE4AcgeNQ4zSPSI4LbFCpkislCXfY4A5uT3kLNa50yeyi8";
        Usuario usuario = new Usuario(72911324, "foo", "foo", email,
                "foopass", "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
    }

    @Test(expected = SQLException.class)
    public void InsertUserNullPassword() throws SQLException {
        Usuario usuario = new Usuario(72415622, "foo", "foo", "foo@foo.com",
                null, "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
    }

    @Test(expected = MysqlDataTruncation.class)
    public void InsertUserVeryLongPassword() throws SQLException {
        String pass = "AA23fZCVYQENmvJYCYsrXi3eYboZRCJVoy4HOUU9RdKVrqjpooCt5NC3Bq3cXW5k2T1V5cOVXlGPGcYqxIrfS7TcHO" +
                "ZysdpQXziWZiNp2P50miS9xRk78kPBidhfTvvvtlrziE7rrt8Q5zEmGkyvRtMilqOQDbA5YSpfv0cl79qqyDkecqerA6xrNg8" +
                "e9GXwrTwDQjwmVbOa6LwXnDlE4AcgeNQ4zSPSI4LbFCpkislCXfY4A5uT3kLNa50yeyi8";
        Usuario usuario = new Usuario(72231534, "foo", "foo", "foo@foo.com",
                pass, "666777666",
                false, false);
        gestorUsuarios.insert(usuario);
    }
}
