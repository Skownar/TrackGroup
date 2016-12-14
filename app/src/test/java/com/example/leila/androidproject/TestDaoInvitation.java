package com.example.leila.androidproject;

import java.util.ArrayList;

import DAO.InvitationDAO;
import Metier.Invitation;

/**
 * Created by Leïla on 14-12-16.
 */

public class TestDaoInvitation {

    public static void main(String[] args) {
        InvitationDAO invitationDAO = new InvitationDAO();
        ArrayList<Invitation> al;
        al = invitationDAO.readAll();
        for (Invitation g : al){
            System.out.println(g.toString());
        }

        try{
            System.out.println("Création d'une invitation");
            Invitation inv = new Invitation(241,41,681);
            invitationDAO.create(inv);

            System.out.println("L'invitation est inserée ");
        }catch (Exception e){
            System.err.println("Error!");



        }





    }

}
