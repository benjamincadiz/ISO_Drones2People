package com.drones2people.spotify.DescargarAlbum;

import com.drones2people.spotify.persistencia.GestorAlbums;

import java.net.Socket;

/**
 * Hello world!
 */
public class DescargarAlbum {
    GestorAlbums gestorAlbums;

    public boolean descargarAlbum(int ID) {
        boolean downloaded = true;
        String host = "www.google.es";
        int puerto = 80;
        try {
            Socket socket = new Socket(host, puerto);
            if (!socket.isConnected()) {
                downloaded = false;
            }
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            downloaded = false;
        }
        return downloaded;
    }
}
