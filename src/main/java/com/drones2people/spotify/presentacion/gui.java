package com.drones2people.spotify.presentacion;

import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.GestorUsuarios;

import java.util.Scanner;

/**
 * Created by ivangarrera on 15/11/17.
 */
public class gui {
    public static void main (String [] args) {
        boolean seguir = true;
        int opcion;
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
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
                    if (user.getNombre() != null) System.out.println(user.toString());
                    else System.out.println("Email o password incorrectas");
                    break;
                case 3:
                    seguir = false;
                    break;
            }
        } while (seguir);
    }
}
