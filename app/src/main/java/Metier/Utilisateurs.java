package Metier;

import com.google.android.gms.maps.model.Marker;

/**
 * Created by lafer on 03-12-16.
 */

public class Utilisateurs {
    public double lat;
    public double lon;
    public String uuid;
    public Marker marker;
    public Utilisateurs(Double lat, Double lon, String uuid) {
        this.lat = lat;
        this.lon = lon;
        this.uuid = uuid;
    }

    public Utilisateurs() {
    }

    public Utilisateurs(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Utilisateurs{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", uuid='" + uuid + '\'' +
                ", marker=" + marker +
                '}';
    }
}
