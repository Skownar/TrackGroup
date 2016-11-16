package DAO;

import android.net.Uri;

import com.example.leila.androidproject.Groupe;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.net.URI;
import java.util.ArrayList;
import java.util.PropertyResourceBundle;

import javax.ws.rs.core.UriBuilder;

/**
 * Created by lafer on 16-11-16.
 * <p>
 * Implémentation de l'interface dao
 * <p>
 * Variables en variables pour éviter la redondance de code
 */

public class GroupeDAO implements DAO<Groupe> {
    private Client c;
    private URI uri;
    private WebResource service;
    private Gson gson;

    public GroupeDAO() {

        System.out.println("-- new GROUPEDAO --");
        try {
            c = Client.create();
            uri = UriBuilder.fromUri("https://apex.oracle.com/pls/apex/locagroup").build();
            service = c.resource(uri);
            gson = new Gson();
        } catch (Exception e) {
            System.err.println(e);
        }
    }


    @Override
    public ArrayList<Groupe> readAll() {
        System.out.println("---  read all groups method  ---");
        ListeGroupe listeGroupe = new ListeGroupe();
        try {
            String listeJson = service.path("gestionGroupe").path("groupeinfo").get(String.class);
            listeGroupe = gson.fromJson(listeJson, ListeGroupe.class);
        } catch (Exception e) {
            System.err.println(e);
            listeGroupe.setItems(null);
        }

        return listeGroupe.getItems();
    }

    @Override
    public boolean delete() {

        return true;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public int create() {
        return 0;
    }

    @Override
    public Groupe readById() {
        return null;
    }
}
