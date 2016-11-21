package DAO;

import android.widget.Switch;

/**
 * Created by lafer on 21-11-16.
 */

public class ExceptionManager {
    private static final String UNIQUE_KEY = "ORA-00001";
    private static final String FK_NOT_FOUND = "";
    private static final String NO_MATCH = "";

    public static String checkError(String chaine){
        if(chaine.contains(UNIQUE_KEY)){
            return "Déjà pris";
        }
        else if(chaine.contains(FK_NOT_FOUND)){
            return "FK introuvable";
        }
        else if(chaine.contains(NO_MATCH)){
            return "Champs introuvable";
        }
        else{
            return "Erreur non définie";
        }

    }
}
