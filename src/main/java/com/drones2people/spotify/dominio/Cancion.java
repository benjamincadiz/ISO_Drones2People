package com.drones2people.spotify.dominio;

import java.sql.Date;

/**
 * Created by ivangarrera on 15/11/17.
 */
public class Cancion {
    private String nombre;
    private int artista, album;
    private double duracion;
    private Date date;

    public Cancion () {}

    public Cancion(String nombre, int artista, int album, double duracion, Date date) {
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.duracion = duracion;
        this.date = date;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getArtista() {
        return artista;
    }

    public void setArtista(int artista) {
        this.artista = artista;
    }

    public int getAlbum() {
        return album;
    }

    public void setAlbum(int album) {
        this.album = album;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "nombre='" + nombre + '\'' +
                ", artista=" + artista +
                ", album=" + album +
                ", duracion=" + duracion +
                ", date=" + date +
                '}';
    }
}
