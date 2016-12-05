package com.example.leila.androidproject;

import org.json.JSONException;

import DAO.Identifiants;
import DAO.IdentifiantsDAO;
import Metier.Membre;

/**
 * Created by lafer on 20-11-16.
 */

public class TestDaoIdent {
    public static void main(String args[]) throws JSONException {
        IdentifiantsDAO d = new IdentifiantsDAO();
        Identifiants id = new Identifiants("dma","oracle");
        Membre m = d.read(id);
        if(m != null)System.out.println(m.toString());
    }
}
