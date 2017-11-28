package com.drones2people.spotify.crearplaylist.persistencia;

import com.drones2people.spotify.crearplaylist.dominio.PlayList;
import com.drones2people.spotify.persistencia.Agente;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ivangarrera
 */
public class GestorPlayList {
    private Agente agente;
    private PreparedStatement preparedStatement;

    public GestorPlayList() {
        preparedStatement = null;
        try {
            agente = Agente.getAgente();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void crearPlaylist(PlayList playList) {

        String query = "INSERT INTO PlayList VALUES (?,?,?);";
        try {
            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, playList.getUsuario().getDNI());
            preparedStatement.setString(2, playList.getNombre());
            preparedStatement.setDate(3, playList.getFechaCreacion());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
