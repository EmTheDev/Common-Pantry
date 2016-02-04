package com.example.frankkoutoulas.splashscreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by miles on 12/7/15.
 *
 *
 */

/**
 * We used WebView for our recipeObject in order to be able to click a saved recipe
 * and be directed back to the recipe.
 * The user should not have to search for a saved recipe again as the purpose of
 * saving the recipe is to be able to easily view it again at anytime.
 */

public class WebViewFragment extends Fragment {

    private WebView webView;
    private RecipeObject recipeObject;

    private View mView;
    private MainActivity activity;


    Button mAddFB;

    /**
     * Clickable buttons which either take us to facebook login or add a recipe url to the cookbook
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return mView buttons used to add the recipe to the
     * cookbook and share the recipe on facebook
     */


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        mView = inflater.inflate(R.layout.fragment_webview, container, false);


        /**
         * Button used to launch facebook and prompt user login
         * Facebook is launched in order to be able to share the recipe onto facebook
         */
        mAddFB = (Button) mView.findViewById(R.id.add);

        mAddFB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                activity.launchFacebook(recipeObject.getUrl());

            }
        });

        /**
         * addToCookbook button used to add a recipe url to the cookbook
         * the recipe is displayed by name but the recipe is clickable
         * so once the recipe is added to the cookbook the user can click
         * it and will be taken back to the recipe
         */
        Button addToCookBookButton = (Button) mView.findViewById(R.id.cookbook_button);

        addToCookBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.addToCookbook(recipeObject);

                Toast.makeText(getActivity().getApplicationContext(), "Saved to cookbook.",
                        Toast.LENGTH_SHORT).show();
            }
        });


        /**
         * load the recipe url in order to view recipe without leaving app
         */

        webView = (WebView) mView.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        //System.out.println(url);
        webView.loadUrl(recipeObject.getUrl());


        return mView;
    }

    public void setActivity(MainActivity act){
        activity = act;
        return;
    }

    public void setRecipe(RecipeObject recObj){
        recipeObject = recObj;
        return;
    }



}
