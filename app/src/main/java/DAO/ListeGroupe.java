package DAO;

import Metier.Groupe;

import java.util.ArrayList;

/**
 * Created by lafer on 16-11-16.
 *
 * implementation de liste apex pour le groupe
 */

public class ListeGroupe extends ListeApex<Groupe> {


    @Override
    public void setItems(ArrayList<Groupe> list) {
        items = list;
    }

    @Override
    public ArrayList<Groupe> getItems() {
        return items;
    }
}
