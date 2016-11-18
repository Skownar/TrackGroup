package DAO;

import android.net.Uri;

import com.example.leila.androidproject.Groupe;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.net.URI;
import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.PropertyResourceBundle;

import javax.ws.rs.core.MultivaluedMap;
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
    private String json;
    private ClientResponse response;
    private int status;
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
            String listeJson = service.path("gestionGroupe").path("groupeinfo/").get(String.class);
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
    public int create(Groupe groupe) {
        int id_groupe = 0;
        System.out.println("--- Create group method ---");
        json = "";
        try{
            json = gson.toJson(groupe);
            System.out.println("Object to json = " + json);
        }catch (Exception e){
            System.err.println("convertion json failed "+ e);
        }
        response = service.path("gestionGroupe").path("creaGroupe/").type("application/json").post(ClientResponse.class,json);
       // System.out.println(service.path("gestionGroupe").path("creaGroupe"));
        int status = response.getStatus();
        MultivaluedMap header = response.getHeaders();
        if(status >= 400){
            System.err.println("erreur status " + status);
            System.err.println(header.getFirst("Error-Reason"));
        }else{
            System.out.println("Paramètre retourné : " + header.getFirst("id_groupe"));
            System.out.println("Ajout effecuté avec succès ! easy frère");
        }
        return id_groupe;
    }

    @Override
    public Groupe readById() {
        return null;
    }
}
