package com.cocktail.logic;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Recipe {


    private String name;

    private Set<IngredientInRecipe> ingredients;

    /**
     * Constructor for a new Recipe
     * @param name the name of the new Recipe
     */
    public Recipe(@NonNull String name) throws IllegalArgumentException {
        Objects.requireNonNull(name, "name must not be null");
        if (name.replace(" ", "").equals("")) {
            throw new IllegalArgumentException("name must not be empty");
        }
        this.name = name;
        ingredients = new HashSet<>();
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        Objects.requireNonNull(name, "name must not be null");
        if (name.replace(" ", "").equals("")) {
            throw new IllegalArgumentException("name must not be empty");
        }
        this.name = name;
    }

    /**
     * adds an ingredient at the end of the Recipe if the ingredient is not already part of this Recipe
     * @param ingredient the ingredient to add
     * @return true, if the ingredient was added successfully, false otherwise
     */
    public boolean addIngredient(@NonNull Ingredient ingredient) {
        Objects.requireNonNull(ingredient, "ingredient must not be null");
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


    private class ListSet {

        private final Recipe recipe;
        private final Set<IngredientInRecipe> ingredientsSet;
        private final List<IngredientInRecipe> ingredientsList;

        private ListSet(@NonNull Recipe rec) {
            Objects.requireNonNull(rec, "recipe must not be null");
            this.recipe = rec;
            ingredientsSet = new HashSet<>();
            ingredientsList = new ArrayList<>();
        }

        public boolean addIngredient(@NonNull Ingredient ingr) {
            Objects.requireNonNull(ingr, "ingredient must not be null");
            IngredientInRecipe iir = new IngredientInRecipe(ingr, ingredientsList.size());
            if (ingredients.contains(iir)) {
                return false;
            }
            ingredientsSet.add(iir);
            ingredientsList.add(iir);
            return true;
        }

        public boolean removeIngredient(@NonNull IngredientInRecipe iir) {
            Objects.requireNonNull(iir, "ingredient must not be null");
            if (!ingredientsSet.contains(iir)) {
                return false;
            }
            ingredientsSet.remove(iir);
            ingredientsList.remove(iir);
            return true;
        }

        public boolean swapIngredients(@NonNull Ingredient a, @NonNull Ingredient b) {
            Objects.requireNonNull(a, "ingredient must not be null");
            Objects.requireNonNull(b, "ingredient must not be null");
            //TODO
            return false;
        }

        private boolean isPartOfThisRecipe(IngredientInRecipe iir) {
            //TODO
            return false;
        }








    }
}
