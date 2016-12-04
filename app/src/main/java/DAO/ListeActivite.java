package DAO;

import com.example.leila.androidproject.Activite;

import java.util.ArrayList;

/**
 * Created by stephanie on 01-12-16.
 */

public class ListeActivite  extends ListeApex<Activite>{
    @Override
    public void setItems(ArrayList<Activite> list) {
        setItems(list);

    }

    @Override
    public ArrayList<Activite> getItems() {
        return getItems();
    }


}
