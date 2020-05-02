package com.cocktail.logic;


import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

public class Recipe {

    private static final String TAG = "Recipe";

    private String name;

    private Set<IngredientInRecipe> ingredients;

    /**
     * Constructor for a new Recipe
     * @param name the name of the new Recipe
     */
    public Recipe(String name) throws IllegalArgumentException {
        if (name.replace(" ", "").equals("")) {
            throw new IllegalArgumentException("name must not be empty");
        }
        this.name = name;
        ingredients = new HashSet<>();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * adds an ingredient at the end of the Recipe if the ingredient is not already part of this Recipe
     * @param ingredient the ingredient to add
     * @return true, if the ingredient was added successfully, false otherwise
     */
    public boolean addIngredient(@NonNull Ingredient ingredient) {
        IngredientInRecipe iir = new IngredientInRecipe(ingredient, this.ingredients.size());
        return ingredients.add(iir);
    }

    /**
     * removes the ingredient at the given position in the recipe (starting from 0)
     * @param position the position to remove from
     * @return true, if the ingredient was successfully removed, false otherwise if the position is out of bounds
     */
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

    /**
     * removes the ingredient from the Recipe
     * @param toRemove the ingredient to remove
     * @return true, if the ingredient was removed successfully, false otherwise or if the given ingredient was not part of this recipe
     */
    public boolean removeIngredient(@NonNull IngredientInRecipe toRemove) {
        return removeIngredient(toRemove.getPositionInRecipe());
    }

    /**
     * swaps the position of the two given ingredients if, and only if both ingredients are part of this recipe
     * @param a first ingredient
     * @param b second ingredient
     */
    public void swapPositions (IngredientInRecipe a, IngredientInRecipe b) {
        //TODO
    }
}
