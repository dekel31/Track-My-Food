package com.ibraheem.nashat.trackmyfood4.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ibraheem.nashat.trackmyfood4.R;
import com.ibraheem.nashat.trackmyfood4.model.Food;

import java.util.List;

/**
 * Created by dekela on 1/7/2016.
 */
public class FoodListAdapter extends ArrayAdapter<Food> {
    public FoodListAdapter(Context context, int resource, List<Food> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.food_renderer, null);
        }
        Food food = getItem(position);

        TextView name = (TextView) v.findViewById(R.id.name);
        TextView carbs = (TextView) v.findViewById(R.id.carbohydrates);
        TextView protein = (TextView) v.findViewById(R.id.protein);
        if(name != null){
            name.setText(food.getName());
        }
        if(carbs != null){
            carbs.setText(food.getCarbs());
        }
        if(protein != null){
            protein.setText(food.getProtein());
        }

        return super.getView(position, convertView, parent);
    }
}
