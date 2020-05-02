package com.cocktail.logic;


import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

public class Recipe {

    private static final String TAG = "Recipe";

    private String name;

    private Set<IngredientInRecipe> ingredients;

    public Recipe(String name) {
        this.name = name;
        ingredients = new HashSet<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean addIngredient(@NonNull Ingredient ingredient) {
        IngredientInRecipe iir = new IngredientInRecipe(ingredient, this.ingredients.size());
        return ingredients.add(iir);
    }

    public boolean removeIngredient(int position) {
        if (position < 0 || position >= this.ingredients.size()) {
            return false;
        }
        IngredientInRecipe toRemove = null;
        for (IngredientInRecipe iir : this.ingredients) {
            if (iir.getPositionInRecipe() == position) {
                toRemove = iir;
            } else if (iir.getPositionInRecipe() > position) {
                iir.setPositionInRecipe(iir.getPositionInRecipe() - 1);
            }
        }
        return this.ingredients.remove(toRemove);
    }

    public boolean removeIngredient(@NonNull Ingredient ingredient) {
        IngredientInRecipe toRemove = null;
        for (IngredientInRecipe iir : ingredients) {
            if (ingredient.equals(iir.getIngredient())) {
                toRemove = iir;
            }
        }
        if (toRemove == null) {
            return false;
        }
        return removeIngredient(toRemove.getPositionInRecipe());
    }
}
