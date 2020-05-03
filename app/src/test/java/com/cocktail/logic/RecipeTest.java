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
    private Ingredient ingr, pw;

    @Before
    public void setup() {
        recipe = new Recipe("Caipirinha");
        ingr = new Ingredient("Cachaca", 2, 3, 4);
        pw = new Ingredient("Pisswater", 6, 7, 8);
    }

    @After
    public void cleanup() {
        recipe = null;
        ingr = null;
        pw = null;
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

    @Test
    public void testRemovePosition0() {
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> recipe.removeIngredient(-5));
        assertEquals("position out of bounds", t.getMessage());
        t = assertThrows(IndexOutOfBoundsException.class, () -> recipe.removeIngredient(1));
        assertEquals("position out of bounds", t.getMessage());
        t = assertThrows(IndexOutOfBoundsException.class, () -> recipe.removeIngredient(0));
        assertEquals("position out of bounds", t.getMessage());
        assertTrue(recipe.addIngredient(ingr));
        Ingredient removed = recipe.removeIngredient(0);
        assertEquals(ingr, removed);
        t = assertThrows(IndexOutOfBoundsException.class, () -> recipe.removeIngredient(0));
        assertEquals("position out of bounds", t.getMessage());

    }

    @Test
    public void testRemovePosition1() {
        assertTrue(recipe.addIngredient(ingr));
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> recipe.removeIngredient(1));
        assertEquals("position out of bounds", t.getMessage());
        Ingredient pisswater = new Ingredient("pisswater", 6, 5, 4);
        assertTrue(recipe.addIngredient(pisswater));
        assertEquals("removing first (index 0) element from recipe", ingr, recipe.removeIngredient(0));
        assertEquals("removing first (index 0) element again from recipe", pisswater, recipe.removeIngredient(0));
    }

    @Test
    public void testRemovePosition2() {
        assertTrue(recipe.addIngredient(ingr));
        Ingredient pisswater = new Ingredient("pisswater", 2, 3, 4);
        assertTrue(recipe.addIngredient(pisswater));
        Ingredient mntnDw = new Ingredient("mountain Dew", 6, 4, 2);
        assertTrue(recipe.addIngredient(mntnDw));
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> recipe.removeIngredient(3));
        assertEquals(pisswater, recipe.removeIngredient(1));
        assertEquals(mntnDw, recipe.removeIngredient(1));
        assertEquals(ingr, recipe.removeIngredient(0));
    }

    @Test
    public void testRemoveIngr0() {
        assertFalse(recipe.removeIngredient(ingr));
        recipe.addIngredient(ingr);
        assertTrue(recipe.removeIngredient(ingr));
        assertFalse(recipe.removeIngredient(ingr));
    }

    @Test
    public void testRemoveIngr1() {
        recipe.addIngredient(ingr);
        Ingredient pisswater = new Ingredient("pisswater", 2, 3, 4);
        recipe.addIngredient(pisswater);
        assertFalse(recipe.removeIngredient(new Ingredient("mountain Dew", 2, 3, 4)));
        assertTrue(recipe.removeIngredient(ingr));
        assertFalse(recipe.removeIngredient(ingr));
        assertTrue(recipe.removeIngredient(pisswater));
        assertFalse(recipe.removeIngredient(pisswater));
    }

    @Test
    public void testRemovePosAfterIngr() {
        recipe.addIngredient(ingr);
        assertTrue(recipe.removeIngredient(ingr));
        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> recipe.removeIngredient(0));
    }

    @Test
    public void testRemoveIngrAfterPos() {
        recipe.addIngredient(ingr);
        assertEquals(ingr, recipe.removeIngredient(0));
        assertFalse(recipe.removeIngredient(ingr));
    }

    @Test
    public void testGetPositionOfIngredient() {
        assertThrows(NullPointerException.class, () -> recipe.getPositionOfIngredient((IngredientInRecipe) null));
        assertThrows(NullPointerException.class, () -> recipe.getPositionOfIngredient((Ingredient) null));
        assertEquals(-1, recipe.getPositionOfIngredient(ingr));
        assertEquals(-1, recipe.getPositionOfIngredient(new IngredientInRecipe(ingr, recipe)));
        assertTrue(recipe.addIngredient(ingr));
        assertEquals(0, recipe.getPositionOfIngredient(ingr));
        assertEquals(0, recipe.getPositionOfIngredient(new IngredientInRecipe(ingr, recipe)));
        Ingredient newIngr = new Ingredient("Pisswater", 2, 3, 4);
        assertEquals(-1, recipe.getPositionOfIngredient(newIngr));
        assertTrue(recipe.addIngredient(newIngr));
        assertEquals(0, recipe.getPositionOfIngredient(ingr));
        assertEquals(1, recipe.getPositionOfIngredient(newIngr));
        assertEquals(0, recipe.getPositionOfIngredient(new IngredientInRecipe(ingr, recipe)));
        assertEquals(1, recipe.getPositionOfIngredient(new IngredientInRecipe(newIngr, recipe)));
        recipe.removeIngredient(0);
        assertEquals(-1, recipe.getPositionOfIngredient(ingr));
        assertEquals(0, recipe.getPositionOfIngredient(newIngr));
    }

    @Test
    public void testSwapPosition0() {
        recipe.addIngredient(ingr);
        recipe.addIngredient(pw);
        assertEquals(0, recipe.getPositionOfIngredient(ingr));
        assertEquals(1, recipe.getPositionOfIngredient(pw));
        assertTrue(recipe.swapPositions(ingr, pw));
        assertEquals(0, recipe.getPositionOfIngredient(pw));
        assertEquals(1, recipe.getPositionOfIngredient(ingr));
        recipe.swapPositions(ingr, pw);
        assertEquals(0, recipe.getPositionOfIngredient(ingr));
        assertEquals(1, recipe.getPositionOfIngredient(pw));
    }

    @Test
    public void testSwapPosition1() {
        recipe.addIngredient(ingr);
        recipe.addIngredient(pw);
        IngredientInRecipe ingrInRec = new IngredientInRecipe(ingr, recipe);
        IngredientInRecipe pwInRec = new IngredientInRecipe(pw, recipe);
        assertEquals(0, recipe.getPositionOfIngredient(ingr));
        assertEquals(1, recipe.getPositionOfIngredient(pw));
        assertTrue(recipe.swapPositions(ingrInRec, pwInRec));
        assertEquals(0, recipe.getPositionOfIngredient(pw));
        assertEquals(1, recipe.getPositionOfIngredient(ingr));
        assertTrue(recipe.swapPositions(ingrInRec, pwInRec));
        assertEquals(0, recipe.getPositionOfIngredient(ingr));
        assertEquals(1, recipe.getPositionOfIngredient(pw));
    }

    @Test
    public void testSwapPosition2() {
        assertFalse(recipe.swapPositions(ingr, ingr));
        assertFalse(recipe.swapPositions(ingr, pw));
        recipe.addIngredient(ingr);
        assertTrue(recipe.swapPositions(ingr, ingr));
        assertFalse(recipe.swapPositions(ingr, pw));
        recipe.addIngredient(pw);
        assertTrue(recipe.swapPositions(ingr, ingr));
        assertTrue(recipe.swapPositions(ingr, pw));

        IngredientInRecipe iir = new IngredientInRecipe(ingr, new Recipe("poop cocktail"));
        assertFalse(recipe.swapPositions(new IngredientInRecipe(ingr, recipe), iir));
    }

    @Test
    public void testSwapPositions3() {
        recipe.addIngredient(ingr);
        recipe.addIngredient(pw);
        assertEquals(0, recipe.getPositionOfIngredient(ingr));
        assertEquals(1, recipe.getPositionOfIngredient(pw));
        recipe.swapPositions(0, 1);
        assertEquals(1, recipe.getPositionOfIngredient(ingr));
        assertEquals(0, recipe.getPositionOfIngredient(pw));
        recipe.swapPositions(1, 0);
        assertEquals(0, recipe.getPositionOfIngredient(ingr));
        assertEquals(1, recipe.getPositionOfIngredient(pw));

        recipe.swapPositions(1, 1);
        assertEquals(0, recipe.getPositionOfIngredient(ingr));
        assertEquals(1, recipe.getPositionOfIngredient(pw));

        Throwable t = assertThrows(IndexOutOfBoundsException.class, () -> recipe.swapPositions(400, 1));
        assertEquals("position(s) are out of bounds", t.getMessage());
        t = assertThrows(IndexOutOfBoundsException.class, () -> recipe.swapPositions(-4, 1));
        assertEquals("position(s) are out of bounds", t.getMessage());
        assertEquals(0, recipe.getPositionOfIngredient(ingr));
        assertEquals(1, recipe.getPositionOfIngredient(pw));
    }
}
