package com.example.frankkoutoulas.splashscreen;

/**
 * Created by sonalig on 12/03/2015.
 */

/**
 * Holds data members and constructors needed for Database(Cookbook)
 * Identifies members as strings
 * get to provide access to value a variable holds
 * set to assign value to variables of objects
 */
public class RecipeObject {

    //Private data members

    private String id;
    private String recid;
    private String name;
    private String url;
    private String api;

    //Default constructors
    /**
     * Default constructor
     */
    public RecipeObject(){
    }


    //Constructor
    /**
     * Constructor
     * Contains strings that exist in and are displayed from RecipeObject
     * @param id string for auto-incremented id of recipe
     * @param recid string for the recipe id
     * @param name string for recipe name
     * @param url string for recipe url
     * @param api string for recipe api
     */
    public RecipeObject(String id, String recid, String name, String url, String api){

        this.id = id;
        this.recid = recid;
        this.name = name;
        this.url = url;
        this.api = api;
    }

    /**
     * getId provides access to id
     * @return id
     */
    public String getId() { return id; }

    /**
     * setId assigns value to id
     * @param id autoincremented id
     */
    public void setId(String id) { this.id = id; }

    /**
     * getRecid provides access to recid
     * @return recid
     */
    public String getRecid() { return recid; }

    /**
     * setRecid assigns value to recid
     * @param recid
     */
    public void setRecid(String recid) { this.recid = recid; }

    /**
     * getName provides access to name
     * @return name
     */
    public String getName() { return name; }

    /**
     * setName assigns value to name
     * @param name name of recipe
     */
    public void setName(String name) { this.name = name; }

    /**
     * getUrl provides access to url
     * @return url
     */
    public String getUrl() { return url; }

    /**
     * setUrl assigns value to url
     * @param url url of recipe
     */
    public void setUrl(String url) { this.url = url; }

    /**
     * getApi provides access to api
     * @return api
     */
    public String getApi() { return api; }

    /**
     * setApi assigns value to api
     * @param api
     */
    public void setApi(String api) { this.api = api; }

}
