package com.drones2people.spotify.crearplaylist.dominio;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;

import java.sql.Date;
import java.util.ArrayList;

/**
 * @author ivangarrera
 */

public class PlayList {
    private ArrayList<Cancion> canciones;
    private Usuario usuario;
    private String nombre;
    private Date fechaCreacion;

    public PlayList(Usuario usuario, Date fechaCreacion, String nombre) {
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.nombre = nombre;
    }

    public PlayList() {
        canciones = new ArrayList();
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
