package com.cocktail.logic;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recipe {


    private String name;

    protected final List<IngredientInRecipe> ingredients;

    /**
     * A Recipe models a cocktail recipe.
     *
     * @param name the name of the new Recipe
     * @throws IllegalArgumentException if the given name is an empty string
     */
    public Recipe(@NonNull String name) throws IllegalArgumentException {
        Objects.requireNonNull(name, "name must not be null");
        if (name.replace(" ", "").equals("")) {
            throw new IllegalArgumentException("name must not be empty");
        }
        this.name = name;
        ingredients = new ArrayList<>();
    }

    /**
     * @return the name of the recipe
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * sets the name of the recipe
     * @param name the name
     * @throws IllegalArgumentException if the given name is an empty string
     */
    public void setName(@NonNull String name) throws IllegalArgumentException {
        Objects.requireNonNull(name, "name must not be null");
        if (name.replace(" ", "").equals("")) {
            throw new IllegalArgumentException("name must not be empty");
        }
        this.name = name;
    }

    /**
     * adds an ingredient at the end of the Recipe if the ingredient is not already part of this Recipe
     *
     * @param ingredient the ingredient to add
     * @return true, if the ingredient was added successfully, false otherwise
     */
    public boolean addIngredient(@NonNull Ingredient ingredient) {
        Objects.requireNonNull(ingredient, "ingredient must not be null");
        IngredientInRecipe iir = new IngredientInRecipe(ingredient, this);
        if (ingredients.contains(iir)) {
            return false;
        }
        ingredients.add(iir);
        return true;
    }

    /**
     * removes the ingredient at the given position in the recipe (starting at 0)
     *
     * @param position the position to remove from
     * @return true, if the ingredient was successfully removed, false otherwise if the position is out of bounds
     * @throws IndexOutOfBoundsException if position is out of bounds
     */
    public Ingredient removeIngredient(int position) {
        if (position < 0 || position >= this.ingredients.size()) {
            throw new IndexOutOfBoundsException("position out of bounds");
        }
        return ingredients.remove(position).getIngredient();
    }

    /**
     * removes a given ingredient from the Recipe
     *
     * @param toRemove the ingredient to remove
     * @return true, if the ingredient was removed successfully, false otherwise or if the given ingredient is not part of this recipe
     */
    public boolean removeIngredient(@NonNull Ingredient toRemove) {
        Objects.requireNonNull(toRemove, "ingredient must not be null");
        IngredientInRecipe iir = new IngredientInRecipe(toRemove, this);
        return ingredients.remove(iir);
    }

    /**
     * swaps the position of the two given ingredients if, and only if both ingredients are part of this recipe
     * and the two ingredients are not equal
     *
     * @param a first ingredient
     * @param b second ingredient
     * @return true, if the ingredients were swapped successfully, false otherwise or when at least one ingredient is not part of this recipe
     */
    public boolean swapPositions(@NonNull Ingredient a, @NonNull Ingredient b) {
        Objects.requireNonNull(a, "ingredient must not be null");
        Objects.requireNonNull(b, "ingredient must not be null");
        return swapPositions(new IngredientInRecipe(a, this), new IngredientInRecipe(b, this));
    }

    /**
     * same as {@link #swapPositions(Ingredient, Ingredient)} but with IngredientInRecipe-class
     * @param a first ingredient
     * @param b second ingredient
     * @return true, if the ingredients were swapped successfully, false otherwise or if one or both ingredients were not part of this recipe
     */
    public boolean swapPositions(@NonNull IngredientInRecipe a, @NonNull IngredientInRecipe b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        if (!ingredients.contains(a) || !ingredients.contains(b)) {
            return false;
        }
        int indexA = ingredients.indexOf(a);
        int indexB = ingredients.indexOf(b);
        if (indexA == indexB) return true;
        IngredientInRecipe buff = ingredients.get(indexA);
        ingredients.set(indexA, ingredients.get(indexB));
        ingredients.set(indexB, buff);
        return true;
    }

    /**
     * swaps the ingredients at the given positions
     * @param pos1 first position
     * @param pos2 second position
     * @throws IndexOutOfBoundsException if a given position is out of bounds
     */
    public void swapPositions(int pos1, int pos2) throws IndexOutOfBoundsException {
        if (pos1 < 0 || pos1 >= ingredients.size() || pos2 < 0 || pos2 >= ingredients.size()) {
            throw new IndexOutOfBoundsException("position(s) are out of bounds");
        }
        if (pos1 == pos2) return;
        IngredientInRecipe buff = ingredients.get(pos1);
        ingredients.set(pos1, ingredients.get(pos2));
        ingredients.set(pos2, buff);
        return;
    }

    /**
     * Returns the index of the first occurrence of the specified ingredient in this recipe.
     * @param ingr the ingredient to look up
     * @return the index of the first occurence of the ingredient, or -1 if the ingredient is not part of this recipe
     */
    public int getPositionOfIngredient(@NonNull Ingredient ingr) {
        Objects.requireNonNull(ingr);
        return getPositionOfIngredient(new IngredientInRecipe(ingr, this));
    }

    /**
     * same as {@link #getPositionOfIngredient(Ingredient)} but with IngredientInRecipe-class
     * @param ingr the ingredient to look up
     * @return the index of the first occurence of the ingredient, or -1 if the ingredient is not part of this recipe
     */
    public int getPositionOfIngredient(@NonNull IngredientInRecipe ingr) {
        Objects.requireNonNull(ingr);
        return ingredients.indexOf(ingr);
    }

}
