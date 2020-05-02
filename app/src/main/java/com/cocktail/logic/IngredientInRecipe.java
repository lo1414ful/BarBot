package com.cocktail.logic;

import androidx.annotation.NonNull;

import java.util.Objects;

public class IngredientInRecipe {

    @NonNull
    private Ingredient ingredient;
    private int pours;
    private int positionInRecipe;


    /**
     * Constructor for a new Link between Recipe and Ingredient
     * @param ingredient the ingredient
     * @param posInRec the position in the recipe. lower positions are poured first
     * @throws IllegalArgumentException if the position is negative
     */
    public IngredientInRecipe(@NonNull Ingredient ingredient, int posInRec) throws IllegalArgumentException {
        this.ingredient = ingredient;
        pours = 1;
        if (posInRec < 0) {
            throw new IllegalArgumentException("position in recipe < 0");
        }
        this.positionInRecipe = posInRec;
    }

    @NonNull
    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getPours() {
        return pours;
    }

    public void setPours(int pours) {
        this.pours = pours;
    }

    public int getPositionInRecipe() {
        return positionInRecipe;
    }

    public void setPositionInRecipe(int positionInRecipe) {
        this.positionInRecipe = positionInRecipe;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !this.getClass().isAssignableFrom(o.getClass())) {
            return false;
        }
        IngredientInRecipe other = (IngredientInRecipe) o;
        return other.ingredient.equals(this.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient);
    }
}
