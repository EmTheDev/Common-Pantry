package com.example.frankkoutoulas.splashscreen;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.util.Log;

import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sonalig on 10/28/2015.
 */

/**
 * SQLiteHandler
 * creates database and database fields
 * creates the table
 * creates the columns that will populate the table
 * allows recipes to be accessed and assigned
 * allows recipes to be displayed in table
 */
public class SQLiteHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "cookbook.db";
    private static final String TABLE_NAME = "recipes";
    private static final String COL_ID = "id";
    private static final String COL_RECID = "recid";
    private static final String COL_NAME = "recipename";
    private static final String COL_URL = "url";
    private static final String COL_API = "api";

    private static final String TAG = "COOKBOOK";


    /**
     * @param pContext
     */
    public SQLiteHandler(Context pContext) {
        this(pContext, null, null, 1);
    }

    /**
     * Creates SQLite constructor
     * allows database to have name and version, which were previously named and assigned
     * @param context
     * @param name name of database(cookbook.db)
     * @param factory
     * @param version version of database(1)
     */
    public SQLiteHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);

        Log.i(TAG, "SQLite constructor created.");
    }

    /**
     * Create database table named Cookbook
     * Identify columns
     * @param db database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {


        String query = "CREATE TABLE " + TABLE_NAME
                + "("
                + COL_ID + " TEXT  , "
                + COL_RECID + " TEXT ,"
                + COL_NAME + " TEXT, "
                + COL_URL + " TEXT, "
                + COL_API + " TEXT "
                + ");";

        db.execSQL(query);

        Log.i(TAG, "Cookbook has been create, onCreate()");


    }

    /**
     * Upgrade table if any changes are made
     * Changes would include a new recipe being added
     * Replace old table with new one if an old one exists
     * @param db database
     * @param oldVersion old table of database being replaced
     * @param newVersion new table of database doing the replacing
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

        Log.i(TAG, "Database has been upgraded, onUpgrade()");

    }

    /**
     * insertIntoDatabase insert recipeObject recipeOrb into database
     * allows recipes to be inserted into the database
     * insert values into the columns
     * database is writable
     * insert recipes into database so they can be accessed later
     * close database
     * @param recipeOrb used to insert text(strings) into the columns of the database
     */
    public void insertIntoDatabase(RecipeObject recipeOrb) {
        ContentValues insertedvalues = new ContentValues();
        Log.i(TAG, "Generating WriteTable");
        insertedvalues.put(COL_ID, recipeOrb.getId());
        insertedvalues.put(COL_RECID, recipeOrb.getRecid());
        insertedvalues.put(COL_NAME, recipeOrb.getName());
        insertedvalues.put(COL_URL, recipeOrb.getUrl());
        insertedvalues.put(COL_API, recipeOrb.getApi());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, insertedvalues);
        db.close();

        Log.i(TAG, " inserted recipe " + recipeOrb.getName() + "" + "has been added to the database, insertIntoDatabase()");

    }

    /**
     * ArrayList has the list of recipes saved
     * Displays all recipes in the cookbook
     * We are able to view everything we inserted(saved) to the cookbook
     * @return return the recipes that were saved to the cookbook database
     */
    public List<RecipeObject> getListofRecipes() {
        List<RecipeObject> recipes = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            RecipeObject recipeOrb = new RecipeObject();
            recipeOrb.setId(String.valueOf(cursor.getColumnIndex(COL_ID)));
            recipeOrb.setRecid(String.valueOf(cursor.getColumnIndex(COL_RECID)));
            recipeOrb.setName(cursor.getString(cursor.getColumnIndex(COL_NAME)));
            recipeOrb.setUrl(cursor.getString(cursor.getColumnIndex(COL_URL)));
            recipeOrb.setApi(cursor.getString(cursor.getColumnIndex(COL_API)));

            Log.i(TAG, "extract recipe");


            recipes.add(recipeOrb);
        }


        return recipes;


    }

    /**
     * Readable database
     * Display rows of recipes
     * @return rowCount list of recipes
     */
    public int getRowCount(){
        int rowCount = 0;

        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        rowCount = cursor.getCount();
        cursor.close();

        return rowCount;
    }




}

