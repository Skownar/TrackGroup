package DAO;

import com.example.leila.androidproject.Groupe;
import com.example.leila.androidproject.Membre;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

/**
 * Created by lafer on 20-11-16.
 */

public class IdentifiantsDAO extends BaseDAO implements DAO<Groupe>{

    public IdentifiantsDAO() {
        super();
    }

    public Membre read(Identifiants id) throws JSONException {
        String pseudo = id.getPseudo();
        String mdp = id.getMdp();
        String uriformat = "getIdent-"+pseudo+"-"+mdp;
        String jsonString = service.path("gestionMembre").path(uriformat).get(String.class);
        ListeMembre ls = new ListeMembre();
        ls = gson.fromJson(jsonString,ListeMembre.class);
        if(ls.getItems().isEmpty()) return null;
        else{Membre m = ls.getItems().get(0);
            return m;}
    }
    @Override
    public ArrayList<Groupe> readAll() {
        return null;
    }

    @Override
    public Groupe readById() {
        return null;
    }

    @Override
    public int create(Groupe obj) {
        return 0;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }
}
