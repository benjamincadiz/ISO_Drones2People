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
public class GestorCanciones {
    private ArrayList<Cancion> canciones;
    private Agente agente;
    private GestorUsuarios gestorUsuarios;
    private GestorAlbums gestorAlbums;
    private PreparedStatement preparedStatement;

    public GestorCanciones() {
        canciones = new ArrayList<Cancion>();
        preparedStatement = null;
        gestorUsuarios = new GestorUsuarios();
        gestorAlbums = new GestorAlbums();
        try {
            agente = Agente.getAgente();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public int añadirCancion(Cancion cancion) {
        // exit_code = 0 -> cancion añadida correctamente
        int exit_code = 0;
        Usuario usuario = gestorUsuarios.selectUser_byDNI(cancion.getArtista());
        Album album = gestorAlbums.selectAlbum_byID(cancion.getAlbum());
        // Si existe el usuario en la base de datos y además tiene permisos para añadir canciones...
        if (usuario.getNombre() != null && (usuario.isIs_admin() == true || usuario.isIs_artist() == true)
                && album.getNombre() != null) {
            String query = "INSERT INTO Cancion VALUES (?,?,?,?,?);";
            try {
                preparedStatement = agente.getConnection().prepareStatement(query);
                preparedStatement.setString(1, cancion.getNombre());
                preparedStatement.setInt(2, cancion.getArtista());
                preparedStatement.setDate(3, cancion.getDate());
                preparedStatement.setInt(4, cancion.getAlbum());
                preparedStatement.setDouble(5, cancion.getDuracion());
                preparedStatement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            exit_code = 1; // exit_code = 1 -> canción no añadida correctamente
        }
        return exit_code;
    }
}
