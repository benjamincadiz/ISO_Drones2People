package com.drones2people.spotify.delcancion;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.Agente;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorCanciones;
import com.drones2people.spotify.persistencia.GestorUsuarios;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DelCancion
{
    PreparedStatement preparedStatement;
    GestorUsuarios gestorUsuarios;
    GestorAlbums gestorAlbums;
    Agente agente;
    public int eliminarCancion(Cancion cancion){
        //exit_code=0 -> cancion eliminada correctamente
        int exit_code = 0;
        Usuario usuario = null;
        Album album = null;
        try {
            usuario = gestorUsuarios.selectUser_byDNI(cancion.getArtista());
            album = gestorAlbums.selectAlbum_byName(cancion.getAlbum());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
}
