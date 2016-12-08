package com.ibraheem.nashat.trackmyfood4.application;

import android.content.Context;

import com.ibraheem.nashat.trackmyfood4.model.FoodData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Created by nashati on 8/10/2015.
 */
public class Storage {

    private static final String FILENAME = "food";
    private final Context con;

    public Storage(Context con) {
        this.con = con;
    }

    public void saveFoodData(FoodData foodData) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(con.openFileOutput(FILENAME, Context.MODE_PRIVATE));
            oos.writeObject(foodData);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public FoodData loadFoodData() {
        FoodData foodData = null;

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(con.openFileInput(FILENAME));
            foodData = (FoodData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return foodData;
    }
}
