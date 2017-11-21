package com.drones2people.spotify.persistencia;


import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivangarrera on 15/11/17.
 */
public class GestorAlbums {
    private ArrayList<Album> albums;
    private Agente agente;
    private GestorUsuarios gestorUsuarios;
    private PreparedStatement preparedStatement;

    public GestorAlbums() {
        albums = new ArrayList<Album>();
        preparedStatement = null;
        gestorUsuarios = new GestorUsuarios();
        try {
            agente = Agente.getAgente();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void añadirAlbum(Album album) {
        int artista = album.getArtista();
        Usuario usuario = gestorUsuarios.selectUser_byDNI(artista);
        // Si existe el usuario en la base de datos y además tiene permisos para añadir albums...
        if (usuario != null && (usuario.isIs_admin() == true || usuario.isIs_artist() == true)) {
            String query = "INSERT INTO Album (Artista, Nombre, NumeroCanciones, Duracion, FechaLanzamiento) VALUES (?,?,?,?,?);";
            try {
                preparedStatement = agente.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, album.getArtista());
                preparedStatement.setString(2, album.getNombre());
                preparedStatement.setInt(3, album.getNumeroCanciones());
                preparedStatement.setDouble(4, album.getDuracion());
                preparedStatement.setDate(5, album.getFechaLanzamiento());
                preparedStatement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Album selectAlbum_byID (int ID) {
        Album album = new Album();
        String query = "SELECT * FROM Album WHERE ID = ?";
        try {
            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                album.setID(resultSet.getInt("ID"));
                album.setArtista(resultSet.getInt("Artista"));
                album.setNombre(resultSet.getString("Nombre"));
                album.setNumeroCanciones(resultSet.getInt("NumeroCanciones"));
                album.setDuracion(resultSet.getDouble("Duracion"));
                album.setFechaLanzamiento(resultSet.getDate("FechaLanzamiento"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return album;
    }
  
}
