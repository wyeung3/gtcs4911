package com.projectbasil.projectbasil;
import java.sql.Time;

/**
 * Created by John Brand on 9/21/2015.
 */
public class Plan {
    /**
     * recipe for this plan
     */
    public Recipe recipe;
    /**
     * name of the dish/meal
     */
    public String name;
    /**
     * Time, day and hour (meal time)
     */
    public Time time;
    //TODO: set up triplet for easy formatting
    //private Triplet<Recipe, String, Time> plan;

    /**
     * Constructor
     * @param recipe
     * @param name
     * @param time
     */
    public Plan(Recipe recipe, String name, Time time){
        this.recipe = recipe;
        this.name = name;
        this.time = time;
    }

    //TODO: make single getPlan() to return triplet

    /**
     * returns recipe associated with this Plan
     * @return
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * returns name of the meal
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * returns the time for Plan
     * @return
     */
    public Time getTime(){
        return time;
    }

    /**
     * sets the recipe for Plan
     * @param recipe
     */
    public void setRecipe(Recipe recipe) {this.recipe = recipe;}

    /**
     * sets the name for Plan
     * @param name
     */
    public void setName(String name) {this.name = name;}

    /**
     * sets the time for Plan
     * @param time
     */
    public void setTime(Time time) {this.time = time;}
}
