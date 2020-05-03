package com.cocktail.logic;

import androidx.annotation.NonNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngrInRecTest {

    private Ingredient rum, cola;
    private Recipe cubaLibre, mojito;

    private IngrInRec iir, allMatch, ingrMatch, recMatch, noMatch;

    @Before
    public void setUp() throws Exception {
        rum = new Ingredient("Havanna Club",2, 3, 4);
        cola = new Ingredient("Cola", 6,7,8);
        cubaLibre = new Recipe("Cuba Libre");
        mojito = new Recipe("Mojito");
        iir = new IngrInRec(rum, cubaLibre);
        allMatch = new IngrInRec(rum, cubaLibre);
        ingrMatch = new IngrInRec(rum, mojito);
        recMatch = new IngrInRec(cola, cubaLibre);
        noMatch = new IngrInRec(cola, mojito);
    }

    @After
    public void tearDown() throws Exception {
        rum = null;
        cola = null;
        cubaLibre = null;
        mojito = null;
        iir = null;
        allMatch = null;
        ingrMatch = null;
        recMatch = null;
        noMatch = null;
    }

    @Test
    public void constructor() {
        IngrInRec iir = new IngrInRec(rum, cubaLibre);
    }

    @Test
    public void getIngredient() {
        assertEquals(rum, iir.getIngredient());
    }

    @Test
    public void pours() {
        assertEquals(1, iir.getPours());
        iir.setPours(5);
        assertEquals(5, iir.getPours());
        Throwable t = assertThrows(IllegalArgumentException.class, () -> iir.setPours(-5));
        assertEquals("number of pours must be positive", t.getMessage());
        t = assertThrows(IllegalArgumentException.class, () -> iir.setPours(0));
        assertEquals("number of pours must be positive", t.getMessage());
        assertEquals(5, iir.getPours());
    }

    @Test
    public void positionInRecipe() {
        RecipeDummy dummy = new RecipeDummy("dummy");
        dummy.addIngredient(rum);
        IngrInRec rumInRec = dummy.lastAdded;
        assertEquals(0, rumInRec.getPositionInRecipe());
        dummy.addIngredient(cola);
        IngrInRec colaInRec = dummy.lastAdded;
        assertEquals(1, colaInRec.getPositionInRecipe());
        dummy.swapPositions(rum, cola);
        assertEquals(0, colaInRec.getPositionInRecipe());
        assertEquals(1, rumInRec.getPositionInRecipe());
    }

    @Test
    public void testEquals() {
        assertEquals(iir, iir);
        assertEquals(iir, allMatch);
        assertNotEquals(iir, ingrMatch);
        assertNotEquals(iir, recMatch);
        assertNotEquals(iir, noMatch);
        assertNotEquals(iir, null);
        assertNotEquals(iir, new Object());
    }

    @Test
    public void testHashCode() {
        assertEquals(iir.hashCode(), iir.hashCode());
        assertEquals(iir.hashCode(), allMatch.hashCode());
    }


    private class RecipeDummy extends Recipe {

        private IngrInRec lastAdded;

        private RecipeDummy(String name) {
            super(name);
            lastAdded = null;
        }

        @Override
        public boolean addIngredient(@NonNull Ingredient ingredient) {
            IngrInRec iir = new IngrInRec(ingredient, this);
            if (ingredients.contains(iir)) {
                return false;
            }
            ingredients.add(iir);
            lastAdded = iir;
            return true;
        }
    }
}