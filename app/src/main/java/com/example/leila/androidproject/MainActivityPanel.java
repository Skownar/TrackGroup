package com.example.leila.androidproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DAO.ContientDAO;
import DAO.GroupeDAO;

/**
 * Created by lafer on 28-11-16.
 */
public class MainActivityPanel extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager sessionManager;
    TextView tvNomPrenom;
    TextView tvEmail;

    ListView listGroup;
    HashMap<String,String> membreDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_panel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        listGroup=(ListView)findViewById(R.id.listView);
        setSupportActionBar(toolbar);

        ListingGroupe listingGroupe = new ListingGroupe(MainActivityPanel.this);
        listingGroupe.execute();

        // inclus les layout à l'activité
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // logique de l'activité
        // ATTENTION : etre attentif a cette portion de code
        // START /!\
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // récuperation des info du membre connecté
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        membreDetails = sessionManager.getInformations();
        String nomPrenom = membreDetails.get(SessionManager.KEY_NOM) + " " + membreDetails.get(SessionManager.KEY_PRENOM);
        String email = membreDetails.get(SessionManager.KEY_EMAIL);

        // creation d'un pointeur vers la vue du header du panneau lateral (nom prenom email)
        View navigationViewHeader = navigationView.getHeaderView(0);

        // récuperation des éléments via un findViewById specifique à la nouvelle vue pointer.
        tvNomPrenom = (TextView) navigationViewHeader.findViewById(R.id.tvNomPrenomNavBar);
        tvNomPrenom.setText(nomPrenom);
        tvEmail = (TextView) navigationViewHeader.findViewById(R.id.tvMailNavBar);
        tvEmail.setText(email);

        // END /!\


        // OnClick ADDBUTTON
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCreateGroupe);
        fab.setOnClickListener(view -> {
                    Intent t = new Intent(this,CreaGroupe.class);
                    startActivity(t);
                }
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    // inflater menu pas utile dans notre cas
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_panel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_reception) {
            // todo implémenter l'action a exécuter quand click sur item invitation reçu
        } else if (id == R.id.nav_logout) {
            // todo implémenter l'action a exécuter quand click sur item logout
            sessionManager.logOut();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class ListingGroupe extends AsyncTask<String,Integer,Boolean> {
        ArrayAdapter<String> adapter;
        public ListingGroupe(MainActivityPanel lien){

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            Boolean result = false;

            ContientDAO contientDAO = new ContientDAO();
            try {
                int id_membre = Integer.parseInt(membreDetails.get(SessionManager.KEY_ID_MEMBRE));
                //Contient contient = new Contient(id_membre);
                List listG = contientDAO.readGroupsByMember(id_membre);

                adapter = new ArrayAdapter<String>(MainActivityPanel.this,android.R.layout.simple_list_item_1, listG);

                return true;
            }catch (Exception e){
                System.err.println(e.toString());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            listGroup.setAdapter(adapter);

            super.onPostExecute(aBoolean);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {super.onProgressUpdate(values);}



        @Override
        protected void onCancelled() { super.onCancelled();}
    }
}


