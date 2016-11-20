package MOCK;


import com.example.leila.androidproject.Groupe;

import java.util.ArrayList;

/**
 * Created by stephanie on 20-11-16.
 */

public class GroupeMOCK {
      public ArrayList<Groupe> listegroupe = new ArrayList<Groupe>(){};
      public void create(int id , Groupe nom ){
        listegroupe.add(id,nom);
      }

      public Groupe read(int id){
            return listegroupe.get(id);
        }

      public ArrayList<Groupe> readAll(){
            return listegroupe;
        }

      public Boolean update(){
          return false;
      }

    public Boolean delete(){
        return false;
    }
}
