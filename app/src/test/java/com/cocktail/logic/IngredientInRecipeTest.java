package com.cocktail.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;

public class IngredientInRecipeTest {

    private Ingredient ingr;
    private IngredientInRecipe iir, allMatch, ingrMatch, posMatch, noMatch;

    @Before
    public void setup() {
        ingr = new Ingredient("bla", 2, 3, 4);
        iir = new IngredientInRecipe(ingr, 0);
        allMatch = new IngredientInRecipe(ingr, 0);
        ingrMatch = new IngredientInRecipe(ingr, 15);
        posMatch = new IngredientInRecipe(new Ingredient("other bla", 2, 3, 4), 0);
        noMatch = new IngredientInRecipe(new Ingredient("blib", 5,6,7), 100);
    }


    @After
    public void cleanup() {
        ingr = null;
        iir = null;
        allMatch = null;
        ingrMatch = null;
        posMatch = null;
        noMatch = null;
    }

    @Test
    public void testLegalConstructor() {
        IngredientInRecipe ingrInRec = new IngredientInRecipe(ingr, 0);
        assertEquals(ingr, ingrInRec.getIngredient());
        assertEquals(0, ingrInRec.getPositionInRecipe());
        assertEquals(1, ingrInRec.getPours());
    }

    @Test
    public void testIllegalConstructor() {
        Throwable t = assertThrows(NullPointerException.class, () -> new IngredientInRecipe(null, 0));
        assertEquals("ingredient must not be null", t.getMessage());
        t = assertThrows(IllegalArgumentException.class, () -> new IngredientInRecipe(ingr, -3));
        assertEquals("position in recipe must not be negative", t.getMessage());
    }

    @Test
    public void testLegalSetterGetter() {
        iir.setPositionInRecipe(3);
        assertEquals(3, iir.getPositionInRecipe());
        assertEquals(1, iir.getPours());
        iir.setPours(4);
        assertEquals(4, iir.getPours());
        assertEquals(3, iir.getPositionInRecipe());
        iir.setPositionInRecipe(0);
        assertEquals(0, iir.getPositionInRecipe());
        assertEquals(4, iir.getPours());
    }

    @Test
    public void testIllegalSetterGetter() {
        Throwable t = assertThrows(IllegalArgumentException.class, () -> iir.setPours(-3));
        assertEquals("pours must be positive", t.getMessage());
        t = assertThrows(IllegalArgumentException.class, () -> iir.setPours(0));
        assertEquals("pours must be positive", t.getMessage());
        t = assertThrows(IllegalArgumentException.class, () -> iir.setPositionInRecipe(-3));
        assertEquals("position in recipe must not be negative", t.getMessage());
    }

    @Test
    public void testEquals() {
        assertNotEquals("equals with blank object", iir, new Object());
        assertEquals("equals itself", iir, iir);
        assertEquals("equals equal object", allMatch, iir);
        assertEquals("equals only in ingredient", ingrMatch, iir);
        assertNotEquals("equals only in posInRec", posMatch, iir);
        assertNotEquals("equals null", null, iir);
        int oldPours = allMatch.getPours();
        allMatch.setPours(99);
        assertEquals("equals different pours", iir, allMatch);
        allMatch.setPours(oldPours);
        int oldPosition = allMatch.getPositionInRecipe();
        allMatch.setPositionInRecipe(33);
        assertEquals("equals different position", iir, allMatch);
    }

    @Test
    public void testHashCode() {
        assertEquals("identical hashCode", iir.hashCode(), iir.hashCode());
        assertEquals("allMatch hashCode", allMatch.hashCode(), iir.hashCode());
        assertEquals("ingrMatch hashCode", ingrMatch.hashCode(), iir.hashCode());
    }

}

