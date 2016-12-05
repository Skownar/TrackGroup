package DAO;

import Metier.Activite;
import Managers.ExceptionManager;
import com.sun.jersey.api.client.ClientResponse;

import java.util.ArrayList;

import javax.ws.rs.core.MultivaluedMap;

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
        ListeActivite listeActivite = new ListeActivite();
        try {
            String listeJson = service.path("activiteService").path("read-{idgroupe}/").get(String.class);
            System.out.println(listeJson.toString());
            listeActivite = gson.fromJson(listeJson, ListeActivite.class);
        } catch (Exception e) {
            System.err.println(e);
            listeActivite.setItems(null);
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
        response = service.path("activiteService").path("create/").type("application/json").post(ClientResponse.class,json);
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
