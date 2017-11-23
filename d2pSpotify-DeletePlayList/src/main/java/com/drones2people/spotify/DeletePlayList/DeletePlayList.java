package com.drones2people.spotify.DeletePlayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public void DeletePlayList(String nombre) {

        String query = "DELETE FROM PlayList WHERE nombre = "+nombre+";";
        try {
            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }




}
