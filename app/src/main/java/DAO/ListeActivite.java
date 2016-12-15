package DAO;

import java.util.ArrayList;

import Metier.Activite;


/**
 * Created by stephanie on 07-12-16.
 */

public class ListeActivite extends ListeApex<Activite> {

    @Override
    public void setItems(ArrayList<Activite> list) {items=list; }

    @Override
    public ArrayList<Activite> getItems() {return items;}
}
