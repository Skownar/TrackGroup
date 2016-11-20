package com.example.leila.androidproject;

import com.example.leila.androidproject.Membre;

import java.util.ArrayList;

import DAO.MembreDAO;

/**
 * Created by Leïla on 18-11-16.
 */

public class TestDaoMembre {
    public static void main(String[] args) {
        MembreDAO membreDAO = new MembreDAO();
        ArrayList<Membre> al;
        al = membreDAO.readAll();
        for (Membre m : al){
            System.out.println(m.toString());
        }
        Membre g = new Membre(0,"Boubiboubi","Fredo ","durant.jean@mail.be","jeanjean","condorcet","",241);
        int id_membre = membreDAO.create(g);
        System.out.println("Le nouveau membre port l'id : " + id_membre);

    }
}
