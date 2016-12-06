package DAO;

import com.example.leila.androidproject.Activite;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;

/**
 * Created by stephanie on 01-12-16.
 */

public class ActiviteDAO extends BaseDAO implements DAO<Activite> {

    public ActiviteDAO() {
        super();
    }

    @Override
    public ArrayList<Activite> readAll() {

        System.out.println(" read all activities");
        ListeActivite listeActivite;
        try {
            String listeJson = service.path("activiteService").path("read-{idgroupe}/").get(String.class);
            System.out.println(listeJson.toString());
            listeActivite = gson.fromJson(listeJson, ListeActivite.class);
        } catch (Exception e) {
            System.err.println(e);
            listeActivite= null;
        }

        return listeActivite.getItems();
    }


    @Override
    public Activite readById() {
        return null;
    }

    @Override
    public static int create(Activite act) {
        int idactivite = 0;
        System.out.println("--- Create activite method ---");
        json = "";
        try{
            json = gson.toJson(act);
            System.out.println("Object to json = " + json);
        }catch (Exception e){
            System.err.println("convertion json failed "+ e);
        }
        response = service.path("activiteService").path("createAct/").type("application/json").post(ClientResponse.class,json);
        int status = response.getStatus();
        MultivaluedMap header = response.getHeaders();
        if(status >= 400){
            System.err.println("erreur status " + status);
            System.err.println(header.getFirst("Error-Reason"));

            // pour récupérer l'erreur dans l'asynctask create;
            ExceptionManager.set_exception(header.getFirst("Error-Reason").toString());
        }else{
            System.out.println("Paramètre retourné : " + header.getFirst("idactivite"));
            System.out.println("Ajout effecuté avec succès !");
            idactivite = Integer.parseInt((header.getFirst("idactivite").toString()));
        }
        return idactivite;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update(Activite act) {
        return false;
    }
}
