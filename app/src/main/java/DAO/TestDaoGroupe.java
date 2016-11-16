package DAO;

import com.example.leila.androidproject.Groupe;

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
        groupeDAO.create(g);
    }
}
