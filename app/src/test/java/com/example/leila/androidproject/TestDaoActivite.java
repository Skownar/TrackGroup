package com.example.leila.androidproject;

import java.util.ArrayList;

import DAO.ActiviteDAO;
import Metier.Activite;

/**
 * Created by stephanie on 01-12-16.
 */

public class TestDaoActivite {
    public static void main(String[] args) {
        ActiviteDAO ActiviteDAO = new ActiviteDAO();
        ArrayList<Activite> al;
        al = ActiviteDAO.readAll();
        for (Activite a : al){
            System.out.println(a.toString());
        }
        Activite a = new Activite(0,"Act1","randonne","10/11/2016",1);
        int idactivite = ActiviteDAO.create(a);
        System.out.println("Activite a pour id : " + idactivite);
    }
}
