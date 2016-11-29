package DAO;

import com.example.leila.androidproject.Contient;
import com.example.leila.androidproject.Groupe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leïla on 29-11-16.
 */

public class ContientDAO  extends BaseDAO implements DAO<Contient>  {


    public List<Groupe> readGroupsByMember(int idMembre) {
        System.out.println("---  read groups by member method  ---");


        ListeGroupe listeGroupe = new ListeGroupe();
        try {
            System.out.println("Id membre : "+idMembre);
            String listeJson = service.path("gestContient/").path("getListeGroupe/"+idMembre).get(String.class);
            System.out.println(listeJson.toString());
            listeGroupe = gson.fromJson(listeJson, ListeGroupe.class);
        } catch (Exception e) {
            System.err.println(e);
            listeGroupe.setItems(null);
        }

        return listeGroupe.getItems();
    }


    @Override
    public ArrayList<Contient> readAll() {
        return null;
    }

    @Override
    public Contient readById() {
        return null;
    }

    @Override
    public int create(Contient obj) {
        return 0;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update(Contient obj) {
        return false;
    }
}
