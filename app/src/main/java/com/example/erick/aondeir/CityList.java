package com.example.erick.aondeir;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Erick on 23/09/2017.
 */

public class CityList {
    private List<City> cities = new ArrayList<City>();

    public void makeCityList(){

        cities.add(new City("São josé dos Campos", "-23.17944,-45.88694"));
        cities.add(new City("Jacareí", "-23.30528,-45.96583"));
        cities.add(new City("Ubatuba", "-23.43389,-45.07111"));
        cities.add(new City("Florianópolis", "-27.593500,-48.558540"));
    }
    public List returnCitiesName(){
        List<String> found = new LinkedList<String>();
        makeCityList();
        for(City city: cities){
                found.add(city.getCityName());
        }
        return found;
    }
    public String returnGeoLocByCityName(String cityName){
        String found = new String();
        makeCityList();
        for(City city: cities){
            if(city.getCityName().equals(cityName))
                found = city.getGeoLocation();
        }
        return found;
    }
}