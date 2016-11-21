package com.example.leila.androidproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DAO.ExceptionManager;
import DAO.GroupeDAO;

/**
 * Created by lafer on 15-11-16.
 */
public class CreaGroupe extends AppCompatActivity {
    private Button btnCreate;
    private EditText getNomGroupe;
    private String nomGroupe;
    private TextView textOperation;
    private String exception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_groupe);
        textOperation = (TextView) findViewById(R.id.textOperation);
        btnCreate = (Button) findViewById(R.id.btnCreaGroupe);
        btnCreate.setOnClickListener(v -> {
                    getNomGroupe = (EditText) findViewById(R.id.inputNomGroupe);
                    nomGroupe = getNomGroupe.getText().toString();
                    if (nomGroupe.length() > 4) {
                        InsertGroupe insertGroupe = new InsertGroupe(CreaGroupe.this);
                        insertGroupe.execute();
                    } else {
                        Toast.makeText(this.getApplicationContext(), "Nom de groupe trop court", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    class InsertGroupe extends AsyncTask<String, Integer, Boolean> {

        private String resultat;

        public InsertGroupe(CreaGroupe linkActivity) {

            link(linkActivity);

        }

        private void link(CreaGroupe pActivity) {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //textOperation.setText("Operation de creation en cours !");
            btnCreate.setEnabled(false);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            Groupe groupe = new Groupe(0, nomGroupe, 41);
            GroupeDAO groupeDAO = new GroupeDAO();
            try {
                int i = groupeDAO.create(groupe);
                if (i < 1) {
                    return false;
                }
                else return true;
            } catch (Exception e) {
                System.err.println(e);
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            btnCreate.setEnabled(true);
            if (aBoolean) {
                Toast.makeText(getApplicationContext(), "Création réussie", Toast.LENGTH_LONG).show();
                Intent t = new Intent(CreaGroupe.this, MainActivity.class);
                startActivity(t);
            } else
                Toast.makeText(getApplicationContext(), ExceptionManager.checkError(), Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
