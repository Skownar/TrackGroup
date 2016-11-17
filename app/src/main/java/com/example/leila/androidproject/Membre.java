package com.example.leila.androidproject;

/**
 * Created by Leïla on 16-11-16.
 */

public class Membre {
    int id_membre;
    String nom;
    String prenom;
    String email;
    String pseudo;
    String mdp;
    String localisation;
    int groupe_choisi;

    public Membre(){}

    public Membre(int id_membre, String nom, String prenom, String email, String pseudo, String mdp, String localisation,int groupe_choisi) {
        this.id_membre = id_membre;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.localisation = localisation;
        this.groupe_choisi=groupe_choisi;
    }

    public int getId_membre() {
        return id_membre;
    }

    public void setId_membre(int id_membre) {
        this.id_membre = id_membre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getGroupe_choisi() {
        return groupe_choisi;
    }

    public void setGroupe_choisi(int groupe_choisi) {
        this.groupe_choisi = groupe_choisi;
    }

    @Override
    public String toString() {
        return "Membre{" +
                "id_membre=" + id_membre +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", pseudo='" + pseudo + '\'' +
                ", mdp='" + mdp + '\'' +
                ", localisation='" + localisation + '\'' +
                ", groupe_choisi=" + groupe_choisi +
                '}';
    }
}
