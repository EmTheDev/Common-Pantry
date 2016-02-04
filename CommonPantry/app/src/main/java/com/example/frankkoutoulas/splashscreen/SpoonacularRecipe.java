package com.example.frankkoutoulas.splashscreen;

import java.util.List;

/**
 * Created by miles on 11/9/15.
 */
public class SpoonacularRecipe {

    /**
     * vegetarian : false
     * vegan : false
     * glutenFree : true
     * dairyFree : true
     * veryHealthy : false
     * cheap : false
     * veryPopular : false
     * sustainable : false
     * servings : 10
     * readyInMinutes : 45
     * sourceUrl : http://www.epicurious.com/recipes/food/views/Char-Grilled-Beef-Tenderloin-with-Three-Herb-Chimichurri-235342
     * spoonacularSourceUrl : https://spoonacular.com/Char-Grilled-Beef-Tenderloin-with-Three-Herb-Chimichurri-156992
     * aggregateLikes : 0
     * sourceName : Epicurious
     * extendedIngredients : [{"aisle":"Ethnic Foods","name":"ancho chile powder","amount":1.5,"unit":"teaspoons","unitShort":"t","unitLong":"teaspoons","originalString":"1 1/2 teaspoons chipotle chile powder or ancho chile powder","metaInformation":[],"longNameAdditions":[]},{"aisle":"Meat","name":"beef tenderloin","amount":3.5,"unit":"pound","unitShort":"lb","unitLong":"pounds","originalString":"1 3 1/2-pound beef tenderloin","metaInformation":[],"longNameAdditions":[]},{"aisle":"Spices and Seasonings","name":"black pepper","amount":0.5,"unit":"teaspoon","unitShort":"t","unitLong":"teaspoons","originalString":"1/2 teaspoon freshly ground black pepper","metaInformation":[],"longNameAdditions":[]},{"aisle":"Spices and Seasonings","name":"coarse kosher salt","amount":1,"unit":"tablespoon","unitShort":"T","unitLong":"tablespoon","originalString":"1 tablespoon coarse kosher salt","metaInformation":[],"longNameAdditions":[]},{"aisle":"Baking","name":"dark brown sugar","amount":2,"unit":"tablespoons","unitShort":"T","unitLong":"tablespoons","originalString":"2 tablespoons dark brown sugar","metaInformation":[],"longNameAdditions":[]},{"aisle":"Produce","name":"fresh cilantro","amount":2,"unit":"cups","unitShort":"c","unitLong":"cups","originalString":"2 cups (packed) stemmed fresh cilantro","metaInformation":[],"longNameAdditions":[]},{"aisle":"Produce","name":"fresh mint","amount":1,"unit":"cup","unitShort":"c","unitLong":"cup","originalString":"1 cup (packed) stemmed fresh mint","metaInformation":[],"longNameAdditions":[]},{"aisle":"Spices and Seasonings","name":"fresh parsley","amount":3,"unit":"cups","unitShort":"c","unitLong":"cups","originalString":"3 cups (packed) stemmed fresh parsley","metaInformation":[],"longNameAdditions":[]},{"aisle":"Produce","name":"garlic cloves","amount":3,"unit":"","unitShort":"","unitLong":"","originalString":"3 garlic cloves, peeled","metaInformation":[],"longNameAdditions":[]},{"aisle":"Spices and Seasonings","name":"ground pepper","amount":1,"unit":"teaspoon","unitShort":"t","unitLong":"teaspoon","originalString":"1 teaspoon ground black pepper","metaInformation":[],"longNameAdditions":[]},{"aisle":"Produce","name":"lemon juice","amount":3,"unit":"tablespoons","unitShort":"T","unitLong":"tablespoons","originalString":"3 tablespoons fresh lemon juice","metaInformation":[],"longNameAdditions":[]},{"aisle":"Oil, Vinegar, Salad Dressing","name":"olive oil","amount":2,"unit":"tablespoons","unitShort":"T","unitLong":"tablespoons","originalString":"2 tablespoons olive oil","metaInformation":[],"longNameAdditions":[]},{"aisle":"Spices and Seasonings","name":"pepper","amount":0.5,"unit":"teaspoon","unitShort":"t","unitLong":"teaspoons","originalString":"1/2 teaspoon dried crushed red pepper","metaInformation":[],"longNameAdditions":[]},{"aisle":"Oil, Vinegar, Salad Dressing","name":"red wine vinegar","amount":3,"unit":"tablespoons","unitShort":"T","unitLong":"tablespoons","originalString":"3 tablespoons Sherry wine vinegar or red wine vinegar","metaInformation":[],"longNameAdditions":[]},{"aisle":"Spices and Seasonings","name":"sea salt","amount":1,"unit":"teaspoon","unitShort":"t","unitLong":"teaspoon","originalString":"1 teaspoon fine sea salt","metaInformation":[],"longNameAdditions":[]},{"aisle":"Produce","name":"shallots","amount":2,"unit":"","unitShort":"","unitLong":"","originalString":"2 medium shallots, peeled, quartered","metaInformation":[],"longNameAdditions":[]},{"aisle":"Spices and Seasonings","name":"sweet paprika","amount":1,"unit":"tablespoon","unitShort":"T","unitLong":"tablespoon","originalString":"1 tablespoon sweet smoked paprika*","metaInformation":[],"longNameAdditions":[]}]
     * id : 156992
     * title : Char-Grilled Beef Tenderloin with Three-Herb Chimichurri
     * image : char-grilled-beef-tenderloin-with-three-herb-chimichurri-156992.jpg
     * imageUrls : ["char-grilled-beef-tenderloin-with-three-herb-chimichurri-156992.jpg"]
     */

