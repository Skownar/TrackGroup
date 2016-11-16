package DAO;

import java.util.ArrayList;

/**
 * Created by lafer on 16-11-16.
 *
 * classe abstraite à overrider pour chaque liste de classe métier
 *
 */

public abstract class ListeApex<T> {
    protected ArrayList<T> items;
    protected DeplacementJsonApex next;
    protected DeplacementJsonApex previous;
    protected DeplacementJsonApex first;
    public abstract void setItems(ArrayList<T> list);
    public abstract ArrayList<T> getItems();
}
