package com.example.leila.androidproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.Arrays;

import DAO.Identifiants;
import DAO.IdentifiantsDAO;

/**
 * Created by lafer on 15-11-16.
 */

public class Authentification extends AppCompatActivity{
    public final static String IDPERSONNE = "personne";
    private Button btnConnexion;
    private Button btnInscription;
    private TextView pseudo;
    private TextView mdp;
    private Membre membre;
    private Identifiants identifiants;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        sessionManager = new SessionManager(getApplicationContext());
        membre = new Membre();

        // Event inscription
        btnInscription = (Button) findViewById(R.id.btnSinscrire);
        btnInscription.setOnClickListener(v -> {
            Intent t = new Intent(this,CreaMembre.class);
            startActivity(t);
        });

        // Event connexion
        btnConnexion = (Button) findViewById(R.id.btnSeConnecter);
        btnConnexion.setOnClickListener(v -> {
            pseudo = (TextView) findViewById(R.id.inputPseudo);
            mdp = (TextView) findViewById(R.id.inputMdp);
            String pseudostr = pseudo.getText().toString();
            String mdpstr = mdp.getText().toString();
            if((pseudostr.length() > 0) && (mdpstr.length() > 0)){
                if((pseudostr.trim().length() > 0) && (mdpstr.length() > 0)) {
                    identifiants = new Identifiants(pseudo.getText().toString(), mdp.getText().toString());
                    Connexion c = new Connexion(Authentification.this);
                    c.execute();
                }
                else{
                    Toast.makeText(this.getApplicationContext(),"Champs invalides",Toast.LENGTH_LONG);
                }
            }
            else{
                Toast.makeText(this.getApplicationContext(),"Champs vides",Toast.LENGTH_LONG).show();
            }
        });


    }
    private class Connexion extends AsyncTask<String,Integer,Boolean>{

        private String resultat;
        public Connexion(Authentification linkActivity)

        {
            link(linkActivity);
        }
        private void link(Authentification pActivity) {

        }

        @Override
        protected void onPreExecute() {
            btnConnexion.setEnabled(false);
            btnInscription.setEnabled(false);
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean result = false;
            IdentifiantsDAO identifiantsDAO = new IdentifiantsDAO();
            try {
                membre = identifiantsDAO.read(identifiants);
                if (membre != null){
                    result = true;
                    System.out.println(membre.toString());
                    sessionManager.createLoginSession(membre);
                }
                else
                    return false;
            } catch (JSONException e) {
                e.printStackTrace();
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            btnConnexion.setEnabled(true);
            btnInscription.setEnabled(true);
            if(aBoolean){
                Toast.makeText(getApplicationContext(),"Connexion reussie",Toast.LENGTH_LONG).show();
                Intent t = new Intent(getApplicationContext(),MainActivityPanel.class);
                startActivity(t);
                finish();
            }
            else {
                Toast.makeText(getApplicationContext(),"Connexion echouee, retry",Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(aBoolean);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            System.out.println(Arrays.toString(values));
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {

            super.onCancelled(aBoolean);
        }


    }
}
