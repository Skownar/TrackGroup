package Managers;

/**
 * Created by lafer on 03-12-16.
 */


import android.util.Log;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import org.json.JSONException;
import org.json.JSONObject;

public class PubNubManager {

    public final static String TAG = "PUBNUB";

    public static Pubnub startPubnub() {
        Log.d(TAG, "Initializing PubNub");
        Pubnub pubnub =  new Pubnub("pub-c-c3d2d821-1716-46b7-a56b-00f0381b406a", "sub-c-5ea376f8-bafa-11e6-b07a-0619f8945a4f");
        return pubnub;
    }

    public static void subscribe(Pubnub mPubnub, String channelName, Callback subscribeCallback) {
        // Subscribe to channel
        try {
            mPubnub.subscribe(channelName, subscribeCallback);
            Log.d(TAG, "Subscribed to Channel");
        } catch (PubnubException e) {
            Log.e(TAG, e.toString());
        }
    }

    public static void broadcastLocation(Pubnub pubnub, String channelName, double latitude,
                                         double longitude, double altitude,String uuid) {
        JSONObject message = new JSONObject();
        try {
            message.put("lat", latitude);
            message.put("lng", longitude);
            message.put("alt", altitude);
            message.put("uuid",uuid);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
        Log.d(TAG, "Sending JSON Message: " + message.toString());
        pubnub.publish(channelName, message, publishCallback);
    }

    public static Callback publishCallback = new Callback() {

        @Override
        public void successCallback(String channel, Object response) {
            Log.d("PUBNUB", "Sent Message: " + response.toString());
        }

        @Override
        public void errorCallback(String channel, PubnubError error) {
            Log.d("PUBNUB", error.toString());
        }
    };
}

