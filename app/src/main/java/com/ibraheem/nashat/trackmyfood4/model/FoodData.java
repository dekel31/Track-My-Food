package com.ibraheem.nashat.trackmyfood4.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by nashati on 12/29/2015.
 */
public class FoodData implements Serializable {

    private List<Food> foodList;
    private List<Food> todayFood;
    private int water;

    public FoodData() {
        foodList = new ArrayList<>();
        todayFood = new ArrayList<>();
    }

    public FoodData(List<Food> foodsType) {
        this.foodList = foodsType;
        todayFood = new ArrayList<>();
    }

    public void addFoodType(Food foodType) {
        foodList.add(foodType);
    }

    public void addFoodForToday(Food food) {
        todayFood.add(food);
    }

    public List<Food> getFoodTypes() {
        Collections.sort(foodList);
        return foodList;
    }

    public List<Food> getTodayFood() {
        return todayFood;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }
}
