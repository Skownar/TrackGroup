package com.example.leila.androidproject;

import android.content.Intent;
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
import android.widget.TextView;

import java.util.HashMap;

public class MainActivityPanel extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SessionManager sessionManager;
    TextView tvNomPrenom;
    TextView tvEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_panel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // inclus les layout à l'activité
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // logique de l'activité
        // ATTENTION : etre attentif a cette portion de code
        // START /!\
        // récuperation des info du membre connecté
        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();
        HashMap<String,String> membreDetails = sessionManager.getInformations();
        String nomPrenom = membreDetails.get(SessionManager.KEY_NOM) + " " + membreDetails.get(SessionManager.KEY_PRENOM);
        String email = membreDetails.get(SessionManager.KEY_EMAIL);
        System.out.println("-----------------NOM PRENOM " + nomPrenom);

        // creation d'un pointeur vers la vue du header du panneau lateral (ici son header pour les nom prenom email)
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
        //getMenuInflater().inflate(R.menu.main_activity_panel, menu);
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
}
