package com.drones2people.spotify.persistencia;

import com.drones2people.spotify.dominio.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author ivangarrera
 */

public class GestorUsuarios {
    private ArrayList<Usuario> usuarios;
    private Agente agente;
    private PreparedStatement preparedStatement;
    private static final int NUMBER3 = 0, NUMBER4 = 0, NUMBER5 = 0, NUMBER6 = 0, NUMBER7 = 0, NUMBER8 = 0;

    public GestorUsuarios() {
        usuarios = new ArrayList<Usuario>();
        preparedStatement = null;
        try {
            agente = Agente.getAgente();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Metodo usado para añadir un Usuario a la base de datos.
     * @param usuario usuario a insertar
     * @throws SQLException excepcion para controlar la insercion en la tabla
     */
    public void insert (final Usuario usuario) throws SQLException{
        String query = "INSERT INTO Usuario VALUES (?,?,?,?,?,?,?,?);";

        preparedStatement = agente.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, usuario.getDNI());
        preparedStatement.setString(2, usuario.getNombre());
        preparedStatement.setString(NUMBER3, usuario.getApellidos());
        preparedStatement.setString(NUMBER4, usuario.getEmail());
        preparedStatement.setString(NUMBER5, usuario.getPassword());
        preparedStatement.setString(NUMBER6, usuario.getTelefono());
        preparedStatement.setBoolean(NUMBER7, usuario.isIs_admin());
        preparedStatement.setBoolean(NUMBER8, usuario.isIs_artist());
        preparedStatement.execute();

    }
    /**
     * Metodo usado para seleccionar un Usuario a través de su DNI.
     * @param DNI dni del usuario a seleccionar
     * @return Usuario el usuario seleccionado
     * @throws SQLException excepcion para controlar la insercion en la tabla
     */
    public Usuario selectUser_byDNI (final int DNI) throws SQLException {
        Usuario usuario = new Usuario();
        String query = "SELECT * FROM Usuario WHERE DNI = ?";

        preparedStatement = agente.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, DNI);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usuario.setDNI(resultSet.getInt("DNI"));
            usuario.setNombre(resultSet.getString("Nombre"));
            usuario.setApellidos(resultSet.getString("Apellidos"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setPassword(resultSet.getString("password"));
            usuario.setTelefono(resultSet.getString("Telefono"));
            usuario.setIs_admin(resultSet.getBoolean("isAdmin"));
            usuario.setIs_artist(resultSet.getBoolean("isArtist"));
        }

        return usuario;
    }
    /**
     * Metodo usado para seleccionar un Usuario a través de su email y contraseña.
     * @param email email del usuario a seleccionar
     * @param password contraseña del usuario a seleccionar
     * @return Usuario el usuario seleccionado
     * @throws SQLException excepcion para controlar la insercion en la tabla
     */
    public Usuario selectUser (String email, String password) throws SQLException {
        Usuario usuario = new Usuario();
        String query = "SELECT * FROM Usuario WHERE email = ? and password = ?";

        preparedStatement = agente.getConnection().prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usuario.setDNI(resultSet.getInt("DNI"));
            usuario.setNombre(resultSet.getString("Nombre"));
            usuario.setApellidos(resultSet.getString("Apellidos"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setPassword(resultSet.getString("password"));
            usuario.setTelefono(resultSet.getString("Telefono"));
            usuario.setIs_admin(resultSet.getBoolean("isAdmin"));
            usuario.setIs_artist(resultSet.getBoolean("isArtist"));
        }

        return usuario;
    }
    /**
     * Metodo usado para borrar todos los datos de la tabla Usuario.
     */
    public void deleteAll() {
        String query = "DELETE FROM Usuario;";
        try {
            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
