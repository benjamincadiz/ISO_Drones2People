import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.GestorAlbums;
import com.drones2people.spotify.dominio.GestorCanciones;

public class DelAlbum
{
public void eliminarAlbum(Album album) {
        int artista = album.getArtista();
        Usuario usuario = gestorUsuarios.selectUser_byDNI(artista);
        // Si existe el usuario en la base de datos y además tiene permisos para borrar albums
        if (usuario != null && (usuario.isIs_admin() == true || usuario.isIs_artist() == true)) {
            String query = "DELETE FROM ALBUM WHERE ID="+album.getID()+";";
            try {
                preparedStatement = agente.getConnection().prepareStatement(query);
                preparedStatement.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}