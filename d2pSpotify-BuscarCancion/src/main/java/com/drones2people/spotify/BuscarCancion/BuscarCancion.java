package com.drones2people.spotify.BuscarCancion;
import com.drones2people.spotify.persistencia.Agente;
import com.drones2people.spotify.persistencia.GestorCanciones;
import com.drones2people.spotify.dominio.Cancion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarCancion
{
    private PreparedStatement preparedStatement;
    private Agente agente;
    public Cancion buscarCancion(String nombre_cancion) {
        Cancion cancion = new Cancion();
        String query = "Select * FROM Cancion WHERE Nombre ="+nombre_cancion+";";
        try {
            preparedStatement = agente.getConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                cancion.setNombre(rs.getString("Nombre"));
                cancion.setArtista(rs.getInt("Artista"));
                cancion.setDate(rs.getDate("fecha"));
                cancion.setAlbum(rs.getString("album"));
                cancion.setDuracion(rs.getDouble("Duracion"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cancion;
    }
}
