package DAO;

import com.example.leila.androidproject.Groupe;

import java.util.ArrayList;

/**
 * Created by lafer on 16-11-16.
 */

public class groupeDAO implements DAO<Groupe> {

    @Override
    public ArrayList<Groupe> readAll() {
        return null;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public int create() {
        return 0;
    }

    @Override
    public Groupe readById() {
        return null;
    }
}
