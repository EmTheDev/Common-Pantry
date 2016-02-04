package com.example.frankkoutoulas.splashscreen;

import android.graphics.drawable.Drawable;

/**
 * Created by miles on 11/11/15.
 */
public class RecipeAPIResult {
    String title;
    String id;
    String imageURL;
    String apiName;
    Drawable recipePhoto;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String toString(){
        return title + '\n' + apiName;
    }

    public Drawable getRecipePhoto() {
        return recipePhoto;
    }

    public void setRecipePhoto(Drawable recipePhoto) {
        this.recipePhoto = recipePhoto;
    }
}
