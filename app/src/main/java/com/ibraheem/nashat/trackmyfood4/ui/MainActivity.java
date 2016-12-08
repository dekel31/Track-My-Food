package com.ibraheem.nashat.trackmyfood4.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ibraheem.nashat.trackmyfood4.R;
import com.ibraheem.nashat.trackmyfood4.application.MyApp;
import com.ibraheem.nashat.trackmyfood4.model.Food;
import com.ibraheem.nashat.trackmyfood4.model.FoodData;

public class MainActivity extends Activity {

    private ListView todaysFood;
    private Button water;
    private TextView protein;
    private TextView carbohydrates;
    private TextView fat;
    private EditText newFoodType;
    private EditText newFoodCarbs;
    private EditText newFoodFat;
    private EditText newFoodProtein;
    private EditText newFoodMass;
    private EditText todayFoodMass;
    private Spinner foodTypes;

    private ArrayAdapter<Food> spinnerAdapter;
    private ArrayAdapter<Food> foodListAdapter;
    private FoodData foodData;

    public void addNewTypeClick(View view){
        String name = newFoodType.getText().toString();
        String carbs = newFoodCarbs.getText().toString();
        String protein = newFoodProtein.getText().toString();
        String fat = newFoodFat.getText().toString();
        if(carbs.equals("")){
            carbs = "0";
        }
        if(protein.equals("")){
            protein = "0";
        }
        if(fat.equals("")){
            fat = "0";
        }
        Food food = new Food(name,
                Integer.parseInt(carbs),
                Integer.parseInt(protein),
                Integer.parseInt(fat));
        if(newFoodMass.getText().toString().equals("")){
            food.setDefaultMass(1);
        } else {
            food.setDefaultMass(Integer.parseInt(newFoodMass.getText().toString()));
        }

        spinnerAdapter.remove(food);
        spinnerAdapter.add(food);

        EditText[] editTexts = {newFoodType, newFoodCarbs, newFoodProtein, newFoodFat, newFoodMass};
        for(EditText editText : editTexts){
            editText.setText("");
        }

        updateObjs();

        newFoodType.requestFocus();
    }

    public void addTodayFoodClick(View v) {
        Food foodType = (Food) foodTypes.getSelectedItem();
        int foodMass = Integer.parseInt(todayFoodMass.getText().toString().equals("") ? "1" : todayFoodMass.getText().toString());
        Food todayFood;
        if(foodMass < 10){
            todayFood = new Food(
                    foodType.getName(),
                    foodType.getCarbs() * foodMass,
                    foodType.getProtein() * foodMass,
                    foodType.getFat() * foodMass,
                    foodMass);
        } else {
            todayFood = new Food(
                    foodType.getName(),
                    foodType.getCarbs() * foodMass / 100,
                    foodType.getProtein() * foodMass / 100,
                    foodType.getFat() * foodMass / 100,
                    foodMass);
        }

        foodListAdapter.add(todayFood);

        updateObjs();
    }

    public void clearFoodList(View v) {
        foodListAdapter.clear();
        foodData.setWater(0);

        updateObjs();
    }

    public void addWater(View v) {
        foodData.setWater(foodData.getWater() + 1);

        updateObjs();
    }

    public void editFoodTypeAction(View v) {
        Food food = (Food) foodTypes.getSelectedItem();
        newFoodType.setText(food.getName());
        newFoodCarbs.setText(String.valueOf(food.getCarbs()));
        newFoodProtein.setText(String.valueOf(food.getProtein()));
        newFoodFat.setText(String.valueOf(food.getFat()));

        newFoodCarbs.requestFocus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findObjs();
        initObjs();
        updateObjs();

        todaysFood.requestFocus();
    }

    private void updateObjs() {
        spinnerAdapter.notifyDataSetChanged();
        foodListAdapter.notifyDataSetChanged();
        MyApp.storage.saveFoodData(foodData);

        int carbs = 0, protein = 0, fat = 0;
        for (Food food : foodData.getTodayFood()) {
            carbs += food.getCarbs();
            protein += food.getProtein();
            fat += food.getFat();
        }

        this.protein.setText(String.valueOf(protein));
        this.carbohydrates.setText(String.valueOf(carbs));
        this.fat.setText(String.valueOf(fat));
        water.setText(String.valueOf(foodData.getWater()));
        todaysFood.setSelection(todaysFood.getCount() - 1);
    }

    private void initObjs() {
        foodData = MyApp.storage.loadFoodData();
        if (foodData == null) {
            foodData = new FoodData();
        }

//        spinnerAdapter = new FoodListAdapter(this, android.R.layout.simple_spinner_dropdown_item, foodData.getFoodTypes());
//        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        foodTypes.setAdapter(spinnerAdapter);
//
//        foodListAdapter = new FoodListAdapter(this, R.layout.food_renderer, foodData.getTodayFood());
////        foodListAdapter.setDropDownViewResource(android.R.layout.);
//        todaysFood.setAdapter(foodListAdapter);

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, foodData.getFoodTypes());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodTypes.setAdapter(spinnerAdapter);

        foodListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, foodData.getTodayFood());
        foodListAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        todaysFood.setAdapter(foodListAdapter);
    }

    private AdapterView.OnItemSelectedListener spinnerClickListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Food item = spinnerAdapter.getItem(position);
            int defaultMass = item.getDefaultMass();
            todayFoodMass.setText(Integer.toString(defaultMass));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void findObjs() {
        todaysFood = (ListView) findViewById(R.id.foodList);
        newFoodType = (EditText) findViewById(R.id.newFoodType);
        newFoodCarbs = (EditText) findViewById(R.id.newFoodCarbs);
        newFoodProtein = (EditText) findViewById(R.id.newFoodProtein);
        newFoodFat = (EditText) findViewById(R.id.newFoodFat);
        newFoodMass = (EditText) findViewById(R.id.newFoodMass);
        foodTypes = (Spinner) findViewById(R.id.foodTypes);
        foodTypes.setOnItemSelectedListener(spinnerClickListener);
        todayFoodMass = (EditText) findViewById(R.id.todayFoodMass);
        water = (Button) findViewById(R.id.water);
        carbohydrates = (TextView) findViewById(R.id.carbohydrates);
        protein = (TextView) findViewById(R.id.protein);
        fat = (TextView) findViewById(R.id.fat);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
