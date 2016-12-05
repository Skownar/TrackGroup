package com.example.leila.androidproject;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Managers.AsyncTaskTools;
import Managers.PubNubManager;
import Managers.SessionManager;
import Metier.Utilisateurs;

public class AfficherCarte extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private static final String TAG = "Build";
    // pour pubnub
    Pubnub mPubnub;
    String nomCanal;

    // pour la gestion des membres du canal
    private Utilisateurs meUser;
    private ArrayList<Utilisateurs> listUtilisateurs = new ArrayList<>();

    // Pour la localisation
    private GoogleMap _googleMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private boolean mRequestingLocationUpdates = false;
    private PolylineOptions _polylineOptions;
    private LatLng _latlng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_carte);

        this.buildGoogleApiClient();

        //récupération de l'uuid à placer dans les message pubnub
        meUser = new Utilisateurs();
        SessionManager sessionManager = new SessionManager(getApplicationContext());
        meUser.uuid = sessionManager.getKeyPseudo();
        nomCanal = "Conal";



        // récupération du fragment et synchronisation avec la map
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mPubnub = PubNubManager.startPubnub();
    }

    private synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        System.out.println("Build créer");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        _googleMap = googleMap;
        _googleMap.setMyLocationEnabled(true);
        System.out.println("Map prête");


    }

    protected void onStart() {
        googleApiClient.connect();
        PubNubManager.subscribe(mPubnub, nomCanal, subscribeCallback);
        super.onStart();
    }

    Callback subscribeCallback = new Callback() {
        @Override
        public void successCallback(String s, Object o) {
            super.successCallback(s, o);
            System.out.println("Message reçu de pubnub");
            AsyncTaskTools.execute(new ReceptionPosition(o));
        }

        @Override
        public void errorCallback(String s, PubnubError pubnubError) {
            super.errorCallback(s, pubnubError);
        }
    };

    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
        mPubnub.unsubscribe(nomCanal);
    }

    @Override
    public void onConnected(Bundle bundle) {
        System.out.println("Connexion à requestLocationUpdates");
        locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        initalizeMyMarker();

    }

    private void initalizeMyMarker() {
        System.out.println("initialisation marker");
/*
        _googleMap.clear();
        meUser.marker = _googleMap.addMarker(
                new MarkerOptions()
                        .position(new LatLng(0, 0))
                        .title("it's me")
        );*/
    }

    private void updateMyMarker() {
        /*
        _googleMap.clear();
        if (meUser.marker != null)
            meUser.marker.remove();
        meUser.marker = _googleMap.addMarker(
                new MarkerOptions()
                        .position(new LatLng(meUser.lat, meUser.lon))
                        .title("it's me")
        );*/
    }

    @Override
    public void onLocationChanged(Location location) {
        AsyncTaskTools.execute(new EnvoiePosition(this, location), meUser);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    private class EnvoiePosition extends AsyncTask<Utilisateurs, Integer, Utilisateurs> {
        private Context _context;
        private Location _location;

        public EnvoiePosition(Activity activity, Location location) {
            _context = activity.getApplicationContext();
            _location = location;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Utilisateurs doInBackground(Utilisateurs... user) {
            meUser.lat = _location.getLatitude();
            meUser.lon = _location.getLongitude();
            PubNubManager.broadcastLocation(mPubnub, nomCanal, meUser.lat, meUser.lon, _location.getAltitude(), meUser.uuid);
            return meUser;
        }

        @Override
        protected void onPostExecute(Utilisateurs utilisateurs) {
            super.onPostExecute(utilisateurs);
            meUser = utilisateurs;
            // updateMyMarker();

        }
    }

    private class ReceptionPosition extends AsyncTask<Object, Object, Utilisateurs> {
        private JSONObject _message;

        public ReceptionPosition(Object message) {
            System.out.println("message délégué à l'async task ");
            _message = (JSONObject) message;
        }

        @Override
        protected Utilisateurs doInBackground(Object... voids) {
            Utilisateurs u = new Utilisateurs();
            boolean res = false;
            boolean isHere = false;
            int posInList = 0;
            try {
                u.lat = _message.getDouble("lat");
                u.lon = _message.getDouble("lat");
                u.uuid = _message.getString("uuid");
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
            if (listUtilisateurs.isEmpty())
                listUtilisateurs.add(u);
            else {
                for (Utilisateurs user : listUtilisateurs) {
                    if (user.uuid.equals(u.uuid)) {
                        isHere = true;
                        posInList = listUtilisateurs.indexOf(user);
                        listUtilisateurs.set(posInList, u);
                    }

                }
            }
            if (!isHere) listUtilisateurs.add(u);
            return u;
        }

        @Override
        protected void onPostExecute(Utilisateurs user) {
            _googleMap.clear();
            //updateMyMarker();
            for (Utilisateurs u : listUtilisateurs){
                u.marker =  _googleMap.addMarker(
                        new MarkerOptions()
                                .position(new LatLng(u.lat, u.lon))
                                .title(u.uuid)
                );

            }
        }
    }}
