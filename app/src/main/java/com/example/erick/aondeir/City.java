package com.example.erick.aondeir;

/**
 * Created by Erick on 23/09/2017.
 */

public class City {
    private String cityName;
    private String geoLocation;

    public City(String cityName, String geoLocation) {
        this.cityName = cityName;
        this.geoLocation = geoLocation;
    }

    public String getCityName() {
        return cityName;
    }

    public String getGeoLocation() { return geoLocation; }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }
}
