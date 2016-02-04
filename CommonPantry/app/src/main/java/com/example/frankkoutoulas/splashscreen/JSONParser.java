package com.example.frankkoutoulas.splashscreen;

/**
 * Created by Kristina on 10/21/2015.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Link for response type
 * https://developers.google.com/places/web-service/details
 */

public class JSONParser {

    /** Receives a JSONObject and returns a list */
    public List<HashMap<String,String>> parse(JSONObject mObject){

        JSONArray mPlaces = null;
        try {
            /** Retrieves all the elements in the 'places' array */
            mPlaces = mObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /** Invoking getPlaces with the array of json object
         * where each json object represent a place
         */
        return getPlaces(mPlaces);
    }
    /* Puts pairs of information together */
    private List<HashMap<String, String>> getPlaces(JSONArray jPlaces){
        int placesCount = jPlaces.length();
        List<HashMap<String, String>> placesList = new ArrayList<HashMap<String,String>>();
        HashMap<String, String> place = null;

        /** Taking each place, parses and adds to list object */
        for(int i=0; i<placesCount;i++){
            try {
                /** Call getPlace with place JSON object to parse the place */
                place = getPlace((JSONObject)jPlaces.get(i));
                placesList.add(place);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return placesList;
    }

    /** Parsing the Place JSON object */
    private HashMap<String, String> getPlace(JSONObject mPlace){

        HashMap<String, String> place = new HashMap<>();
        String placeName = "NA";
        String mAddress="NA";
        String mlat="";
        String mlng="";

        try {
            // Get place name
            if(!mPlace.isNull("name")){
                placeName = mPlace.getString("name");
            }

            // Get place address
            if(!mPlace.isNull("vicinity")){
                mAddress = mPlace.getString("vicinity");
            }

            mlat = mPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
            mlng = mPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");

            place.put("place_name", placeName);
            place.put("vicinity", mAddress);
            place.put("lat", mlat);
            place.put("lng", mlng);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return place;
    }
}
