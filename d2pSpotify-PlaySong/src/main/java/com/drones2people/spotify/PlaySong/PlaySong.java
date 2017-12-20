package com.drones2people.spotify.PlaySong;

import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.Agente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class PlaySong {
    PreparedStatement preparedStatement;
    Agente agente;

    public int PlaySong(Cancion cancion,int artista) throws SQLException{
        int retorno  = 1;// Retorna 0 si reproduce bien la cancion
        if ( cancion.getNombre() != null){
            System.out.println("Reproduciendo "+cancion.getNombre()+" con éxito");
            retorno = 0;
        }
        return retorno;
    }
    public Cancion selectSong (String name, int artist) throws SQLException {
        Cancion song = new Cancion();
        String query = "SELECT * FROM Cancion WHERE nombre = ? and artista = ?";

        preparedStatement = agente.getConnection().prepareStatement(query);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, artist);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            song.setNombre(resultSet.getString("Nombre"));
            song.setArtista(resultSet.getInt("Artista"));
            song.setDate(resultSet.getDate("fecha"));
            song.setAlbum(resultSet.getString("album"));
            song.setDuracion(resultSet.getDouble("Duracion"));
        }

        return song;
    }
}
