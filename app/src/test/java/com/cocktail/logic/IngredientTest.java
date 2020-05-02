package com.cocktail.logic;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertThrows;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.Is.isA;
import static org.junit.Assert.*;

public class IngredientTest {

    private Ingredient identity, noMatch, allMatch, nameMatch;



    @Before
    public void setUp() {
        identity = new Ingredient("bla", 1, 2, 3);
        noMatch = new Ingredient("other bla", 20, 33, 41);
        allMatch = new Ingredient("bla", 1, 2, 3);
        nameMatch = new Ingredient("bla", 5, 6, 7);
    }

    @After
    public void tearDown() {
        identity = null;
        noMatch = null;
        allMatch = null;
        nameMatch = null;
    }

    @Test
    public void testLegalParameters() {
        String ingrName = "name";
        int position = 3;
        int hold = 14;
        int wait = 12;
        Ingredient i = new Ingredient(ingrName, position, hold, wait);
        assertEquals("name", ingrName, i.getName());
        assertEquals("position", position, i.getPosition());
        assertEquals("hold", hold, i.getHold());
        assertEquals("wait", wait, i.getWait());
        assertFalse(i.isActivated());
        String newName = "newName";
        int newPos = 99;
        int newHold = 13;
        int newWait = 17;
        i.setName(newName);
        i.setPosition(newPos);
        i.setHold(newHold);
        i.setWait(newWait);
        i.setActivated(true);
        assertEquals("new name", newName, i.getName());
        assertEquals("new position", newPos, i.getPosition());
        assertEquals("new hold", newHold, i.getHold());
        assertEquals("new wait", newWait, i.getWait());
        assertTrue(i.isActivated());
    }

    @Test
    public void testConstuctorExceptions() {
        String basicMessage = "failed to create Ingredient:";
        String nameMessage = "\r\n  name must not be an empty string";
        String holdMessage = "\r\n  hold duration must not be negative";
        String waitMessage = "\r\n  wait duration must not be negative";
        Throwable e = assertThrows(IllegalArgumentException.class, () -> new Ingredient("", 2, 3, 4));
        assertEquals(basicMessage + nameMessage, e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> new Ingredient(" ", 2, 3, 4));
        assertEquals(basicMessage + nameMessage, e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> new Ingredient("bsdaf", 2, -3, 2));
        assertEquals(basicMessage + holdMessage, e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> new Ingredient("dasd", 2, 3, -4));
        assertEquals(basicMessage + waitMessage, e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> new Ingredient(" ", 2, -3, 4));
        assertEquals(basicMessage + nameMessage + holdMessage, e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> new Ingredient(" ", 2, 4, -5));
        assertEquals(basicMessage + nameMessage + waitMessage, e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> new Ingredient("dsads", 2, -3, -4));
        assertEquals(basicMessage + holdMessage + waitMessage, e.getMessage());
        e = assertThrows(IllegalArgumentException.class, () -> new Ingredient(" ", 2, -3, -4));
        assertEquals(basicMessage + nameMessage + holdMessage + waitMessage, e.getMessage());
    }

    @Test
    public void testSetterExceptions() {
        String oldName = identity.getName();
        Exception e = assertThrows(IllegalArgumentException.class, () -> identity.setName(""));
        assertEquals("name must not be empty", e.getMessage());
        assertEquals(oldName, identity.getName());
        e = assertThrows(IllegalArgumentException.class, () -> identity.setName(" "));
        assertEquals("name must not be empty", e.getMessage());
        assertEquals(oldName, identity.getName());
        int oldHold = identity.getHold();
        e = assertThrows(IllegalArgumentException.class, () -> identity.setHold(-4));
        assertEquals("hold duration must not be negative", e.getMessage());
        assertEquals(oldHold, identity.getHold());
        int oldWait = identity.getWait();
        e = assertThrows(IllegalArgumentException.class, () -> identity.setWait(-5));
        assertEquals("wait duration must not be negative", e.getMessage());
        assertEquals(oldWait, identity.getWait());
    }

    @Test
    public void testEquals() {
        assertEquals("equals itself", identity, identity);
        assertNotEquals("equals different ingredients, different values", identity, noMatch);
        assertEquals("equals different Ingredients, same values", identity, allMatch);
        assertEquals("equals different Ingredients, only name matches", identity, nameMatch);
    }

    @Test
    public void testHashCode() {
        assertEquals("hash itself", identity.hashCode(), identity.hashCode());
        assertNotEquals("hash different ingredients, different values", identity.hashCode(), noMatch.hashCode());
        assertEquals("hash different ingredients, same values", identity.hashCode(), allMatch.hashCode());
        assertEquals("hash different ingredients, only name matches", identity.hashCode(), nameMatch.hashCode());
    }
}
