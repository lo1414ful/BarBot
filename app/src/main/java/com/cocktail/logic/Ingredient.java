package com.cocktail.logic;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Ingredient {

    @NonNull
    private String name;
    private int position;
    private int hold;
    private int wait;
    private boolean activated;

    /**
     * Constructs an Ingredient which has a name, a position, a hold- and a wait duration between pours
     *
     * @param name the name of the Ingredient (must not be null or empty string)
     * @param pos the position
     * @param hold the hold duration (must not be negative)
     * @param wait the wait duration between subsequent pours (must not be negative)
     * @throws IllegalArgumentException if the given durations are negative or if the name is an empty string
     */
    public Ingredient(@NonNull String name, int pos, int hold, int wait) throws IllegalArgumentException {
        Objects.requireNonNull(name, "name must not be null");
        if (name.replace(" ", "").equals("") || hold < 0 || wait < 0) {
            String message = "";
            if (name.replace(" ", "").equals("")) {
                message = message.concat("\r\n  name must not be an empty string");
            }
            if (hold < 0) {
                message = message.concat("\r\n  hold duration must not be negative");
            }
            if (wait < 0) {
                message = message.concat("\r\n  wait duration must not be negative");
            }
            throw new IllegalArgumentException("failed to create Ingredient:" + message);
        }
        this.name = name;
        this.position = pos;
        this.hold = hold;
        this.wait = wait;
        activated = false;
    }


    /**
     * @return the name of the ingredient
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * sets the name of the ingredient
     * @param name the name
     * @throws IllegalArgumentException if the given name is an empty string
     */
    public void setName(@NonNull String name) throws IllegalArgumentException {
        Objects.requireNonNull(name, "name must not be null");
        if (name.replace(" ", "").equals("")) {
            throw new IllegalArgumentException("name must not be empty");
        }
        this.name = name;
    }

    /**
     *
     * @return the position of the ingredient
     */
    public int getPosition() {
        return position;
    }

    /**
     * sets the position of the ingredient
     * @param position the position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return the hold duration of the ingredient
     */
    public int getHold() {
        return hold;
    }

    /**
     * sets the hold duration of the ingredient
     * @param hold the hold duration
     * @throws IllegalArgumentException if the given duration is negative
     */
    public void setHold(int hold) throws IllegalArgumentException {
        if (hold < 0) {
            throw new IllegalArgumentException("hold duration must not be negative");
        }
        this.hold = hold;
    }

    /**
     * @return the wait duration of the ingredient
     */
    public int getWait() {
        return wait;
    }

    /**
     * sets the wait duration of the ingredient
     * @param wait the wait duration
     * @throws IllegalArgumentException if the given duration is negative
     */
    public void setWait(int wait) throws IllegalArgumentException {
        if (wait < 0) {
            throw new IllegalArgumentException("wait duration must not be negative");
        }
        this.wait = wait;
    }

    /**
     * @return true, if this ingredient is active, false otherwise
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * (de-)actives the ingredients
     * @param activated true, if this ingredient should be active, false otherwise
     */
    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !this.getClass().isAssignableFrom(o.getClass())) {
            return false;
        }
        Ingredient i = (Ingredient) o;
        return (i.name.equals(this.name));
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
