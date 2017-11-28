package com.drones2people.spotify.BorrarCancionPlaylist;

import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.Agente;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class BorrarCancionPlaylist
{
    PreparedStatement preparedStatement;
    Agente agente;
    public void BorrarCancionP(PlayList playlist){
        String query = "DELETE FROM PLAYLIST WHERE NOMBRE="+playlist.getNombre()+" && ARTISTA="+playlist.getUsuario().getDNI()+";";
        try {
            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
