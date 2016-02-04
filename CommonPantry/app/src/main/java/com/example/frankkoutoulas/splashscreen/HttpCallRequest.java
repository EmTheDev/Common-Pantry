package com.example.frankkoutoulas.splashscreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 11/10/15.
 */
public class HttpCallRequest {
    OkHttpClient client = new OkHttpClient();
    String jsonP;
    MainActivity activity;
    SearchResultsAdapter adapter;

    void doGetRequest(Context context, final ArrayAdapter adapter, final String url) throws IOException {

    /*String yummlyStuff = "http://api.yummly.com/v1/api/recipes?" +
            "_app_id=" + "11a7a04b" +
            "&_app_key=" + "9c7c996ed0ad0904bff15e755a2ccccc" + "&q=onion+soup";
*/
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);

        File cacheDirectory = new File(context.getCacheDir(), "http");
        int cacheSize = 10 * 1024 * 1024;
        try {
            Cache cache = new Cache(cacheDirectory, cacheSize);
            client.setCache(cache);
        }catch (Exception e){
            e.printStackTrace();
        }


        //Asynchronus GET request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("The request failed.");
                e.printStackTrace();
                return;
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if(!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();

                for (int i = 0; i < responseHeaders.size(); i++){
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                jsonP = response.body().string();
                System.out.println(jsonP);


                ArrayList recipeResult = null;
                try {
                    recipeResult = getJSON(jsonP);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(recipeResult.size());

                SharedResultList<RecipeAPIResult> sharedResultList = SharedResultList.getInstance();
                for (int i = 0; i < recipeResult.size(); i++){
                    YummlyRecipeResult recipe = (YummlyRecipeResult)recipeResult.get(i);
                    String id = String.valueOf(recipe.getId());

                    RecipeAPIResult yummlyResult = new RecipeAPIResult();

                    yummlyResult.setApiName("Yummly");
                    yummlyResult.setId(id);
                    yummlyResult.setImageURL(recipe.getImageUrlsBySize());
                    yummlyResult.setTitle(recipe.getRecipeName());
                    sharedResultList.add(yummlyResult);
                }



                String formattedResult = "";
                for (int i = 0; i < sharedResultList.size(); i++) {
                    RecipeAPIResult thisRecipe = (RecipeAPIResult) sharedResultList.get(i);
                    formattedResult += thisRecipe.toString();
                }

                final String uiReadyResult = formattedResult;
/*
                YummlyRecipeResult recipe1 = (YummlyRecipeResult) recipeResult.get(0);

                final String recipeName = recipe1.getRecipeName();
                final String thumbnail = recipe1.getImageUrlsBySize().toString();
*/
                //Get the main UI thread
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                        //TextView jsonTextBox = (TextView) mainActivity.findViewById(R.id.parsedJSON);
                        //jsonTextBox.setText(uiReadyResult);

                        adapter.notifyDataSetChanged();

                        /*
                        TextView recipeTitle = (TextView) mainActivity.findViewById(R.id.recipeTitle);
                        recipeTitle.setText(recipeName);

                        ImageView imageView = (ImageView) mainActivity.findViewById(R.id.imageView);
                        Picasso.with(mainActivity).load(thumbnail).resize(200,200).into(imageView);
                        */

                    }
                });
            }

        });

        return;
    }

    public void doGetRecipe(final Context context, String url){
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);

        File cacheDirectory = new File(context.getCacheDir(), "http");
        int cacheSize = 10 * 1024 * 1024;
        try {
            Cache cache = new Cache(cacheDirectory, cacheSize);
            client.setCache(cache);
        }catch (Exception e){
            e.printStackTrace();
        }

        //Asynchronus GET request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("The request failed.");
                e.printStackTrace();
                return;
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if(!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();

                for (int i = 0; i < responseHeaders.size(); i++){
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                String jsonResponse = response.body().string();
                System.out.println(jsonResponse);

                //parse for the original recipe URL
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonParser parser = new JsonParser();

                JsonObject jsonObject = parser.parse(jsonResponse).getAsJsonObject();
                YummlyRecipe thisRecipe = gson.fromJson(jsonObject, YummlyRecipe.class);
                System.out.println(thisRecipe.getSource().getSourceRecipeUrl());


                String url = thisRecipe.getSource().getSourceRecipeUrl();
                RecipeObject recipeObject = new RecipeObject();
                recipeObject.setName(thisRecipe.getName());
                recipeObject.setUrl(url);
                recipeObject.setId(thisRecipe.getId());
                recipeObject.setApi("Yummly");
                activity.setWebView(recipeObject);
            }

        });

    }

    ArrayList<YummlyRecipeResult> getJSON(String jsonString) throws JSONException{


        JSONObject recipe = new JSONObject(jsonString);
        String results = recipe.getString("matches");

        JSONArray currently = recipe.getJSONArray("matches");

        ArrayList<YummlyRecipeResult> yummlyResult = new ArrayList<YummlyRecipeResult>();
        //String[] idArray;
        for(int i=0; i< currently.length(); i++) {
            //idArray = new String[5];
            JSONObject jObj = currently.getJSONObject(i);
            YummlyRecipeResult feed = new YummlyRecipeResult();

            feed.setImageUrlsBySize(jObj.getString("imageUrlsBySize"));
            System.out.println(feed.getImageUrlsBySize());
            feed.setId(jObj.getString("id"));
            System.out.println(feed.getId());
            feed.setRecipeName(jObj.getString("recipeName"));
            System.out.println(feed.getRecipeName());


            yummlyResult.add(feed);
        }

        return yummlyResult;
    }

    public void setAdapter(SearchResultsAdapter ada){
        adapter = ada;
    }

    public void setActivity(MainActivity act){
        activity = act;
        return;
    }
}
