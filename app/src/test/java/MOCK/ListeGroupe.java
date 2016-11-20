package MOCK;



import com.example.leila.androidproject.Groupe;

import java.util.ArrayList;

import DAO.ListeApex;
import java.util.ArrayList;

/**
 * Created by stephanie on 20-11-16.
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
