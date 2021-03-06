package com.example.leila.androidproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DAO.MembreDAO;
import Metier.Membre;

/**
 * Created by Leïla on 17-11-16.
 */

public class CreaMembre extends AppCompatActivity {

    private EditText getNomMembre;
    private String nomMembre;
    private EditText getPrenomMembre;
    private String prenomMembre;
    private EditText getEmail;
    private String email;
    private EditText getPseudo;
    private String pseudo;
    private EditText getMdp;
    private String mdp;
    private Button btnEnvoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_membre);

        btnEnvoi = (Button)findViewById(R.id.btnEnvoi);
        btnEnvoi.setOnClickListener(v->{
            getNomMembre = (EditText) findViewById(R.id.inputNomMembre);
            nomMembre = getNomMembre.getText().toString();
            getPrenomMembre= (EditText) findViewById(R.id.inputPrenomMembre);
            prenomMembre = getPrenomMembre.getText().toString();
            getEmail = (EditText) findViewById(R.id.inputEmail);
            email = getEmail.getText().toString();
            getPseudo = (EditText) findViewById(R.id.inputPseudo);
            pseudo = getPseudo.getText().toString();
            getMdp = (EditText) findViewById(R.id.inputMdp);
            mdp = getMdp.getText().toString();

            InsertMembre insertMembre = new InsertMembre(CreaMembre.this);
            insertMembre.execute();
        });
    }

    private class InsertMembre extends AsyncTask<String,Integer,Boolean>{

        String result;

        public InsertMembre(CreaMembre linkActivity) {
            link(linkActivity);
        }
        private void link(CreaMembre pActivity) {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Ajout en cours",Toast.LENGTH_LONG);
            btnEnvoi.setEnabled(false);
        }


        @Override
        protected Boolean doInBackground(String... params) {
            Membre membre=new Membre(0,nomMembre,prenomMembre,email,pseudo,mdp);
            MembreDAO membreDAO= new MembreDAO();
            try{
                int i = membreDAO.create(membre);
                if(i < 1){
                    return false;
                }
            }catch (Exception e){
                System.err.println(e);
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            btnEnvoi.setEnabled(true);
            if(aBoolean){
                Toast.makeText(getApplicationContext(),"Vous etes desormais inscrit",Toast.LENGTH_LONG).show();
                Intent t = new Intent(CreaMembre.this,Authentification.class);
                startActivity(t);
            }
            else{
                Toast.makeText(getApplicationContext(),"Erreur lors de l'ajout ",Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}
