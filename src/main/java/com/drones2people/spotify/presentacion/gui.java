package com.drones2people.spotify.presentacion;

import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorCanciones;
import com.drones2people.spotify.persistencia.GestorUsuarios;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by ivangarrera on 15/11/17.
 */
public class gui {
    private static Scanner sc = new Scanner(System.in);
    private static Usuario usuarioRegistrado = null;

    public static void main (String [] args) {
        boolean seguir = true;
        int opcion;
        sc.useDelimiter("\n");
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorAlbums gestorAlbums = new GestorAlbums();
        GestorCanciones gestorCanciones = new GestorCanciones();
        do {
            if (usuarioRegistrado != null){
                opcion = 2;
            } else {
                System.out.println("¿Qué quiere hacer?\n1. - Registrar usuario.\n2.- Loggearse\n3. - Salir");
                opcion = sc.nextInt();
            }
            switch (opcion) {
                case 1:
                    mostrarMenuRegistarUsuario(gestorUsuarios);
                    break;

                case 2:
                    Usuario user = usuarioRegistrado;
                    if (usuarioRegistrado == null) {
                        user = loggearse(gestorUsuarios);
                    }
                    if (user.getNombre() != null) {
                        usuarioRegistrado = user;
                        // Si el usuario puede añadir canciones, albums
                        if (user.isIs_artist()) {
                            menuArtist(gestorCanciones, gestorAlbums);
                        } else if (user.isIs_admin()) {   // Si el usuario es un administrador...


                        } else { // Es un usuario normal
                            menuUsuario(gestorCanciones, gestorAlbums);
                        }
                    } else
                        System.out.println("Email o password incorrectas");
                    break;
                case 3:
                    seguir = false;
                    break;
            }
        } while (seguir);
    }

    private static void mostrarMenuRegistarUsuario(GestorUsuarios gestorUsuarios) {
        Usuario usuario = new Usuario();
        System.out.println("Inserte el DNI del usuario: ");
        usuario.setDNI(sc.nextInt());
        System.out.println("Inserte el nombre del usuario: ");
        usuario.setNombre(sc.next());
        System.out.println("Inserte los apellidos del usuario: ");
        usuario.setApellidos(sc.next());
        System.out.println("Inserte el telefono del usuario: ");
        usuario.setTelefono(sc.next());
        System.out.println("Inserte el email del usuario: ");
        usuario.setEmail(sc.next());
        System.out.println("Inserte la contraseña del usuario: ");
        usuario.setPassword(sc.next());
        usuario.setIs_admin(false);
        usuario.setIs_artist(false);
        gestorUsuarios.insert(usuario);
    }

    private static Usuario loggearse(GestorUsuarios gestorUsuarios) {
        String email, password;
        System.out.println("Introduce email");
        email = sc.next();
        System.out.println("Introduce password");
        password = sc.next();
        Usuario user = gestorUsuarios.selectUser(email, password);
        return user;
    }

    private static void menuArtist(GestorCanciones gestorCanciones, GestorAlbums gestorAlbums) {
        int option;
        Album album = new Album();
        Cancion cancion = new Cancion();
        System.out.println("Artista registrado. ¿Qué deseas hacer?\n1.- Añadir álbum.\n2.- Añadir canción\n3.- Salir");
        option = sc.nextInt();
        switch (option) {
            case 1:
                System.out.println("Nombre del álbum:");
                album.setNombre(sc.next());
                System.out.println("Numero de canciones:");
                album.setNumeroCanciones(sc.nextInt());
                System.out.println("Duración del álbum: ");
                album.setDuracion(sc.nextDouble());
                album.setArtista(usuarioRegistrado.getDNI());
                album.setFechaLanzamiento(new Date(new Timestamp(System.currentTimeMillis()).getTime()));
                gestorAlbums.añadirAlbum(album);
                break;
            case 2:
                System.out.println("Nombre de la canción:");
                cancion.setNombre(sc.next());
                System.out.println("Álbum: ");
                cancion.setAlbum(sc.nextInt());
                System.out.println("Duración de la canción");
                cancion.setDuracion(sc.nextDouble());
                cancion.setArtista(usuarioRegistrado.getDNI());
                cancion.setDate(new Date(new Timestamp(System.currentTimeMillis()).getTime()));
                gestorCanciones.añadirCancion(cancion);
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    private static void menuUsuario(GestorCanciones gestorCanciones, GestorAlbums gestorAlbums) {
        int option;
        System.out.println("Usuario registrado. ¿Qué desea hacer?\n1.- Comprar Canción\n2.- Comprar Álbum\n" +
                "3.- Ver canciones compradas\n4.- Reproducir canción\n5.- Salir");
        option = sc.nextInt();
        switch (option) {
            case 1:
                int i = 0;
                ArrayList<Cancion> canciones = gestorCanciones.getListaCanciones();
                System.out.println("Elija la canción que desea");
                for (Cancion cancion : canciones){
                    System.out.println(i + 1 + " - " + cancion.getNombre());
                    i += 1;
                }
                int index = sc.nextInt();
                Cancion cancion = canciones.get(index - 1);
                System.out.println("Ha seleccionado: " + cancion.getNombre());

                break;
            case 2:
                i = 0;
                ArrayList<Album> albums = gestorAlbums.getListaAlbums();
                System.out.println("Elija el álbum que desea");
                for (Album album : albums){
                    System.out.println(i + 1 + " - " + album.getNombre());
                    i += 1;
                }
                index = sc.nextInt();
                Album album = albums.get(index - 1);
                System.out.println("Ha seleccionado: " + album.getNombre());

                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                usuarioRegistrado = null;
                break;
        }
    }
}
