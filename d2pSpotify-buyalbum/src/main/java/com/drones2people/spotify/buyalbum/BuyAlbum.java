package com.drones2people.spotify.buyalbum;
import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.pagospaypal.PagosPaypal;
/**
 * Hello world!
 *
 */
public class BuyAlbum 
{ public boolean buyAlbum(Album album){
        boolean result;
        PagosPaypal pp = new PagosPaypal();
        result = pp.pagar();
        return result;
    }
}
