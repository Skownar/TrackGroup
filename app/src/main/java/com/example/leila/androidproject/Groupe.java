package com.example.leila.androidproject;

/**
 * Created by Leïla on 15-11-16.
 */

public class Groupe {
    int id_groupe;
    String nom_groupe;
    int id_admin;

    public Groupe() {}

    public Groupe(int id_groupe, String nom_groupe, int id_admin) {
        this.id_groupe=id_groupe;
        this.nom_groupe=nom_groupe;
        this.id_admin=id_admin;
    }

    public int getId_groupe() {
        return id_groupe;
    }

    public void setId_groupe(int id_groupe) {
        this.id_groupe = id_groupe;
    }

    public String getNom_groupe() {
        return nom_groupe;
    }

    public void setNom_groupe(String nom_groupe) {
        this.nom_groupe = nom_groupe;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    @Override
    public String toString() {
        return "Groupe{" +
                "id_groupe=" + id_groupe +
                ", nom_groupe='" + nom_groupe + '\'' +
                ", id_admin=" + id_admin +
                '}';
    }
}
