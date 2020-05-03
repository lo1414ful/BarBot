package com.cocktail.logic;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recipe {


    private String name;

    protected final List<IngrInRec> ingredients;

    /**
     * Constructor for a new Recipe
     *
     * @param name the name of the new Recipe
     */
    public Recipe(@NonNull String name) throws IllegalArgumentException {
        Objects.requireNonNull(name, "name must not be null");
        if (name.replace(" ", "").equals("")) {
            throw new IllegalArgumentException("name must not be empty");
        }
        this.name = name;
        ingredients = new ArrayList<>();
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
     *
     * @param ingredient the ingredient to add
     * @return true, if the ingredient was added successfully, false otherwise
     */
    public boolean addIngredient(@NonNull Ingredient ingredient) {
        Objects.requireNonNull(ingredient, "ingredient must not be null");
        IngrInRec iir = new IngrInRec(ingredient, this);
        if (ingredients.contains(iir)) {
            return false;
        }
        ingredients.add(iir);
        return true;
    }

    /**
     * removes the ingredient at the given position in the recipe (starting from 0)
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
     * removes the ingredient from the Recipe
     *
     * @param toRemove the ingredient to remove
     * @return true, if the ingredient was removed successfully, false otherwise or if the given ingredient was not part of this recipe
     */
    public boolean removeIngredient(@NonNull Ingredient toRemove) {
        Objects.requireNonNull(toRemove, "ingredient must not be null");
        IngrInRec iir = new IngrInRec(toRemove, this);
        return ingredients.remove(iir);
    }

    /**
     * swaps the position of the two given ingredients if, and only if both ingredients are part of this recipe
     *
     * @param a first ingredient
     * @param b second ingredient
     */
    public boolean swapPositions(@NonNull Ingredient a, @NonNull Ingredient b) {
        Objects.requireNonNull(a, "ingredient must not be null");
        Objects.requireNonNull(b, "ingredient must not be null");
        return swapPositions(new IngrInRec(a, this), new IngrInRec(b, this));
    }

    public boolean swapPositions(@NonNull IngrInRec a, @NonNull IngrInRec b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);
        if (!ingredients.contains(a) || !ingredients.contains(b)) {
            return false;
        }
        int indexA = ingredients.indexOf(a);
        int indexB = ingredients.indexOf(b);
        if (indexA == indexB) return true;
        IngrInRec buff = ingredients.get(indexA);
        ingredients.set(indexA, ingredients.get(indexB));
        ingredients.set(indexB, buff);
        return true;
    }

    public void swapPositions(int pos1, int pos2) {
        if (pos1 < 0 || pos1 >= ingredients.size() || pos2 < 0 || pos2 >= ingredients.size()) {
            throw new IndexOutOfBoundsException("position(s) are out of bounds");
        }
        if (pos1 == pos2) return;
        IngrInRec buff = ingredients.get(pos1);
        ingredients.set(pos1, ingredients.get(pos2));
        ingredients.set(pos2, buff);
        return;
    }

    public int getPositionOfIngredient(@NonNull Ingredient ingr) {
        Objects.requireNonNull(ingr);
        return getPositionOfIngredient(new IngrInRec(ingr, this));
    }

    public int getPositionOfIngredient(@NonNull IngrInRec ingr) {
        Objects.requireNonNull(ingr);
        return ingredients.indexOf(ingr);
    }

}
