package com.cocktail.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class RecipeTest {

    private Recipe recipe;
    private Ingredient ingr;

    @Before
    public void setup() {
        recipe = new Recipe("Caipirinha");
        ingr = new Ingredient("Cachaca", 2, 3, 4);
    }

    @After
    public void cleanup() {
        recipe = null;
        ingr = null;
    }


    @Test
    public void testLegalConstructor() {
        String name = "Cuba Libre";
        Recipe tmpRecipe = new Recipe(name);
        assertEquals("recipe name", name, tmpRecipe.getName());
        String newName = "Cuba not so Libre";
        tmpRecipe.setName(newName);
        assertEquals("recipe new name", newName, tmpRecipe.getName());
    }

    @Test
    public void testIllegalConstructor() {
        Throwable t = assertThrows(NullPointerException.class, () -> new Recipe(null));
        assertEquals("recipe name null", "name must not be null", t.getMessage());
        t = assertThrows(IllegalArgumentException.class, () -> new Recipe(""));
        assertEquals("recipe name is empty string", "name must not be empty", t.getMessage());
        t = assertThrows(IllegalArgumentException.class, () -> new Recipe(" "));
        assertEquals("recipe name ist empty space string", "name must not be empty", t.getMessage());
    }

    @Test
    public void testLegalSetterGetter() {
        String newName = "Cosmopolitan";
        recipe.setName(newName);
        assertEquals(newName, recipe.getName());
        newName = "Zombie";
        recipe.setName(newName);
        assertEquals(newName, recipe.getName());
    }

    @Test
    public void testIllegalSetterGetter() {
        String name = recipe.getName();
        Throwable t = assertThrows(NullPointerException.class, () -> recipe.setName(null));
        assertEquals("name must not be null", t.getMessage());
        assertEquals(name, recipe.getName());
        t = assertThrows(IllegalArgumentException.class, () -> recipe.setName(""));
        assertEquals("name must not be empty", t.getMessage());
        assertEquals(name, recipe.getName());
        t = assertThrows(IllegalArgumentException.class, () -> recipe.setName(" "));
        assertEquals("name must not be empty", t.getMessage());
        assertEquals(name, recipe.getName());
    }

    @Test
    public void testAddIngredient() {
        boolean inserted = recipe.addIngredient(ingr);
        assertTrue("first insertion", inserted);
        inserted = recipe.addIngredient(ingr);
        assertFalse("second insertion", inserted);
        for (int i = 0; i < 10; ++i) {
            inserted = recipe.addIngredient(ingr);
            assertFalse("another insertion", inserted);
        }
        Recipe rec = new Recipe("Cosmo");
        Throwable t = assertThrows(NullPointerException.class, () -> rec.addIngredient(null));
        assertEquals("insert null ingredient", "ingredient must not be null", t.getMessage());

    }

}
