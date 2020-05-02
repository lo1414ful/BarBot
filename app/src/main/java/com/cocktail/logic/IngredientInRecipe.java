package com.cocktail.logic;

import androidx.annotation.NonNull;

public class IngredientInRecipe {

    @NonNull
    private Ingredient ingredient;
    private int pours;
    private int positionInRecipe;

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
}
