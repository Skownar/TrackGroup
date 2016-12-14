package DAO;

import com.sun.jersey.api.client.ClientResponse;

import java.util.ArrayList;

import javax.ws.rs.core.MultivaluedMap;

import Managers.ExceptionManager;
import Metier.Invitation;

/**
 * Created by Leïla on 14-12-16.
 */

public class InvitationDAO extends BaseDAO implements DAO<Invitation>  {


    @Override
    public int create(Invitation invit) {
        int retour = 0;
        System.out.println("--- Create invitation method ---");
        json = "";
        try{
            json = gson.toJson(invit);
            System.out.println("Object to json = " + json);
        }catch (Exception e){
            System.err.println("convertion json failed "+ e);
        }
        response = service.path("gInvitation").path("creainvit/").type("application/json").post(ClientResponse.class,json);
        int status = response.getStatus();
        MultivaluedMap header = response.getHeaders();
        if(status >= 400){
            System.err.println("erreur status " + status);
            System.err.println(header.getFirst("Error-Reason"));

            ExceptionManager.set_exception(header.getFirst("Error-Reason").toString());
            return 0;
        }else{
            System.out.println("Ajout effecuté avec succès !");
            return 1;
        }
    }


    @Override
    public ArrayList<Invitation> readAll() {
        System.out.println("---  read all invitations method  ---");
        ListeInvitation listeInvitation = new ListeInvitation();
        try {
            String listeJson = service.path("invitationinfo/").path("invitationinfo/").get(String.class);
            System.out.println(listeJson.toString());
            listeInvitation = gson.fromJson(listeJson, ListeInvitation.class);
        } catch (Exception e) {
            System.err.println(e);
            listeInvitation.setItems(null);
        }

        return listeInvitation.getItems();
    }

    @Override
    public Invitation readById() {
        return null;
    }
    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update(Invitation obj) {
        return false;
    }
}
