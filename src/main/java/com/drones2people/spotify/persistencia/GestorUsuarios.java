package com.drones2people.spotify.persistencia;

import com.drones2people.spotify.dominio.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivangarrera on 15/11/17.
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

    public void insert (Usuario usuario) {
        String query = "INSERT INTO Usuario VALUES (?,?,?,?,?,?,?,?);";
        try {
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
