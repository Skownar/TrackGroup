package com.example.leila.androidproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import DAO.ActiviteDAO;
import Metier.Activite;

/**
 * Created by stephanie on 12-12-16.
 */

public class CreateAct extends AppCompatActivity{

    private EditText getTitreActivite;
    private String titreActivite;
    private EditText getDescriptionActivite;
    private String descriptionActivite;
    private EditText getDateAct;
    private String dateAct;
    private Button btnEnvoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activite);

        btnEnvoi = (Button)findViewById(R.id.btnEnvoi);
        btnEnvoi.setOnClickListener(v->{
            getTitreActivite = (EditText) findViewById(R.id.inputTitreActivite);
            titreActivite = getTitreActivite.getText().toString();
            getDescriptionActivite= (EditText) findViewById(R.id.inputDescriptionActivite);
            descriptionActivite = getDescriptionActivite.getText().toString();
            getDateAct = (EditText) findViewById(R.id.inputDateAct);
            dateAct = getDateAct.getText().toString();
            CreateAct.InsertActivite insertActivite = new CreateAct.InsertActivite(CreateAct.this);
            insertActivite.execute();
        });
    }



    private class InsertActivite extends AsyncTask<String,Integer,Boolean> {

        String result;

        public InsertActivite(CreateAct linkActivity) {
            link(linkActivity);
        }
        private void link(CreateAct pActivity) {
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Ajout en cours",Toast.LENGTH_LONG);
            btnEnvoi.setEnabled(false);
        }


        @Override
        protected Boolean doInBackground(String... params) {
            Activite activite=new Activite(0,titreActivite,descriptionActivite,dateAct);
            ActiviteDAO activiteDAO= new ActiviteDAO();
            try{
                int i = activiteDAO.create(activite);
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
                Toast.makeText(getApplicationContext(),"Vous avez une activite",Toast.LENGTH_LONG).show();
                Intent t = new Intent(CreateAct.this,Authentification.class);
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
