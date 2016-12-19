package com.example.leila.androidproject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import DAO.MembreDAO;
import Managers.AsyncTaskTools;
import Managers.SessionManager;
import Metier.Membre;

public class InviterMembre extends AppCompatActivity {

    EditText editTextDestinataire;
    ListView listMembre;
    int id_groupe ;

    SessionManager sessionManager;
    String nomRech;
    List listToAdapter;
    boolean isZerochar = false;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // MERCIIIII !
        StrictMode.setThreadPolicy(policy);   // OK FRERE

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inviter_membre);

        // initialisation
        listMembre = (ListView) findViewById(R.id.listViewInviteMembre);
        editTextDestinataire = (EditText) findViewById(R.id.editTextDesti);

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        id_groupe = sessionManager.getKeyIdGroupeChoisi();

        editTextDestinataire.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nomRech = s.toString().toLowerCase();
                adapter.clear();
                if(nomRech.trim().length() >0){
                    AsyncTaskTools.execute(new OnChangeListing());
                }
                else{
                    isZerochar = true;
                    AsyncTaskTools.execute(new ReadyListing());
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                adapter.addAll(listToAdapter);
                adapter.notifyDataSetChanged();
            }

        });
        AsyncTaskTools.execute(new ReadyListing());

    }
    private class OnChangeListing extends AsyncTask<String,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(String... params) {
            try{
                MembreDAO membreDAO = new MembreDAO();
                listToAdapter = membreDAO.readMembreForSearchPlusName(id_groupe,nomRech);
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return true;
            }
        }


    }
    private class ReadyListing extends AsyncTask<String,Integer,Boolean>{
        @Override
        protected Boolean doInBackground(String... params) {
            try {
                MembreDAO membreDAO = new MembreDAO() ;

                System.out.println("LE GROUPE EST"+id_groupe);
                listToAdapter = new ArrayList();
                listToAdapter = membreDAO.readMembreForSearch(id_groupe);
                // quand on a tapé et qu'on supprime tout pour obtenir 0 char dans l'edittext

                if(listToAdapter != null && !isZerochar) {
                    adapter = new ArrayAdapter<String>(InviterMembre.this, android.R.layout.simple_list_item_1, listToAdapter);
                    return true;
                }else return false;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(!isZerochar) listMembre.setAdapter(adapter);
            super.onPostExecute(aBoolean);
        }
    }
}
