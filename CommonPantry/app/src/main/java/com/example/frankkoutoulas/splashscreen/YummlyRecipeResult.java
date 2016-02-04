package com.example.frankkoutoulas.splashscreen;

/**
 * Created by user on 11/10/15.
 */
public class YummlyRecipeResult {

    String imageUrlsBySize;
    String sourceDisplayName;
    String[] ingredients;
    String id;
    String smallImageUrls;
    String recipeName;
    String totalTimeInSeconds;
    String attributes;
    String temp;

    public String getImageUrlsBySize() {
        return imageUrlsBySize;
    }

    public void setImageUrlsBySize(String imageUrlsBySize) {
        temp = imageUrlsBySize.replaceAll("\"", "");
        temp = temp.replaceAll("\\{90:", "");
        temp = temp.replaceAll("\\}", "");
        this.imageUrlsBySize = temp.replaceAll("\\\\","");

        //imageUrlsBySize = imageUrlsBySize.replaceAll("\"90\"","");
    }

    public String getSourceDisplayName() {
        return sourceDisplayName;
    }

    public void setSourceDisplayName(String sourceDisplayName) {
        this.sourceDisplayName = sourceDisplayName;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = "id";
        this.id = "http://api.yummly.com/v1/api/recipe/"+id+"?_app_id=11a7a04b&_app_key=9c7c996ed0ad0904bff15e755a2ccccc";
    }

    public String getSmallImageUrls() {
        return smallImageUrls;
    }

    public void setSmallImageUrls(String smallImageUrls) {
        this.smallImageUrls = smallImageUrls;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }

    public void setTotalTimeInSeconds(String totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }


}
