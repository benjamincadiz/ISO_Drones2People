package com.drones2people.spotify.ModifySong;
import com.drones2people.spotify.persistencia.Agente;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorCanciones;
import com.drones2people.spotify.persistencia.GestorUsuarios;
import com.drones2people.spotify.presentacion.gui;
import com.drones2people.spotify.dominio.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class ModifySong {
    GestorUsuarios gestorUsuarios = new GestorUsuarios();
    GestorAlbums gestorAlbums = new GestorAlbums();
    Agente agente ;
    PreparedStatement preparedStatement;

    public int ModifySong(Cancion cancion, int option) throws SQLException {
        try {
            agente = Agente.getAgente();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        int exit_code = 0;
        Usuario user = gestorUsuarios.selectUser_byDNI(cancion.getArtista());
        Album album = gestorAlbums.selectAlbum_byName(cancion.getAlbum());

        // Si existe el usuario en la base de datos y además tiene permisos para añadir canciones...
        if (user.getNombre() != null && (user.isIs_admin() == true || user.isIs_artist() == true) && album.getNombre() != null) {
            String query = "UPDATE Cancion SET Nombre = ?,Artista = ?,Album = ?,Duracion = ?,Año = ?;";

            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.setString(1, cancion.getNombre());
            preparedStatement.setInt(2, cancion.getArtista());
            preparedStatement.setDate(3, cancion.getDate());
            preparedStatement.setInt(4, album.getID());
            preparedStatement.setDouble(5, cancion.getDuracion());
            preparedStatement.execute();

        } else {
            exit_code = 1; // exit_code = 1 -> canción no añadida correctamente
        }
        return exit_code;
    }
    public static void main( String[] args ) throws ParseException,SQLException {
        ModifySong modifySong = new ModifySong();
        Scanner sc = new Scanner(System.in);
        Album album = new Album();
        Cancion cancion = new Cancion();
        System.out.println("¿Que parametro de la cancion quieres cambiar? \n 1.Nombre.\n 2.Artista\n 3.Fecha.\n 4.Album. \n 5.Duracion.\n");
        int elegido = sc.nextInt();
        switch (elegido) {
            case 1:
                System.out.println("Escriba el nuevo nombre que quiere cambiar:\n");
                String nombre = sc.next();
                cancion.setNombre(nombre);
                break;
            case 2:
                System.out.println("Escriba el nuevo artista que quiere cambiar:\n");
                int artista = sc.nextInt();
                cancion.setArtista(artista);
                break;
            case 3:
                System.out.println("Escriba la nueva fecha que quiere cambiar:\n");
                String fecha = sc.next();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = (Date) sdf.parse(fecha);
                cancion.setDate(date);
                break;
            case 4:
                System.out.println("Escriba el nuevo album que quiere cambiar:\n");
                String albu = sc.next();
                cancion.setAlbum(albu);
                break;
            case 5:
                System.out.println("Escriba la nueva duracion que quiere cambiar:\n");
                Double duracion = sc.nextDouble();
                cancion.setDuracion(duracion);
                break;
        }
            modifySong.ModifySong(cancion,elegido);

    }
}
