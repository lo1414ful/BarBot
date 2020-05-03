package com.cocktail.logic;

import androidx.annotation.NonNull;

import java.util.Objects;

public class IngrInRec {

    @NonNull
    private final Ingredient ingredient;
    private int pours;
    @NonNull
    private final Recipe containingRecipe;

    public IngrInRec(@NonNull Ingredient ingr, @NonNull Recipe containingRec) {
        Objects.requireNonNull(ingr);
        Objects.requireNonNull(containingRec);
        this.ingredient = ingr;
        this.containingRecipe = containingRec;
        pours = 1;
    }

    @NonNull
    public Ingredient getIngredient() {
        return this.ingredient;
    }

    @NonNull
    private Recipe getContainingRecipe() {
        return this.containingRecipe;
    }

    public int getPours() {
        return pours;
    }

    public void setPours(int pours) {
        if (pours < 1) {
            throw new IllegalArgumentException("number of pours must be positive");
        }
        this.pours = pours;
    }

    public int getPositionInRecipe() {
        return containingRecipe.getPositionOfIngredient(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !this.getClass().isAssignableFrom(o.getClass())) {
            return false;
        }
        IngrInRec other = (IngrInRec) o;
        return ingredient.equals(other.ingredient) && other.getContainingRecipe().equals(getContainingRecipe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(containingRecipe, ingredient);
    }
}
