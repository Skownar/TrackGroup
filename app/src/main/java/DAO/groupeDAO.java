package DAO;

import android.net.Uri;

import com.example.leila.androidproject.Groupe;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.UriBuilder;

/**
 * Created by lafer on 16-11-16.
 */

public class GroupeDAO implements DAO<Groupe> {
    Client c;
    URI uri;
    WebResource service;
    public GroupeDAO(){
        System.out.println("-- new GROUPEDAO --");
        c = Client.create();
        uri = UriBuilder.fromUri("https://apex.oracle.com/pls/apex/locagroup").build();
        service = c.resource(uri);
    }

    @Override
    public ArrayList<Groupe> readAll() {

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
