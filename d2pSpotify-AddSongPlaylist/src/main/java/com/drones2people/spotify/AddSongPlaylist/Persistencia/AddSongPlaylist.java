package com.drones2people.spotify.AddSongPlaylist.Persistencia;
import com.drones2people.spotify.PlaySong.PlaySong;
import com.drones2people.spotify.dominio.Cancion;

import com.drones2people.spotify.persistencia.Agente;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class AddSongPlaylist {
    PlaySong playSong;
    Cancion cancion;
    PreparedStatement preparedStatement;
    Agente agente;

    public void AddSongPlaylist(String name, int dni,String playlist){
        cancion = playSong.selectSong(name,dni);
        String query = "INSERT INTO Cancion-Playlist VALUES (?,?,?);";
        try {
            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.setString(1, playlist);
            preparedStatement.setString(2, cancion.getNombre());
            preparedStatement.setInt(3, cancion.getArtista());
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
