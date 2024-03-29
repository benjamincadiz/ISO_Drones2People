package com.drones2people.spotify.persistencia;


import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author ivangarrera
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

    public void añadirAlbum(Album album) throws SQLException {
        int artista = album.getArtista();
        Usuario usuario = gestorUsuarios.selectUser_byDNI(artista);
        // Si existe el usuario en la base de datos y además tiene permisos para añadir albums...
        if (usuario != null && (usuario.isIs_admin() == true || usuario.isIs_artist() == true)) {
            String query = "INSERT INTO Album (Artista, Nombre, NumeroCanciones, Duracion, FechaLanzamiento) VALUES (?,?,?,?,?);";

            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, album.getArtista());
            preparedStatement.setString(2, album.getNombre());
            preparedStatement.setInt(3, album.getNumeroCanciones());
            preparedStatement.setDouble(4, album.getDuracion());
            preparedStatement.setDate(5, album.getFechaLanzamiento());
            preparedStatement.execute();
        }
    }

    public Album selectAlbum_byID (int ID) throws SQLException {
        Album album = new Album();
        String query = "SELECT * FROM Album WHERE ID = ?";
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

        return album;
    }

    public Album selectAlbum_byName (String name) throws SQLException {
        Album album = new Album();
        String query = "SELECT * FROM Album WHERE Nombre = ?";
        preparedStatement = agente.getConnection().prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            album.setID(resultSet.getInt("ID"));
            album.setArtista(resultSet.getInt("Artista"));
            album.setNombre(resultSet.getString("Nombre"));
            album.setNumeroCanciones(resultSet.getInt("NumeroCanciones"));
            album.setDuracion(resultSet.getDouble("Duracion"));
            album.setFechaLanzamiento(resultSet.getDate("FechaLanzamiento"));
        }

        return album;
    }

    public ArrayList<Album> getListaAlbums() throws SQLException {
        String query = "SELECT * FROM Album;";

        preparedStatement = agente.getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Album album = new Album();
            album.setArtista(resultSet.getInt("Artista"));
            album.setNombre(resultSet.getString("Nombre"));
            album.setID(resultSet.getInt("ID"));
            album.setDuracion(resultSet.getDouble("Duracion"));
            album.setNumeroCanciones(resultSet.getInt("NumeroCanciones"));
            album.setFechaLanzamiento(resultSet.getDate("FechaLanzamiento"));
            albums.add(album);
        }

        return albums;
    }

    public void deleteAll() {
        String query = "DELETE FROM Album;";
        try {
            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
  
}
