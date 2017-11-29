package com.drones2people.spotify.descargarCancion;

import java.net.Socket;

/**
 * @author ivangarrera
 *
 */
public class DescargarCancion {

    public DescargarCancion () {}

    public boolean descargarCancion() {
        boolean downloaded = true;
        String host = "www.google.es";
        int puerto  = 80;
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
