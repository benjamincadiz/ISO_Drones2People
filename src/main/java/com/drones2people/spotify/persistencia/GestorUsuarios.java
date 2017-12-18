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

    public GestorUsuarios() {
        usuarios = new ArrayList<Usuario>();
        preparedStatement = null;
        try {
            agente = Agente.getAgente();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void insert (Usuario usuario) throws SQLException{
        String query = "INSERT INTO Usuario VALUES (?,?,?,?,?,?,?,?);";

        preparedStatement = agente.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, usuario.getDNI());
        preparedStatement.setString(2, usuario.getNombre());
        preparedStatement.setString(3, usuario.getApellidos());
        preparedStatement.setString(4, usuario.getEmail());
        preparedStatement.setString(5, usuario.getPassword());
        preparedStatement.setString(6, usuario.getTelefono());
        preparedStatement.setBoolean(7, usuario.isIs_admin());
        preparedStatement.setBoolean(8, usuario.isIs_artist());
        preparedStatement.execute();

    }

    public Usuario selectUser_byDNI (int DNI) throws SQLException {
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
