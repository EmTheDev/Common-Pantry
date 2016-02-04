package com.example.frankkoutoulas.splashscreen;

import java.util.List;

/**
 * Created by miles on 11/12/15.
 */
public class YummlyRecipe {

    /**
     * html : <a href='http://www.yummly.com/recipe/Hot-Turkey-Salad-Sandwiches-Allrecipes'>Hot Turkey Salad Sandwiches recipe</a> information powered by <img src='http://static.yummly.com/api-logo.png'/>
     * url : http://www.yummly.com/recipe/Hot-Turkey-Salad-Sandwiches-Allrecipes
     * text : Hot Turkey Salad Sandwiches recipes: information powered by Yummly
     * logo : http://static.yummly.com/api-logo.png
     */

    private AttributionEntity attribution;
    /**
     * Salty : 0.004261637106537819
     * Meaty : 0.0019220244139432907
     * Piquant : 0
     * Bitter : 0.006931612268090248
     * Sour : 0.009972159750759602
     * Sweet : 0.0032512755133211613
     */

    private FlavorsEntity flavors;
    /**
     * attribution : {"html":"<a href='http://www.yummly.com/recipe/Hot-Turkey-Salad-Sandwiches-Allrecipes'>Hot Turkey Salad Sandwiches recipe<\/a> information powered by <img src='http://static.yummly.com/api-logo.png'/>","url":"http://www.yummly.com/recipe/Hot-Turkey-Salad-Sandwiches-Allrecipes","text":"Hot Turkey Salad Sandwiches recipes: information powered by Yummly","logo":"http://static.yummly.com/api-logo.png"}
     * ingredientLines : ["2 cups diced cooked turkey","2 celery ribs, diced","1 small onion, diced","2 hard-cooked eggs, chopped","3/4 cup mayonnaise","1/2 teaspoon salt","1/4 teaspoon pepper","6 hamburger buns, split"]
     * flavors : {"Salty":0.004261637106537819,"Meaty":0.0019220244139432907,"Piquant":0,"Bitter":0.006931612268090248,"Sour":0.009972159750759602,"Sweet":0.0032512755133211613}
     * nutritionEstimates : [{"attribute":"ENERC_KCAL","description":"Energy","value":317.4,"unit":{"name":"calorie","abbreviation":"kcal","plural":"calories","pluralAbbreviation":"kcal"}},{"attribute":"FAT","description":"Total lipid (fat)","value":13.97,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}},{"attribute":"FASAT","description":"Fatty acids, total saturated","value":2.7,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}},{"attribute":"CHOLE","description":"Cholesterol","value":0.11,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}},{"attribute":"NA","description":"Sodium, Na","value":0.66,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}},{"attribute":"K","description":"Potassium, K","value":0.2,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}},{"attribute":"CHOCDF","description":"Carbohydrate, by difference","value":29.92,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}},{"attribute":"FIBTG","description":"Fiber, total dietary","value":1.3,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}},{"attribute":"SUGAR","description":"Sugars, total","value":5.25,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}},{"attribute":"PROCNT","description":"Protein","value":17.6,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}},{"attribute":"VITA_IU","description":"Vitamin A, IU","value":159.13,"unit":{"name":"IU","abbreviation":"IU","plural":"IU","pluralAbbreviation":"IU"}},{"attribute":"VITC","description":"Vitamin C, total ascorbic acid","value":0,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}},{"attribute":"CA","description":"Calcium, Ca","value":0.08,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}},{"attribute":"FE","description":"Iron, Fe","value":0,"unit":{"name":"gram","abbreviation":"g","plural":"grams","pluralAbbreviation":"grams"}}]
     * images : [{"hostedLargeUrl":"http://i2.yummly.com/Hot-Turkey-Salad-Sandwiches-Allrecipes.l.png","hostedSmallUrl":"http://i2.yummly.com/Hot-Turkey-Salad-Sandwiches-Allrecipes.s.png"}]
     * name : Hot Turkey Salad Sandwiches
     * yield : 6 servings
     * totalTime : 30 Min
     * attributes : {"holiday":["Christmas","Thanksgiving"],"cuisine":["Italian","Soul food","American"]}
     * totalTimeInSeconds : 1800
     * rating : 4.44
     * numberOfServings : 6
     * source : {"sourceRecipeUrl":"http://allrecipes.com/Recipe/hot-turkey-salad-sandwiches/detail.aspx","sourceSiteUrl":"http://www.allrecipes.com","sourceDisplayName":"AllRecipes"}
     * id : Hot-Turkey-Salad-Sandwiches-Allrecipes
     */

