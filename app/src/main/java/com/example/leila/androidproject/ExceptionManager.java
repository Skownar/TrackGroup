package com.example.leila.androidproject;

/**
 * Created by lafer on 21-11-16.
 */

public class ExceptionManager {

    //TODO internationaliser les valeur de retour
    private static final String UNIQUE_KEY = "ORA-00001";
    private static final String FK_NOT_FOUND = "ORA-02291";
    //private static final String NO_MATCH = "";

    private static String _exception;

    public static String checkError() {
        String toReturn = "";
        if (_exception.contains(UNIQUE_KEY)) {
            toReturn = "Deja pris";
        } else if (_exception.contains(FK_NOT_FOUND)) {
            toReturn = "FK introuvable";
        }
        /*else if(_exception.contains(NO_MATCH)){
            return "Champs introuvable";
        }*/
        else {
            toReturn = "Erreur non définie";
        }
        _exception = "";
        return toReturn;
    }

    public static String get_exception() {
        return _exception;
    }

    public static void set_exception(String _exception) {
        ExceptionManager._exception = _exception;
    }
}
