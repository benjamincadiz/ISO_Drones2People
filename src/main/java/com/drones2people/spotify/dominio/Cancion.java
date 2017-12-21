package com.drones2people.spotify.dominio;

import java.sql.Date;

/**
 * @author ivangarrera
 */
public class Cancion {
    private String nombre, album;
    private int artista;
    private double duracion;
    private Date date;

    public Cancion () {}
    /**
     * Constructor de la clase Cancion.
     * @param nombre nombre de la cancion
     * @param artista identificador del artista propietario de la cancion
     * @param album nombre del album al que pertenece la cancion
     * @param duracion duracion de la cancion
     * @param date fecha de creacion de la cancion
     */
    public Cancion(final String nombre, final int artista,
                   final String album, final double duracion, final Date date) {
        this.nombre = nombre;
        this.artista = artista;
        this.album = album;
        this.duracion = duracion;
        this.date = date;
    }

    public final String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public final int getArtista() {
        return artista;
    }

    public final void setArtista(int artista) {
        this.artista = artista;
    }

    public final String getAlbum() {
        return album;
    }

    public final void setAlbum(String album) {
        this.album = album;
    }

    public final double getDuracion() {
        return duracion;
    }

    public final void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public final Date getDate() {
        return date;
    }

    public final void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Cancion{"
                + "nombre='" + nombre + '\''
                + ", artista=" + artista
                + ", album=" + album
                + ", duracion=" + duracion
                + ", date=" + date
                + '}';
    }
}
