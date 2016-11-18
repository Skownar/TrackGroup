package DAO;

import com.example.leila.androidproject.Membre;

import java.util.ArrayList;

/**
 * Created by Leïla on 18-11-16.
 */

public class ListeMembre extends ListeApex<Membre> {
    @Override
    public void setItems(ArrayList<Membre> list) {

        items=list;
    }


    @Override
    public ArrayList<Membre> getItems() {
        return items;
    }
}
