package com.drones2people.spotify.BuySong;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.pagospaypal.PagosPaypal;
import com.drones2people.spotify.persistencia.Agente;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorCanciones;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.persistencia.GestorUsuarios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class BuySong {
    PreparedStatement preparedStatement;
    Agente agente;
    GestorCanciones gestorCanciones;
    GestorUsuarios gestorUsuarios;

    public Usuario selectUser_byName (String Nombre) throws SQLException{
                Usuario usuario = new Usuario();
                String query = "SELECT * FROM Usuario WHERE Nombre = ?";

                preparedStatement = agente.getConnection().prepareStatement(query);
                preparedStatement.setString(1, Nombre);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                        usuario.setDNI(resultSet.getInt("DNI"));
                        usuario.setNombre(resultSet.getString("Nombre"));
                        usuario.setApellidos(resultSet.getString("Apellidos"));
                        usuario.setEmail(resultSet.getString("email"));
                        usuario.setPassword(resultSet.getString("password"));
                        usuario.setTelefono(resultSet.getString("Telefono"));
                        usuario.setIs_admin(resultSet.getBoolean("isAdmin"));
                        usuario.setIs_artist(resultSet.getBoolean("isArtist"));
                    }
                return usuario;
            }
    public Cancion selectSong(String name, int artist) throws SQLException{
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
                    song.setAlbum(resultSet.getInt("album"));
                    song.setDuracion(resultSet.getDouble("Duracion"));
                }
                return song;
          }
    public boolean buySong(Cancion cancion){
        boolean result;
        PagosPaypal pp = new PagosPaypal();
        result = pp.pagar();
        return result;
    }
}
