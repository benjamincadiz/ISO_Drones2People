package com.drones2people.spotify.dominio;

/**
 * @author ivangarrera
 */
public class Usuario {
    private int DNI;
    private String nombre, apellidos, email, password, telefono;
    private boolean is_admin, is_artist;

    public Usuario () {}
    /**
     * Constructor de la clase Usuario.
     * @param DNI dni del usuario
     * @param nombre nombre del usuario
     * @param apellidos appellidos del usuario
     * @param email correo electronico del usuario
     * @param password contraseña del usuario
     * @param telefono telefono de contacto del usuario
     * @param is_admin si el usuario tiene privilegios de administrador
     * @param is_artist si el usuario es artista
     */
    public Usuario (final int DNI, final String nombre, final String apellidos,
                    final String email, final String password,
                    final String telefono, final boolean is_admin,
                    final boolean is_artist) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.is_admin = is_admin;
        this.is_artist = is_artist;
    }

    public final int getDNI() {
        return DNI;
    }

    public final void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public final String getNombre() {
        return nombre;
    }

    public final void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public final String getApellidos() {
        return apellidos;
    }

    public final void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public final String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        this.email = email;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public final String getTelefono() {
        return telefono;
    }

    public final void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public final boolean isIs_admin() {
        return is_admin;
    }

    public final void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public final boolean isIs_artist() {
        return is_artist;
    }

    public final void setIs_artist(boolean is_artist) {
        this.is_artist = is_artist;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "DNI=" + DNI
                + ", nombre='" + nombre + '\''
                + ", apellidos='" + apellidos + '\''
                + ", email='" + email + '\''
                + ", password='" + password + '\''
                + ", telefono='" + telefono + '\''
                + ", is_admin=" + is_admin
                + ", is_artist=" + is_artist
                + '}';
    }
}