    private boolean vegetarian;
    private boolean vegan;
    private boolean glutenFree;
    private boolean dairyFree;
    private boolean veryHealthy;
    private boolean cheap;
    private boolean veryPopular;
    private boolean sustainable;
    private int servings;
    private int readyInMinutes;
    private String sourceUrl;
    private String spoonacularSourceUrl;
    private int aggregateLikes;
    private String sourceName;
    private int id;
    private String title;
    private String image;
    /**
     * aisle : Ethnic Foods
     * name : ancho chile powder
     * amount : 1.5
     * unit : teaspoons
     * unitShort : t
     * unitLong : teaspoons
     * originalString : 1 1/2 teaspoons chipotle chile powder or ancho chile powder
     * metaInformation : []
     * longNameAdditions : []
     */

    private List<ExtendedIngredientsEntity> extendedIngredients;
    private List<String> imageUrls;

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public void setGlutenFree(boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public void setDairyFree(boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    public void setVeryHealthy(boolean veryHealthy) {
        this.veryHealthy = veryHealthy;
    }

    public void setCheap(boolean cheap) {
        this.cheap = cheap;
    }

    public void setVeryPopular(boolean veryPopular) {
        this.veryPopular = veryPopular;
    }

    public void setSustainable(boolean sustainable) {
        this.sustainable = sustainable;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public void setReadyInMinutes(int readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setSpoonacularSourceUrl(String spoonacularSourceUrl) {
        this.spoonacularSourceUrl = spoonacularSourceUrl;
    }

    public void setAggregateLikes(int aggregateLikes) {
        this.aggregateLikes = aggregateLikes;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setExtendedIngredients(List<ExtendedIngredientsEntity> extendedIngredients) {
        this.extendedIngredients = extendedIngredients;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public boolean isGlutenFree() {
        return glutenFree;
    }

    public boolean isDairyFree() {
        return dairyFree;
    }

    public boolean isVeryHealthy() {
        return veryHealthy;
    }

    public boolean isCheap() {
        return cheap;
    }

    public boolean isVeryPopular() {
        return veryPopular;
    }

    public boolean isSustainable() {
        return sustainable;
    }

    public int getServings() {
        return servings;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getSpoonacularSourceUrl() {
        return spoonacularSourceUrl;
    }

    public int getAggregateLikes() {
        return aggregateLikes;
    }

    public String getSourceName() {
        return sourceName;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public List<ExtendedIngredientsEntity> getExtendedIngredients() {
        return extendedIngredients;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public static class ExtendedIngredientsEntity {
        private String aisle;
        private String name;
        private double amount;
        private String unit;
        private String unitShort;
        private String unitLong;
        private String originalString;
        private List<?> metaInformation;
        private List<?> longNameAdditions;

        public void setAisle(String aisle) {
            this.aisle = aisle;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public void setUnitShort(String unitShort) {
            this.unitShort = unitShort;
        }

        public void setUnitLong(String unitLong) {
            this.unitLong = unitLong;
        }

        public void setOriginalString(String originalString) {
            this.originalString = originalString;
        }

        public void setMetaInformation(List<?> metaInformation) {
            this.metaInformation = metaInformation;
        }

        public void setLongNameAdditions(List<?> longNameAdditions) {
            this.longNameAdditions = longNameAdditions;
        }

        public String getAisle() {
            return aisle;
        }

        public String getName() {
            return name;
        }

        public double getAmount() {
            return amount;
        }

        public String getUnit() {
            return unit;
        }

        public String getUnitShort() {
            return unitShort;
        }

        public String getUnitLong() {
            return unitLong;
        }

        public String getOriginalString() {
            return originalString;
        }

        public List<?> getMetaInformation() {
            return metaInformation;
        }

        public List<?> getLongNameAdditions() {
            return longNameAdditions;
        }
    }
}
