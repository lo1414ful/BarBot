package com.cocktail.logic;

import androidx.annotation.NonNull;

import java.util.Objects;


@Deprecated
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
        if (posInRec < 0) {
            throw new IllegalArgumentException("position in recipe must not be negative");
        }
        this.ingredient = Objects.requireNonNull(ingredient, "ingredient must not be null");
        pours = 1;
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
        if (pours < 1) {
            throw new IllegalArgumentException("pours must be positive");
        }
        this.pours = pours;
    }

    public int getPositionInRecipe() {
        return positionInRecipe;
    }

    public void setPositionInRecipe(int positionInRecipe) {
        if (positionInRecipe < 0) {
            throw new IllegalArgumentException("position in recipe must not be negative");
        }
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
