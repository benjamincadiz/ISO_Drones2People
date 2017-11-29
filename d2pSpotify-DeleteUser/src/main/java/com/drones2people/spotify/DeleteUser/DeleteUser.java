package com.drones2people.spotify.DeleteUser;
import com.drones2people.spotify.dominio.Album;
import com.drones2people.spotify.dominio.Usuario;
import com.drones2people.spotify.persistencia.Agente;
import com.drones2people.spotify.persistencia.GestorAlbums;
import com.drones2people.spotify.persistencia.GestorUsuarios;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


/**
 * Hello world!
 *
 */
public class DeleteUser  {
    GestorUsuarios gestorUsuarios = new GestorUsuarios();
    PreparedStatement preparedStatement;
    Agente agente;

    public int DeleteUser(int dni){
        //exit_code=0 -> cancion eliminada correctamente
        int exit_code=0;
        String query="DELETE FROM Usuario WHERE DNI="+dni;
        try {
            preparedStatement = agente.getConnection().prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
            exit_code=1;
        }

        return exit_code;

    }
    public static void main( String[] args ) {
        DeleteUser deleteUser = new DeleteUser();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el DNI del usuario a eliminar\n");
        int dni = sc.nextInt();
        if(deleteUser.DeleteUser(dni) == 0){
            System.out.println("Usuario eliminado correctamente\n");
        }

    }
}
