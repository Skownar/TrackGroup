package DAO;

import com.example.leila.androidproject.Groupe;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.UriBuilder;

/**
 * Created by lafer on 16-11-16.
 */

public class groupeDAO implements DAO<Groupe> {
    Client c;
    URI uri;
    WebResource service;
    ArrayList<Groupe> al;
    public groupeDAO() {
        c = new Client();
        uri = UriBuilder.fromUri("test").build();
        service = c.resource(uri);
        al = new ArrayList<>();
    }

    @Override
    public ArrayList<Groupe> readAll() {
        String hello = "";
        String bobo = "";
        return null;
    }

    @Override
    public boolean delete() {

        return true;
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
