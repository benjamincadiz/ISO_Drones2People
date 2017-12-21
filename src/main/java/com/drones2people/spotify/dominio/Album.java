package com.drones2people.spotify.dominio;

import java.sql.Date;

/**
 * @author ivangarrera
 */
public class Album {
    /**
     * Variable ID reperesenta el Identificador del Album.
     * Artista representa el Identificador del artista al que pertenece el Album
     * numeroCanciones representa el numero de canciones que tiene el album
     */
    private int ID, Artista, numeroCanciones;
    private String nombre;
    private double duracion;
    private Date fechaLanzamiento;

    public Album(){

    }
    /**
     * Constructor de la clase Album.
     * @param artista id del artista
     * @param numeroCanciones numero de canciones del album
     * @param nombre nombre del album
     * @param duracion duracion de todas las canciones del album
     * @param fechaLanzamiento fecha de lanzamiento del album
     */
    public Album(final int artista, final int numeroCanciones, final String nombre, final double duracion, final Date fechaLanzamiento) {
        Artista = artista;
        this.numeroCanciones = numeroCanciones;
        this.nombre = nombre;
        this.duracion = duracion;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public final int getID() {
        return ID;
    }

    public final void setID(final int ID) {
        this.ID = ID;
    }

    public final int getArtista() {
        return Artista;
    }

    public final void setArtista(final int artista) {
        Artista = artista;
    }

    public final int getNumeroCanciones() {
        return numeroCanciones;
    }

    public final void setNumeroCanciones(final int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public final String getNombre() {
        return nombre;
    }

    public final void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public final double getDuracion() {
        return duracion;
    }

    public final void setDuracion(final double duracion) {
        this.duracion = duracion;
    }

    public final Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public final void setFechaLanzamiento(final Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    @Override
    public String toString() {
        return "GestorAlbums{"
                + "ID=" + ID
                + ", Artista=" + Artista
                + ", numeroCanciones=" + numeroCanciones
                + ", nombre='" + nombre + '\''
                + ", duracion=" + duracion
                + ", fechaLanzamiento=" + fechaLanzamiento
                + '}';
    }
}
