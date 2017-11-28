package com.drones2people.spotify.DeletePlayList;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.drones2people.spotify.persistencia.Agente;

/**
 * Hello world!
 *
 */
public class DeletePlayList
{
    public void DeletePlayList(String nombre) {
        Agente agente = null;
        String query = "DELETE FROM PlayList WHERE nombre = "+nombre+";";
        try {
            PreparedStatement preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }




}
