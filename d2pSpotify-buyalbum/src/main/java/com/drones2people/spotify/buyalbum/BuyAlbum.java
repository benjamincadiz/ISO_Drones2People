package com.drones2people.spotify.buyalbum;

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
