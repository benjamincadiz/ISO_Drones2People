package com.drones2people.spotify.dominio;

/**
 * Created by ivangarrera on 15/11/17.
 */
public class Usuario {
    private int DNI;
    private String nombre, apellidos, email, password, telefono;
    private boolean is_admin, is_artist;

    public Usuario () {}
    public Usuario (int DNI, String nombre, String apellidos, String email, String password, String telefono,
                   boolean is_admin, boolean is_artist) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.is_admin = is_admin;
        this.is_artist = is_artist;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public boolean isIs_artist() {
        return is_artist;
    }

    public void setIs_artist(boolean is_artist) {
        this.is_artist = is_artist;
    }
}
