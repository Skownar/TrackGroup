package DAO;

import android.net.Uri;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.uri.UriBuilderImpl;

import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.UriBuilder;

/**
 * Created by lafer on 16-11-16.
 *
 * @param T : Classe
 *
 *        Interface à implémenter pour chaque classe métier
 */

public interface DAO<T> {
    ArrayList<T> readAll();
    T readById();
    int create();
    boolean delete();
    boolean update();
}
