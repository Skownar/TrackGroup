package com.example.leila.androidproject;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
/*import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;*/

import org.json.JSONException;
import org.json.JSONObject;

// TODO mettre un peu d'ordre dans cette classe
public class AfficherCarte extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_carte);
        String channelName = "testChannel";
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);

        // start follow
        FollowLocation fl = new FollowLocation(this,channelName);
        mapFragment.getMapAsync(fl);
        fl.execute();


    }

    private class FollowLocation extends AsyncTask<String,Integer,Boolean> implements OnMapReadyCallback{
        private Context _context;
        private String _channelName;
        public FollowLocation(Context context,String channelName) {
            this._context = context;
            this._channelName = channelName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
        }

        @Override
        protected Boolean doInBackground(String... params) {
            return null;
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {

        }
    }

}
