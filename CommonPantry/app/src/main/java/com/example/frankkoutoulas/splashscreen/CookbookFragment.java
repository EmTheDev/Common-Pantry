package com.example.frankkoutoulas.splashscreen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by miles on 12/7/15.
 */

/**
 * Cookbook display for saved recipes
 * NEWdata add new recipe object to database
 * Returns Recipe Objects that will be displayed on the screen.
 */
public class CookbookFragment extends Fragment {

    View mView;
    SQLiteHandler NEWdata;
    MainActivity activity;
    int dbSize;



    public void CookbookFragment (){


    }

    public void onCreate(){
    }

    /**
     * Returns recipe strings which are passed into database
     * ListView has list of recipes which are provided by NEWdata
     * Contains array adapter
     * @param inflater
     * @param container
     * @param savedInstanceState uses bundle to pass recipe to cookbook
     * @return mView allows cookbook to be returned with list of saved recipes
     */
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState){

        final Context context = getActivity();

        mView = inflater.inflate(R.layout.fragment_cookbook, container, false);

        SQLiteHandler NEWdata = new SQLiteHandler(context);

        final List<RecipeObject> recipes = NEWdata.getListofRecipes();
        final ListView listView = (ListView) mView.findViewById(R.id.listView1);


        String[] idtest = new String[recipes.size()];
        String[] recidtest = new String[recipes.size()];
        String[] values = new String[recipes.size()];
        String[] urltest = new String[recipes.size()];
        String[] apitest = new String[recipes.size()];


        for (int i = 0; i < recipes.size(); i++){
            values[i] = recipes.get(i).getName();
        }

        /**
         * ArrayAdapter allows saved recipes to be displayed with textview
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                R.layout.list_item, R.id.text1, values){
        };

        dbSize = adapter.getCount();

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                /**
                 * Prints out recipe details on cookbook page
                 * ListView; view recipes as a list
                 * Recipe is clickable in order to be able to view it on the web later
                 *position the position of the recipe object
                 */
                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                RecipeObject recipe = recipes.get(position);
                System.out.println(recipe.getName());
                System.out.println(recipe.getApi());
                System.out.println(recipe.getUrl());
                System.out.println(recipe.getRecid());
                System.out.println(recipe.getId());


                activity.setWebView(recipe);
                /*
                * Get the recipe ID and API, and pass them to WebViewFragment.
                * */

            }

        });

        return mView;
    }

    /**
     * @param act activity
     */
    public void setActivity(MainActivity act){
        activity = act;
        NEWdata = new SQLiteHandler(activity);
        return;
    }

    /**
     * add recipeObject to database
     * allows new recipeObject to be added to database without deleting previous recipes
     * recordSerial let RowCount increase so we can add more recipes in a list without replacing
     * NEWdata is added to list
     * NEWdata includes elements of recipeObject
     * @param recipeObject recipeObject to be added to cookbook
     */
    public void addToCookbook(RecipeObject recipeObject){


        String recordSerial = String.valueOf(NEWdata.getRowCount() + 1);

        NEWdata.insertIntoDatabase(new RecipeObject(recordSerial, recipeObject.getRecid(),
                recipeObject.getName(), recipeObject.getUrl(), recipeObject.getApi()));
    }
}
