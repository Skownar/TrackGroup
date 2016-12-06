package DAO;

import Metier.Activite;

import java.util.ArrayList;

/**
 * Created by lafer on 16-11-16.
 *
 * @param T : Classe
 *
 *        Interface à implémenter pour chaque classe métier
 */

public interface DAO<T> {

    /**
     *
     * ORAEXCEPTION
     *
     * ORA-00001 : unique value exception
     * ORA-02291 : integrity error
     */
    ArrayList<T> readAll();
    T readById();
    int create(T act);

    boolean delete();
    boolean update(T obj);
}
