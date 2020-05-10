package com.cocktail.logic;

import androidx.annotation.NonNull;

import java.util.Objects;

public class IngredientInRecipe {

    @NonNull
    private final Ingredient ingredient;
    @NonNull
    private final Recipe containingRecipe;
    private int pours;

    /**
     * The IngredientInRecipe-class represents the link between a recipe and its ingredients.
     * this class holds information about the quantity of a given ingredient in a specific recipe
     * the amount of pours is initialized with 1 by default, but can be set to any positive number with the {@link #setPours(int)}-method
     *  @param ingr the ingredient to represent in a recipe
     * @param containingRec the recipe this link is associated with
     */
    public IngredientInRecipe(@NonNull Ingredient ingr, @NonNull Recipe containingRec) {
        Objects.requireNonNull(ingr);
        Objects.requireNonNull(containingRec);
        this.ingredient = ingr;
        this.containingRecipe = containingRec;
        pours = 1;
    }

    /**
     * @return the ingredient of this link
     */
    @NonNull
    public Ingredient getIngredient() {
        return this.ingredient;
    }

    /**
     * the recipe this link is associated with
     * @return
     */
    @NonNull
    private Recipe getContainingRecipe() {
        return this.containingRecipe;
    }

    /**
     * @return the amount of pours of the ingredient in the associated recipe
     */
    public int getPours() {
        return pours;
    }

    /**
     * sets the amount of pours of the ingredient in the associated recipe
     * @param pours the amount of pours (must be positive)
     * @throws IllegalArgumentException if the given amount is zero or negative
     */
    public void setPours(int pours) throws IllegalArgumentException {
        if (pours < 1) {
            throw new IllegalArgumentException("number of pours must be positive");
        }
        this.pours = pours;
    }

    /**
     * @return the position this ingredient has in the associated recipe
     */
    public int getPositionInRecipe() {
        return containingRecipe.getPositionOfIngredient(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !this.getClass().isAssignableFrom(o.getClass())) {
            return false;
        }
        IngredientInRecipe other = (IngredientInRecipe) o;
        return ingredient.equals(other.ingredient) && other.getContainingRecipe().equals(getContainingRecipe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(containingRecipe, ingredient);
    }
}
