package DAO;

import com.example.leila.androidproject.Groupe;

import java.nio.channels.Pipe;
import java.util.ArrayList;

/**
 * Created by lafer on 16-11-16.
 */

public class TestDaoGroupe {
    public static void main(String[] args) {
        GroupeDAO groupeDAO = new GroupeDAO();
        ArrayList<Groupe> al = new ArrayList<>();
        al = groupeDAO.readAll();
        for (Groupe g : al){
            System.out.println(g.toString());
        }
        Groupe g = new Groupe(0,"Motocyclette",21);
        int id_groupe = groupeDAO.create(g);
        System.out.println("Le nouveau groupe port l'id : " + id_groupe);
    }
}
