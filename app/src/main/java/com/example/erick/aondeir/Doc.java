package com.example.erick.aondeir;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erick on 16/09/2017.
 */

public class Doc {

    private String post_title;
    private String post_image_thumbnail;
    private String place_name;
    private String place_geolocation;
    private String place_neighborhood;
    //private JSONArray event_datetime;

    public Doc(String post_title, String post_image_thumbnail, String place_name, String place_geolocation, String place_neighborhood) {
        this.post_title = post_title;
        this.post_image_thumbnail = post_image_thumbnail;
        this.place_name = place_name;
        this.place_geolocation = place_geolocation;
        this.place_neighborhood = place_neighborhood;
        //this.event_datetime = event_datetime;
    }

    public String getPost_title() {return post_title;}

    public String getPost_image_thumbnail() {return post_image_thumbnail;}

    public String getPlace_name() {return place_name;}

    public String getPlace_geolocation() {return place_geolocation;}

    public String getPlace_neighborhood() {return place_neighborhood;}

    //public JSONArray getEvent_datetime() {return event_datetime;}
}
