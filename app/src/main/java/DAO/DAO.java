package DAO;

import java.util.ArrayList;

/**
 * Created by lafer on 16-11-16.
 */

public interface DAO<T> {
    public ArrayList<T> readAll();
    public T readById();
    public int create();
    public boolean delete();
    public boolean update();
}
