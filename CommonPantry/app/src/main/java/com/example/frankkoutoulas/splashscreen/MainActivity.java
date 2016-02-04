package com.example.frankkoutoulas.splashscreen;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    SearchFragment sFragment;

    /**
     * Launches eula
     * Once we go through eula launches search
     * calls drawer
     * drawer allows navigation to other fragments
     * @param savedInstanceState allows changes to be made while saving previous data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Eula.show(this);

        PreferencesFragment preferencesFragment = new PreferencesFragment();


        SearchFragment fragment = new SearchFragment();


        sFragment = fragment;
        fragment.setActivity(this);

        ArrayList<Boolean> preferences = preferencesFragment.getPrefs();
        fragment.setPreferences(preferences);

        FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack( "tag" );
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    /**
     * allows drawer to be opened and closed
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        System.out.println("Menu item selected.");
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * holds the items in the drawer
     * each fragment has its own layout
     * @param item items in drawer
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        System.out.println("Menu click.");
        int id = item.getItemId();

        if (id == R.id.search_fragment) {
            SearchFragment searchFragment = new SearchFragment();
            searchFragment.setActivity(this);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, searchFragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();
            // Handle the camera action
        } else if (id == R.id.cookbook_fragment) {
            CookbookFragment cookbookFragment = new CookbookFragment();
            cookbookFragment.setActivity(this);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, cookbookFragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();

        } else if (id == R.id.preferences_fragment) {
            PreferencesFragment preferencesFragment = new PreferencesFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, preferencesFragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();

        } else if (id == R.id.maps_fragment) {
            GoogleMapsFragment googleMapsFragment = new GoogleMapsFragment ();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, googleMapsFragment);
            fragmentTransaction.addToBackStack("tag");
            fragmentTransaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     *
     * @param recipeObject recipeObject clickable to view recipes
     */
    public void setWebView(RecipeObject recipeObject){
        /*
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }*/

        WebViewFragment webViewFragment = new WebViewFragment();
        webViewFragment.setRecipe(recipeObject);

        webViewFragment.setActivity(this);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, webViewFragment);
        fragmentTransaction.addToBackStack("tag");
        fragmentTransaction.commit();

        return;
    }

    /**
     * add recipes to the coobook
     * @param recipeObject recipeObject added to cookbook
     */
    public void addToCookbook(RecipeObject recipeObject){
        CookbookFragment cbf = new CookbookFragment();
        cbf.setActivity(this);
        cbf.addToCookbook(recipeObject);
        return;
    }

    /**
     * allows the launch of facebook
     * @param url url for facebook
     */
    public void launchFacebook(String url)
    {
        Facebook post = new Facebook();
        post.setUrl(url);
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, post);
        fragmentTransaction.addToBackStack("tag");
        fragmentTransaction.commit();
        return;
    }

    public MainActivity getMainActivity(){
        return this;
    }

    public ArrayList<Boolean> getPreferences(){
        PreferencesFragment preferencesFragment = new PreferencesFragment();
        return (preferencesFragment.getPrefs());
    }


}
