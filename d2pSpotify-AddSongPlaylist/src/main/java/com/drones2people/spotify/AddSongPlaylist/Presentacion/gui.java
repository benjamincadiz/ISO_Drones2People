package com.drones2people.spotify.AddSongPlaylist.Presentacion;
import com.drones2people.spotify.AddSongPlaylist.Persistencia.AddSongPlaylist;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.PlaySong.PlaySong;

import java.util.Scanner;

public class gui {


    public static void main(String[]args){
        AddSongPlaylist addSongPlaylist = new PlaySong();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre de la cancion\n");
        String name = sc.next();
        System.out.println("Introduce el DNI del artista\n");
        int dni = sc.nextInt();
        System.out.println("Introduce el nombre de la playlist ");
        String name_playlist = sc.next();
        addSongPlaylist.AddSongPlaylist(name, dni,name_playlist);
    }
}
