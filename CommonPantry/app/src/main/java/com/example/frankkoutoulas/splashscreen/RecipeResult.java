package com.example.frankkoutoulas.splashscreen;

import android.graphics.drawable.Drawable;

/**
 * Created by miles on 11/2/15.
 */
public class RecipeResult {
    private int id;
    private String title;
    private String image;
    private int usedIngredientCount;
    private int missedIngredientCount;
    private int likes;
    //private Drawable photo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUsedIngredientCount() {
        return usedIngredientCount;
    }

    public void setUsedIngredientCount(int usedIngredientCount) {
        this.usedIngredientCount = usedIngredientCount;
    }

    public int getMissedIngredientCount() {
        return missedIngredientCount;
    }

    public void setMissedIngredientCount(int missedIngredientCount) {
        this.missedIngredientCount = missedIngredientCount;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    /*public Drawable getPhoto() {
        return photo;
    }

    public void setPhoto(Drawable photo) {
        this.photo = photo;
    }*/

    public String toString(){
        String formattedResult = "";
        formattedResult += "Recipe ID: " + this.getId() + '\n' ;
        formattedResult += "Title: " + this.getTitle() + '\n' ;
        formattedResult += "Image URL: " + this.getImage() + '\n' ;
        formattedResult += "Used ingredient count: " + this.getUsedIngredientCount() + '\n' ;
        formattedResult += "Missed ingredient count: " + this.getMissedIngredientCount() + '\n' ;
        formattedResult += "Likes: " + this.getLikes() + '\n' ;
        formattedResult += '\n' ;
        return formattedResult;
    }
}
