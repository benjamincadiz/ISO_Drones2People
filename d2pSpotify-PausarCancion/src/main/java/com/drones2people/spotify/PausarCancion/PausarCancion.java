package com.drones2people.spotify.PausarCancion;
import com.drones2people.spotify.PlaySong.PlaySong;
import com.drones2people.spotify.dominio.Cancion;
import com.drones2people.spotify.persistencia.GestorCanciones;
/**
 * Hello world!
 *
 */
public class PausarCancion
{
  public void PausarCancion(String cancion, int artista){
      PlaySong playSong = null;
      Cancion cancion1=playSong.selectSong(cancion, artista);
      if(cancion1.getNombre()!=null){
          System.out.println("Pausando cancion "+cancion1.getNombre());
      }

  }
}
