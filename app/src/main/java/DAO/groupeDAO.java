package DAO;

import com.example.leila.androidproject.Groupe;
import com.sun.jersey.api.client.ClientResponse;

import java.util.ArrayList;

import javax.ws.rs.core.MultivaluedMap;

/**
 * Created by lafer on 16-11-16.
 * <p>
 * Implémentation de l'interface dao
 * <p>
 * Variables en variables de classe pour éviter la redondance de code
 */

public class GroupeDAO extends BaseDAO implements DAO<Groupe> {
    

    public GroupeDAO() {
        super();
    }

    @Override
    public ArrayList<Groupe> readAll() {
        System.out.println("---  read all groups method  ---");
        ListeGroupe listeGroupe = new ListeGroupe();
        try {
            String listeJson = service.path("gestionGroupe").path("groupeinfo/").get(String.class);
            System.out.println(listeJson.toString());
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
        int status = response.getStatus();
        MultivaluedMap header = response.getHeaders();
        if(status >= 400){
            System.err.println("erreur status " + status);
            System.err.println(header.getFirst("Error-Reason"));

            // pour récupérer l'erreur dans l'asynctask creaGroupe;
            ExceptionManager.set_exception(header.getFirst("Error-Reason").toString());
        }else{
            System.out.println("Paramètre retourné : " + header.getFirst("id_groupe"));
            System.out.println("Ajout effecuté avec succès !");
        }
        return id_groupe;
    }

    @Override
    public Groupe readById() {
        return null;
    }
}
