package com.example.leila.androidproject;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import DAO.InvitationDAO;
import DAO.MembreDAO;
import Metier.Invitation;
import Metier.Membre;

/**
 * Created by Leïla on 14-12-16.
 */

public class TestDaoInvitation {

    public static void main(String[] args) throws JSONException {
        MembreDAO membreDAO = new MembreDAO();
        List<Membre>  membreList;
        InvitationDAO invitationDAO = new InvitationDAO();

        ArrayList<Invitation> al;
        al = invitationDAO.readAll();
        if(al != null){
            for (Invitation g : al){
                System.out.println(g.toString());
            }
        }
        else{
            System.out.println("La liste est vide !");
        }

        try{
            System.out.println("Création d'une invitation");
            Invitation inv = new Invitation(241,41,681);
            invitationDAO.create(inv);

            System.out.println("L'invitation est inserée ");
        }catch (Exception e){
            System.err.println("Error!");



        }
        System.out.println("------------------ LECTURE MEMBRE A INVITER ----------------");
        try {
            membreList = membreDAO.readMembreForSearch(601,"A");
            if(membreList != null) {
                for (Membre m : membreList) {
                    System.out.println(m);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }



    }

}
