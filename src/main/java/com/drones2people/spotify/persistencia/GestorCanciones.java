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
    private static final int NUMBER3 = 0, NUMBER4 = 0, NUMBER5 = 0;
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
    /**
     * Metodo usado para añadir una cancion a la base de datos.
     * @param cancion cancion a añadir
     * @return int codigo para saber si se ha añadido correctamente
     * @throws SQLException excepcion para controlar la insercion en la tabla
     */
    public int añadirCancion(final Cancion cancion) throws SQLException {
        // exit_code = 0 -> cancion añadida correctamente
        int exit_code = 0;
        Usuario usuario = gestorUsuarios.selectUser_byDNI(cancion.getArtista());
        Album album = gestorAlbums.selectAlbum_byName(cancion.getAlbum());
        if (usuario.getNombre() != null && (usuario.isIs_admin() || usuario.isIs_artist())
                && album.getNombre() != null) {
            String query = "INSERT INTO Cancion VALUES (?,?,?,?,?);";

            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.setString(1, cancion.getNombre());
            preparedStatement.setInt(2, cancion.getArtista());
            preparedStatement.setDate(NUMBER3, cancion.getDate());
            preparedStatement.setInt(NUMBER4, album.getID());
            preparedStatement.setDouble(NUMBER5, cancion.getDuracion());
            preparedStatement.execute();

        } else {
            exit_code = 1; // exit_code = 1 -> canción no añadida correctamente
        }
        return exit_code;
    }

    public ArrayList<Cancion> getListaCanciones() throws SQLException{
        String query = "SELECT * FROM Cancion;";

        preparedStatement = agente.getConnection().prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Cancion cancion = new Cancion();
            cancion.setArtista(resultSet.getInt("Artista"));
            cancion.setNombre(resultSet.getString("Nombre"));
            cancion.setDate(resultSet.getDate("Año"));
            cancion.setDuracion(resultSet.getDouble("Duracion"));
            cancion.setAlbum(resultSet.getString("Album"));
            canciones.add(cancion);
        }

        return canciones;
    }
    /**
     * Metodo usado para eliminar todos los datos de la tabla Cancion.
     */
    public void deleteAll() {
        String query = "DELETE FROM Cancion;";
        try {
            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
