package DAO;

import Metier.Membre;

import com.sun.jersey.api.client.ClientResponse;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by Leïla on 18-11-16.
 */

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
            e.printStackTrace();
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
            System.out.println("id du membre après reception de l'header = " +id_membre);
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
    public boolean update(Membre membre) {
        System.out.println("--- Update member method ---");
        json = "";
        try {
            json = gson.toJson(membre);
            System.out.println("Object to json = " + json);

            System.out.println("test debug ");
            response = service.path("gestionMembre").path("updateGroupeMembre/").type("application/json").put(ClientResponse.class, json);

            int status = response.getStatus();
            System.out.println("Statut : " + status);
            return true;
        } catch (Exception e) {
            System.err.println("convertion json failed " + e);
            return false;
        }


        //return true;
    }

    public List<Membre> readMembreForSearch(int id_groupe) throws JSONException {

        String uriformat = "getMembreInvit-"+id_groupe;
        System.out.println(uriformat);
        ListeMembre lm = new ListeMembre();
        System.out.println("READMEMBREFORSEARCH");

        try {
            String jsonString = service.path("gestionMembre/").path(uriformat).get(String.class);
            System.out.println(jsonString);
            lm = gson.fromJson(jsonString, ListeMembre.class);

        }catch (Exception e){
            e.printStackTrace();
            lm.setItems(null);
        }
         return lm.getItems();
    }

    public List<Membre> readMembreForSearchPlusName(int id_groupe,String concat) throws JSONException {

        String uriformat = "getMembreInvitPlusName-"+id_groupe+"-"+concat.toLowerCase();
        System.out.println(uriformat);
        ListeMembre lm = new ListeMembre();

        try {
            String jsonString = service.path("gestionMembre/").path(uriformat).get(String.class);
            System.out.println(jsonString);
            lm = gson.fromJson(jsonString, ListeMembre.class);

        }catch (Exception e){
            e.printStackTrace();
            lm.setItems(null);
        }
        return lm.getItems();
    }

}
