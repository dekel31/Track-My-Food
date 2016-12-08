package com.ibraheem.nashat.trackmyfood4.model;

import java.io.Serializable;

/**
 * Created by dekela on 1/7/2016.
 */
public class Food implements Serializable, Comparable {
    private String name;
    private int carbs = 0;
    private int protein = 0;
    private int fat = 0;
    private int mass = 0;
    private int defaultMass = 1;

    public Food(String name, int carbs, int protein, int fat) {
        this.name = name;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
    }

    public Food(String name, int carbs, int protein, int fat, int mass) {
        this(name, carbs, protein, fat);
        this.mass = mass;
    }

    @Override
    public String toString() {
        return (mass == 1 || mass == 0) ? name : mass + " " + name
//                + ", carbs=" + carbs + ", protein=" + protein
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        return !(name != null ? !name.equals(food.name) : food.name != null);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + carbs;
        result = 31 * result + protein;
        result = 31 * result + fat;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getDefaultMass() {
        return defaultMass;
    }

    public void setDefaultMass(int defaultMass) {
        this.defaultMass = defaultMass;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    @Override
    public int compareTo(Object another) {
        if(another == null || !(another instanceof Food)){
            return 0;
        }
        return this.getName().compareTo(((Food) another).getName());
    }
}
