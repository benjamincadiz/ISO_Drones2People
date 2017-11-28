package com.drones2people.spotify.dominio;

import java.sql.Date;

/**
 * Created by ivangarrera on 15/11/17.
 */
public class Album {
    private int ID, Artista, numeroCanciones;
    private String nombre;
    private double duracion;
    private Date fechaLanzamiento;

    public Album () {}

    public Album(int ID, int artista, int numeroCanciones, String nombre, double duracion, Date fechaLanzamiento) {
        this.ID = ID;
        Artista = artista;
        this.numeroCanciones = numeroCanciones;
        this.nombre = nombre;
        this.duracion = duracion;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getArtista() {
        return Artista;
    }

    public void setArtista(int artista) {
        Artista = artista;
    }

    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public String toString() {
        return "GestorAlbums{" +
                "ID=" + ID +
                ", Artista=" + Artista +
                ", numeroCanciones=" + numeroCanciones +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", fechaLanzamiento=" + fechaLanzamiento +
                '}';
    }
}
