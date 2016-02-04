package com.example.frankkoutoulas.splashscreen;


import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by miles on 10/14/15.
 * <p/>
 * The SpoonacularNetworking class handles interactions with the Spoonacular API and deserializes
 * the server responses from JSON into Java objects
 */
public class SpoonacularNetworking extends Thread {


    OkHttpClient client = new OkHttpClient(); //Client for all HTTP requests
    String jsonResponse; // Declaring here so that it's available in broad scope
    MainActivity activity;
    SearchFragment searchFragment;

    SearchResultsAdapter adapter;

    /*
    * doGetRequest can handle any of Spoonacular's GET request queries.  It does not handle
    * POST requests.  It requires a reference to the calling activity and the application's
    * context.  The activity reference is required to be able to update the UI with the results
    * of the Spoonacular API callback.  The context is required to enable caching for OkHttp.
    * */
    void doGetRequest(Context context, final ArrayAdapter adapter, final String url) throws IOException {

        //Read API key from external file.
        String apiKey = "";


        apiKey = "MUZFrUN5n0mshD8o72J0rHSoLbdMp1UMHCDjsnWnRdEKN4OQbK";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Mashape-Key", apiKey)
                .addHeader("Accept", "application/json")
                .build();

        //Setup caching for the HTTP client
        File cacheDirectory = new File(context.getCacheDir(), "http");
        int cacheSize = 10 * 1024 * 1024; //Set aside 10 MB of space on the device for caching
        try {
            Cache cache = new Cache(cacheDirectory, cacheSize);
            client.setCache(cache);
        } catch (Exception cacheException) {
            cacheException.printStackTrace();
        }

        //Asynchronous GET request
        client.newCall(request).enqueue(new Callback() {

            //The below activity all happens when the Callback is received.
            @Override
            public void onFailure(Request request, IOException ioe) {
                System.out.println("The request failed.");
                ioe.printStackTrace();
                return;
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                //The below occurs only in the case of a successful response.
                Headers responseHeaders = response.headers();

                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }


                //Print the server response to the console
                jsonResponse = response.body().string();
                System.out.println(jsonResponse);

                //Convert the JSON array to a Java data structure containing RecipeResult objects
                final ArrayList recipeResult = jsonize(jsonResponse);

                //insert images into the list
                /*for (int i = 0; i < recipeResult.size(); i++){
                    System.out.println("Attempting to insert image " + i);
                    RecipeResult current = (RecipeResult) recipeResult.get(i);
                    System.out.println(current.getImage());
                    //insertImage(current);
                    try{Thread.sleep(1000);}catch(InterruptedException ie){ie.printStackTrace();}
                    System.out.println("Inserted image " + i);

                }*/
                //System.out.println(recipeResult.size());

                //Create an ArrayList to populate the ListView with
                final SharedResultList<RecipeAPIResult> sharedResultList = SharedResultList.getInstance();
                for (int i = 0; i < recipeResult.size(); i++) {
                    RecipeResult recipe = (RecipeResult) recipeResult.get(i);
                    String id = String.valueOf(recipe.getId());


                    RecipeAPIResult spoonacularResult = new RecipeAPIResult();

                    spoonacularResult.setApiName("Spoonacular");
                    spoonacularResult.setId(id);
                    spoonacularResult.setImageURL(recipe.getImage());
                    spoonacularResult.setTitle(recipe.getTitle());

                    sharedResultList.add(spoonacularResult);
                }


                System.out.println(sharedResultList.size());

                //Construct a formatted string of the results
                String formattedResult = "";
                for (int i = 0; i < sharedResultList.size(); i++) {
                    RecipeAPIResult thisRecipe = (RecipeAPIResult) sharedResultList.get(i);
                    formattedResult += thisRecipe.toString();
                }


                //the compiler said the String must be declared final because it is accessed from an inner class
                final String uiReadyResult = formattedResult;

                //Get the main UI thread and update the relevant text box with the formatted results
                new Handler(Looper.getMainLooper()).post(new Runnable() {

                    //Update the appropriate TextView with the JSON response
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();

                    }
                });

            }
        });
        return;

    }

    public void doGetRecipe(final Context context,  String id) {

        String url = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/" + id + "/information";
        Request request = new Request.Builder()
                .url(url)
                .addHeader("X-Mashape-Key", "MUZFrUN5n0mshD8o72J0rHSoLbdMp1UMHCDjsnWnRdEKN4OQbK")
                .addHeader("Accept", "application/json")
                .build();

        File cacheDirectory = new File(context.getCacheDir(), "http");
        int cacheSize = 10 * 1024 * 1024; //Set aside 10 MB of space on the device for caching
        try {
            Cache cache = new Cache(cacheDirectory, cacheSize);
            client.setCache(cache);
        } catch (Exception cacheException) {
            cacheException.printStackTrace();
        }

        //Asynchronous GET request
        client.newCall(request).enqueue(new Callback() {

            //The below activity all happens when the Callback is received.
            @Override
            public void onFailure(Request request, IOException ioe) {
                System.out.println("The request failed.");
                ioe.printStackTrace();
                return;
            }

            @Override
            public void onResponse(final Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                //The below occurs only in the case of a successful response.
                Headers responseHeaders = response.headers();

                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }


                //Print the server response to the console
                jsonResponse = response.body().string();
                System.out.println(jsonResponse);

                //parse for the original recipe URL
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser parser = new JsonParser();

                JsonObject jsonObject = parser.parse(jsonResponse).getAsJsonObject();
                SpoonacularRecipe thisRecipe = gson.fromJson(jsonObject, SpoonacularRecipe.class);
                System.out.println(thisRecipe.getSourceUrl());

                String url = thisRecipe.getSourceUrl();

                RecipeObject recObj = new RecipeObject();
                recObj.setUrl(url);
                recObj.setApi("Spoonacular");
                recObj.setId(String.valueOf(thisRecipe.getId()));
                recObj.setName(thisRecipe.getTitle());
                activity.setWebView(recObj);




            }
        });
    }


    /*
    * The jsonize method takes a string containing an array of RecipeResult JSON objects
    * and converts it into a Java ArrayList containing RecipeResults.
    * */
    ArrayList<RecipeResult> jsonize(String jsonString) {

        //Set-up Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser parser = new JsonParser();

        //The response from the server is a JSON array, not a JSON object
        JsonArray jArray = parser.parse(jsonString).getAsJsonArray();

        //todo - discuss if ArrayList is the best data structure for this
        ArrayList<RecipeResult> rResult = new ArrayList<RecipeResult>();

        //Add each individual JSON object in the array to the Java data structure
        for (JsonElement obj : jArray) {
            RecipeResult recipeItem = gson.fromJson(obj, RecipeResult.class);
            rResult.add(recipeItem);
        }


        return rResult;

    }

    public void setAdapter(SearchResultsAdapter ada){
        adapter = ada;
    }

    public void setActivity(MainActivity act){
        activity = act;
        return;
    }
/*
    public void insertImage(final RecipeResult recipeResult){
        String imageUrl = recipeResult.getImage();
        Uri formattedImageUri = Uri.parse(imageUrl);
        URL url;

        Picasso.with(activity).load(formattedImageUri).resize(64,64).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                Drawable d = new BitmapDrawable(activity.getResources(), bitmap);
                recipeResult.setPhoto(d);
            }

            @Override
            public void onBitmapFailed(Drawable drawable) {

            }

            @Override
            public void onPrepareLoad(Drawable drawable) {

            }
        });

    }*/

}

