package com.example.leila.androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import Managers.SessionManager;

public class InviterMembre extends AppCompatActivity {

    EditText destinataire;
    ListView listMembre;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inviter_membre);

        listMembre=(ListView)findViewById(R.id.listeMembresInvit);
        destinataire=(EditText)findViewById(R.id.inputNomMembreInvite);

        listMembre.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


}
