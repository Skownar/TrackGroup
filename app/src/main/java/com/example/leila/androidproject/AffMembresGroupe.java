package com.example.leila.androidproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import DAO.ContientDAO;
import DAO.MembreDAO;

public class AffMembresGroupe extends AppCompatActivity {
    TextView labelAffG;
    ListView listMembers;
    Button btnAfficherCarte;
    Button btnInviterMembre;

    SessionManager sessionManager;
    HashMap<String,String> membreDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aff_membres_groupe);

        labelAffG=(TextView) findViewById(R.id.labelAffG);
        listMembers = (ListView) findViewById(R.id.listMembers);
        btnAfficherCarte = (Button) findViewById(R.id.btnAfficherCarte);
        btnInviterMembre= (Button) findViewById(R.id.btnInviterMembre);

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        membreDetails = sessionManager.getInformations();

       // labelAffG.setText(membreDetails.get(SessionManager.KEY_GROUPE_CHOISI));

        ListingMembres listingMembres = new ListingMembres(AffMembresGroupe.this);
        listingMembres.execute();


        btnAfficherCarte.setOnClickListener(view -> {
                    Intent t = new Intent(this,AfficherCarte.class);
                    startActivity(t);
                }
        );

        btnInviterMembre.setOnClickListener(view -> {
                    Intent t = new Intent(this,InviterMembre.class);
                    startActivity(t);
                }
        );


    }

    private class ListingMembres extends AsyncTask<String,Integer,Boolean> {
        ArrayAdapter<String> adapter;

        public ListingMembres(AffMembresGroupe lien){}


        @Override
        protected Boolean doInBackground(String... params) {
            ContientDAO contientDAO = new ContientDAO();
            try {
                int id_groupe = Integer.parseInt(membreDetails.get(SessionManager.KEY_GROUPE_CHOISI));

                List listMemb = contientDAO.readMembersByGroup(id_groupe);

                adapter = new ArrayAdapter<String>(AffMembresGroupe.this,android.R.layout.simple_list_item_1, listMemb);
                return true;
            }catch (Exception e){
                System.err.println(e.toString());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            listMembers.setAdapter(adapter);

            super.onPostExecute(aBoolean);
        }
    }
}
