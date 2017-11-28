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
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class BuySong {
    PreparedStatement preparedStatement;
    Agente agente;
    GestorCanciones gestorCanciones;
    GestorUsuarios gestorUsuarios;

    public Usuario selectUser_byName (String Nombre) {
                Usuario usuario = new Usuario();
                String query = "SELECT * FROM Usuario WHERE Nombre = ?";
                try {
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
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                return usuario;
            }
    public Cancion selectSong (String name, int artist) {
                Cancion song = new Cancion();
                String query = "SELECT * FROM Cancion WHERE nombre = ? and artista = ?";
                try {
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
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                return song;
          }
    public boolean buySong(Cancion cancion){
        boolean result;
        PagosPaypal pp = new PagosPaypal();
        result = pp.pagar();
        return result;
    }
    public static void main(String[]args) {
        BuySong buySong = new BuySong();
        Scanner sc = new Scanner(System.in);
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorAlbums gestorAlbums = new GestorAlbums();
        GestorCanciones gestorCanciones = new GestorCanciones();
        int option;
        Cancion song = new Cancion();
        System.out.println("Usuario registrado. ¿Qué deseas hacer?\n1.- Comprar cancion.\n2.- Comprar album");
        option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("Escriba el nombre de la cancion\n");
                String nombre_song = sc.next();
                System.out.println("Escriba el nombre del artista\n");
                String nombre_artista = sc.next();
                Usuario useer = buySong.selectUser_byName(nombre_artista);
                song = buySong.selectSong(nombre_song, useer.getDNI());
                if (buySong.buySong(song)) {
                    System.out.println("Pago realizado con exito");
                } else System.out.println("Ha ocurrido un error con el pago");
                break;
        }
    }
}
