package DAO;

import java.util.ArrayList;

import Metier.Invitation;

/**
 * Created by Leïla on 14-12-16.
 */

public class ListeInvitation extends ListeApex<Invitation> {

    @Override
    public void setItems(ArrayList<Invitation> list) {
        items = list;
    }

    @Override
    public ArrayList<Invitation> getItems() {
        return items;
    }
}
