package com.example.leila.androidproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import DAO.GroupeDAO;

public class CreaGroupe extends AppCompatActivity {
    private Button btnCreate ;
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
            exception = " ";
            InsertGroupe insertGroupe = new InsertGroupe(CreaGroupe.this);
            insertGroupe.execute();
        }
        );
    }

    class InsertGroupe extends AsyncTask<String,Integer,Boolean>{

        private String resultat;
        public InsertGroupe(CreaGroupe linkActivity) {

            link(linkActivity);
            // TODO Auto-generated constructor stub
        }
        private void link(CreaGroupe pActivity) {
            // TODO Auto-generated method stub

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textOperation.setText("Operation de creation en cours !");
            btnCreate.setEnabled(false);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            Groupe groupe = new Groupe(0,nomGroupe,41);
            GroupeDAO groupeDAO = new GroupeDAO();
            try {
                int i = groupeDAO.create(groupe);
            }catch (Exception e){
                System.err.println(e);
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            btnCreate.setEnabled(true);
            if(aBoolean) textOperation.setText("Creation reussie");
            else textOperation.setText("Erreur lors de l'ajout :s ");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

}
