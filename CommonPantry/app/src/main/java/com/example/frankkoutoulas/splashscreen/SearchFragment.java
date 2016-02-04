package com.example.frankkoutoulas.splashscreen;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by miles on 12/7/15.
 */
public class SearchFragment extends Fragment {


    SpoonacularNetworking spoonacularNetworking = new SpoonacularNetworking();
    RelativeLayout layout;
            //URL variables necessary for APIs
    String spoonacularBaseURL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com/recipes/";
    String spoonacularFindByIngredientsBaseUrl = spoonacularBaseURL + "findByIngredients?ingredients=";
    String spoonacularGetUrl = spoonacularFindByIngredientsBaseUrl; // Declaring here so that it's available in broader
    // scope, assigning here to please the compiler

    String yummlyBaseURL = "http://api.yummly.com/v1/api/recipes?" +
            "_app_id=" + "11a7a04b" +
            "&_app_key=" + "9c7c996ed0ad0904bff15e755a2ccccc&q=";


    HttpCallRequest yummlyNetworking = new HttpCallRequest();

    SharedResultList<RecipeAPIResult> sharedResultList = SharedResultList.getInstance();

    private MainActivity activity;


    private View mView;


    ArrayList<Boolean> preferences;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        final Context context = getActivity();

        mView = inflater.inflate(R.layout.fragment_search, container, false);

        layout = (RelativeLayout) mView.findViewById(R.id.layout_resource);

        Resources resources = getResources();


        final Drawable whiteImage = resources.getDrawable(R.drawable.white);



        /*
        * Begin search code
        * */

        //Clear the "enter search" message when the text box is tapped on
        final EditText userInput = (EditText) mView.findViewById(R.id.editText);
        userInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput.setText("");
            }
        });

        //Create an ArrayAdapter to populate the ListView with
        final ArrayAdapter<RecipeAPIResult> adapter = new ArrayAdapter<RecipeAPIResult>(context, R.layout.mylist, R.id.Itemname, sharedResultList);
        ListView lv = (ListView) mView.findViewById(R.id.listView);
        lv.setAdapter(adapter);


        //Get a recipe when it is tapped on in the ListView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (sharedResultList.get(position).getApiName().equals("Spoonacular")) {
                    spoonacularNetworking.setActivity(activity);
                    spoonacularNetworking.doGetRecipe(context, sharedResultList.get(position).getId());
                } else if (sharedResultList.get(position).getApiName().equals("Yummly")) {
                    yummlyNetworking.setActivity(activity);
                    System.out.println(sharedResultList.get(position).getId());
                    String yummlyIdUrl = sharedResultList.get(position).getId();
                    yummlyNetworking.doGetRecipe(context, yummlyIdUrl);
                }
            }
        });

        //Search button
        Button searchButton = (Button) mView.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Clear previous search results
                sharedResultList.clear();
                layout.setBackground(whiteImage);

                View view = activity.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                //Get the contents of the TextEdit when clicked
                String userSearch = userInput.getText().toString();

                //Input validation
                if (userSearch.equals("")) {
                    userInput.setText("Please enter an ingredient to search for.");
                } else if (userSearch.contains("Please") || userSearch.contains("Enter")) {
                    userInput.setText("Please enter an ingredient to search for.");
                } else {
                    String query = userSearch.trim(); //get rid of leading or trailing whitespace


                    //URL-encode the user input
                    String encodedQuery = "";
                    try {
                        encodedQuery = URLEncoder.encode(query, "UTF-8");

                    } catch (UnsupportedEncodingException uee) {
                        uee.printStackTrace();
                    }

                    //Setup Spoonacular request
                    String spoonacularRequestURL = "";
                    spoonacularNetworking.setActivity(activity);

                    //Make sure the preferences are fresh.
                    /*
                    * TODO: Currently disabling the Spoonacular preferences, as the returned JSON
                    * TODO: object has a different model than the searchByIngredients object,
                    * TODO: and will need to have a new parser written.
                    * */

                    /*
                    * Here is the Spoonacular preferences code to re-enable once the new parser is written.
                    * preferences = activity.getPreferences();
                    * int prefsTotal = 0;
                    for (int i = 0; i < preferences.size(); i++){
                        if (preferences.get(i) == true){
                            prefsTotal ++;
                        }
                    }
                    //Apply preferences to the search string
                    String intolerances = "&intolerances=";
                    String encodedIntolerances = "";
                    if(prefsTotal > 0){

                        if(preferences.get(0) == true)
                            intolerances += "dairy,";
                        if (preferences.get(1) == true)
                            intolerances += "egg,";
                        if (preferences.get(2) == true)
                            intolerances += "gluten,";
                        if (preferences.get(3) == true)
                            intolerances += "peanut,";
                        if (preferences.get(4) == true)
                            intolerances += "seafood,";
                        if (preferences.get(5) == true)
                            intolerances += "sesame,";
                        if (preferences.get(6) == true)
                            intolerances += "soy,";
                        if (preferences.get(7) == true)
                            intolerances += "tree nut,";
                        if (preferences.get(8) == true)
                            intolerances += "wheat";
                        //search for recipes excluding ingredients

                        try{
                            encodedIntolerances = URLEncoder.encode(intolerances, "UTF-8");
                        }catch(UnsupportedEncodingException uee){uee.printStackTrace();}

                        spoonacularRequestURL = spoonacularBaseURL + "search?" + encodedIntolerances +
                                "&query=" + encodedQuery;
                    }

                    else{
                        spoonacularRequestURL = spoonacularGetUrl + encodedQuery + "&number=100";
                    }
                    * */


                    spoonacularRequestURL = spoonacularGetUrl + encodedQuery + "&number=100";

                    try {
                        spoonacularNetworking.doGetRequest(context, adapter, spoonacularRequestURL);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }

                    //Setup Yummly request
                    yummlyNetworking.setActivity(activity);
                    String yummlyRequestURL = yummlyBaseURL + encodedQuery;
                    try {
                        yummlyNetworking.doGetRequest(context, adapter, yummlyRequestURL);
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }


                }

            }
        });


        /*
        * End search code
        * */

        // Inflate the layout for this fragment
        return mView;
    }

    public void setActivity(MainActivity act){
        activity = act;
        return;
    }

    public void setPreferences(ArrayList<Boolean> prefs){
        preferences = prefs;
        return;
    }


}
