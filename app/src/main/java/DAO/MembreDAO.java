package DAO;

import com.example.leila.androidproject.Membre;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by Leïla on 18-11-16.
 */
// TODO Récupération des erreurs (Integrity & Unique)

public class MembreDAO extends BaseDAO implements DAO<Membre> {

    public MembreDAO() {
       super();
    }

    @Override
    public ArrayList<Membre> readAll() {
        System.out.println("---  read all method  ---");
        ListeMembre listeMembre ;
        try {
            String listeJson = service.path("gestionMembre").path("membreinfo/").get(String.class);
            listeMembre = gson.fromJson(listeJson, ListeMembre.class);
        } catch (Exception e) {
            System.err.println(e);
            listeMembre = null;
        }
        return listeMembre.getItems();
    }

    @Override
    public int create(Membre membre) {
        int id_membre = 0;
        System.out.println("--- Create member method ---");
        json = "";
        try{
            json = gson.toJson(membre);
            System.out.println("Object to json = " + json);
        }catch (Exception e){
            System.err.println("convertion json failed "+ e);
        }

        response = service.path("gestionMembre").path("creamembre/").type("application/json").post(ClientResponse.class,json);
        int status = response.getStatus();
        MultivaluedMap header = response.getHeaders();
        if(status >= 400){
            System.err.println("erreur status " + status);
            System.err.println(header.getFirst("Error-Reason"));
        }else{
            System.out.println("Paramètre retourné : " + header.getFirst("id"));
            System.out.println("Ajout effecuté avec succès !");
            id_membre = Integer.parseInt((header.getFirst("id").toString()));
        }
        return id_membre;

    }

    @Override
    public Membre readById() {
        return null;
    }

    @Override
    public boolean delete() {
        return true;
    }

    @Override
    public boolean update() {
        return false;
    }
}
