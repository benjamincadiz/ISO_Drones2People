package com.drones2people.spotify.Delcancion;

import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.Agente;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorUsuarios;

import java.net.CacheRequest;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class DelCancion extends Cancion
{
    PreparedStatement preparedStatement;
    GestorUsuarios gestorUsuarios;
    GestorAlbums gestorAlbums;
    Agente agente;
    public int eliminarCancion(Cancion cancion) {

        //exit_code=0 -> cancion eliminada correctamente
        int exit_code=0;
        Usuario usuario = gestorUsuarios.selectUser_byDNI(cancion.getArtista());
        Album album = gestorAlbums.selectAlbum_byID(cancion.getAlbum());
        if(usuario.getNombre() != null && album.getNombre() != null){
            if(usuario.isIs_admin()==true || usuario.isIs_artist()==true){
                String query="DELETE FROM Cancion WHERE NOMBRE="+cancion.getNombre()+" && ARTISTA="+usuario.getDNI();
                try {
                    preparedStatement = agente.getConnection().prepareStatement(query);
                    preparedStatement.execute();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }else{
            exit_code=1; //Canción no borrada correctamente
        }
        return exit_code;

    }
    public  static void main(String args[]){
        DelCancion delCancion = null;
        Cancion cancion=null;
        delCancion.eliminarCancion(cancion);
    }
}
