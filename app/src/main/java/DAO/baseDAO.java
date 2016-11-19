package DAO;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

/**
 * Created by lafer on 19-11-16.
 */

public class BaseDAO {
    protected Client c;
    protected URI uri;
    protected WebResource service;
    protected Gson gson;
    protected String json;
    protected ClientResponse response;
    protected int status;

    public BaseDAO(){
        System.out.println("-- new MEMBREDAO --");
        try {
            c = Client.create();
            uri = UriBuilder.fromUri("https://apex.oracle.com/pls/apex/locagroup").build();
            service = c.resource(uri);
            gson = new Gson();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
