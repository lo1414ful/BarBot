package com.cocktail.ui;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.cocktail.database.BBDatabase;
import com.cocktail.database.IngredientEntity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class IngredientsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        FloatingActionButton fab = findViewById(R.id.fab_add_ingredient);
        fab.setOnClickListener(view -> addIngredient());

        final Observer<List<IngredientEntity>> ingredientObserver = ingredientEntities -> ingredientEntities.stream().forEach(ie -> System.out.println(ie.getUid() + " | " + ie.getName()));

        BBDatabase.getSingleton(null).ingredientDao().getAll().observe(this, ingredientObserver);

        //create List of Ingredients
        listIngredients();


    }

    private static char c = 'A';
    private void addIngredient() {
        IngredientEntity.createIngredient(Character.toString(c), 5, 5, 5);
        c++;
        if (c == 'z' + 1) { c = 'A';}
    }

    private void listIngredients() {
        LinearLayout ll = findViewById(R.id.ingredients_linear_layout);
        ll.removeAllViews();

        new Thread(() -> {
           //TODO
        }).run();
    }
}
