package com.drones2people.spotify.presentacion;

import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorCanciones;
import com.drones2people.spotify.persistencia.GestorUsuarios;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Scanner;

/**
 * Created by ivangarrera on 15/11/17.
 */
public class gui {
    public static void main (String [] args) {
        Usuario usuarioRegistrado = null;
        boolean seguir = true;
        int opcion;
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorAlbums gestorAlbums = new GestorAlbums();
        GestorCanciones gestorCanciones = new GestorCanciones();
        do {
            System.out.println("¿Qué quiere hacer?\n1. - Registrar usuario.\n2.- Loggearse\n3. - Salir");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
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
                    break;

                case 2:
                    String email, password;
                    System.out.println("Introduce email");
                    email = sc.next();
                    System.out.println("Introduce password");
                    password = sc.next();
                    Usuario user = gestorUsuarios.selectUser(email, password);
                    if (user.getNombre() != null) {
                        usuarioRegistrado = user;
                        // Si el usuario puede añadir canciones, albums
                        if (user.isIs_artist()) {
                            int option;
                            Album album = new Album();
                            Cancion cancion = new Cancion();
                            System.out.println("Artista registrado. ¿Qué deseas hacer?\n1.- Añadir álbum.\n2.- Añadir canción");
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
                            }
                        } else if (user.isIs_admin()) {   // Si el usuario es un administrador...


                        } else { // Es un usuario normal

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
}