    private String name;
    private String yield;
    private String totalTime;
    private AttributesEntity attributes;
    private int totalTimeInSeconds;
    private double rating;
    private int numberOfServings;
    /**
     * sourceRecipeUrl : http://allrecipes.com/Recipe/hot-turkey-salad-sandwiches/detail.aspx
     * sourceSiteUrl : http://www.allrecipes.com
     * sourceDisplayName : AllRecipes
     */

    private SourceEntity source;
    private String id;
    private List<String> ingredientLines;
    /**
     * attribute : ENERC_KCAL
     * description : Energy
     * value : 317.4
     * unit : {"name":"calorie","abbreviation":"kcal","plural":"calories","pluralAbbreviation":"kcal"}
     */

    private List<NutritionEstimatesEntity> nutritionEstimates;
    /**
     * hostedLargeUrl : http://i2.yummly.com/Hot-Turkey-Salad-Sandwiches-Allrecipes.l.png
     * hostedSmallUrl : http://i2.yummly.com/Hot-Turkey-Salad-Sandwiches-Allrecipes.s.png
     */

    private List<ImagesEntity> images;

    public void setAttribution(AttributionEntity attribution) {
        this.attribution = attribution;
    }

    public void setFlavors(FlavorsEntity flavors) {
        this.flavors = flavors;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYield(String yield) {
        this.yield = yield;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public void setAttributes(AttributesEntity attributes) {
        this.attributes = attributes;
    }

    public void setTotalTimeInSeconds(int totalTimeInSeconds) {
        this.totalTimeInSeconds = totalTimeInSeconds;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setNumberOfServings(int numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public void setSource(SourceEntity source) {
        this.source = source;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIngredientLines(List<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public void setNutritionEstimates(List<NutritionEstimatesEntity> nutritionEstimates) {
        this.nutritionEstimates = nutritionEstimates;
    }

    public void setImages(List<ImagesEntity> images) {
        this.images = images;
    }

    public AttributionEntity getAttribution() {
        return attribution;
    }

    public FlavorsEntity getFlavors() {
        return flavors;
    }

    public String getName() {
        return name;
    }

    public String getYield() {
        return yield;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public AttributesEntity getAttributes() {
        return attributes;
    }

    public int getTotalTimeInSeconds() {
        return totalTimeInSeconds;
    }

    public double getRating() {
        return rating;
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public SourceEntity getSource() {
        return source;
    }

    public String getId() {
        return id;
    }

    public List<String> getIngredientLines() {
        return ingredientLines;
    }

    public List<NutritionEstimatesEntity> getNutritionEstimates() {
        return nutritionEstimates;
    }

    public List<ImagesEntity> getImages() {
        return images;
    }

    public static class AttributionEntity {
        private String html;
        private String url;
        private String text;
        private String logo;

        public void setHtml(String html) {
            this.html = html;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getHtml() {
            return html;
        }

        public String getUrl() {
            return url;
        }

        public String getText() {
            return text;
        }

        public String getLogo() {
            return logo;
        }
    }

    public static class FlavorsEntity {
        private double Salty;
        private double Meaty;
        private int Piquant;
        private double Bitter;
        private double Sour;
        private double Sweet;

        public void setSalty(double Salty) {
            this.Salty = Salty;
        }

        public void setMeaty(double Meaty) {
            this.Meaty = Meaty;
        }

        public void setPiquant(int Piquant) {
            this.Piquant = Piquant;
        }

        public void setBitter(double Bitter) {
            this.Bitter = Bitter;
        }

        public void setSour(double Sour) {
            this.Sour = Sour;
        }

        public void setSweet(double Sweet) {
            this.Sweet = Sweet;
        }

        public double getSalty() {
            return Salty;
        }

        public double getMeaty() {
            return Meaty;
        }

        public int getPiquant() {
            return Piquant;
        }

        public double getBitter() {
            return Bitter;
        }

        public double getSour() {
            return Sour;
        }

        public double getSweet() {
            return Sweet;
        }
    }

    public static class AttributesEntity {
        private List<String> holiday;
        private List<String> cuisine;

        public void setHoliday(List<String> holiday) {
            this.holiday = holiday;
        }

        public void setCuisine(List<String> cuisine) {
            this.cuisine = cuisine;
        }

        public List<String> getHoliday() {
            return holiday;
        }

        public List<String> getCuisine() {
            return cuisine;
        }
    }

    public static class SourceEntity {
        private String sourceRecipeUrl;
        private String sourceSiteUrl;
        private String sourceDisplayName;

        public void setSourceRecipeUrl(String sourceRecipeUrl) {
            this.sourceRecipeUrl = sourceRecipeUrl;
        }

        public void setSourceSiteUrl(String sourceSiteUrl) {
            this.sourceSiteUrl = sourceSiteUrl;
        }

        public void setSourceDisplayName(String sourceDisplayName) {
            this.sourceDisplayName = sourceDisplayName;
        }

        public String getSourceRecipeUrl() {
            return sourceRecipeUrl;
        }

        public String getSourceSiteUrl() {
            return sourceSiteUrl;
        }

        public String getSourceDisplayName() {
            return sourceDisplayName;
        }
    }

    public static class NutritionEstimatesEntity {
        private String attribute;
        private String description;
        private double value;
        /**
         * name : calorie
         * abbreviation : kcal
         * plural : calories
         * pluralAbbreviation : kcal
         */

        private UnitEntity unit;

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setValue(double value) {
            this.value = value;
        }

        public void setUnit(UnitEntity unit) {
            this.unit = unit;
        }

        public String getAttribute() {
            return attribute;
        }

        public String getDescription() {
            return description;
        }

        public double getValue() {
            return value;
        }

        public UnitEntity getUnit() {
            return unit;
        }

        public static class UnitEntity {
            private String name;
            private String abbreviation;
            private String plural;
            private String pluralAbbreviation;

            public void setName(String name) {
                this.name = name;
            }

            public void setAbbreviation(String abbreviation) {
                this.abbreviation = abbreviation;
            }

            public void setPlural(String plural) {
                this.plural = plural;
            }

            public void setPluralAbbreviation(String pluralAbbreviation) {
                this.pluralAbbreviation = pluralAbbreviation;
            }

            public String getName() {
                return name;
            }

            public String getAbbreviation() {
                return abbreviation;
            }

            public String getPlural() {
                return plural;
            }

            public String getPluralAbbreviation() {
                return pluralAbbreviation;
            }
        }
    }

    public static class ImagesEntity {
        private String hostedLargeUrl;
        private String hostedSmallUrl;

        public void setHostedLargeUrl(String hostedLargeUrl) {
            this.hostedLargeUrl = hostedLargeUrl;
        }

        public void setHostedSmallUrl(String hostedSmallUrl) {
            this.hostedSmallUrl = hostedSmallUrl;
        }

        public String getHostedLargeUrl() {
            return hostedLargeUrl;
        }

        public String getHostedSmallUrl() {
            return hostedSmallUrl;
        }

    }
}
