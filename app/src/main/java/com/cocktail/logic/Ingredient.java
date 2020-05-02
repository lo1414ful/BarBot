package com.cocktail.logic;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Ingredient {

    private String name;
    private int position;
    private int hold;
    private int wait;
    private boolean activated;

    /**
     * Constructor for a new Ingredient
     * @param name the name of the Ingredient (must not be null or empty string)
     * @param pos the position
     * @param hold the hold duration
     * @param wait the wait duration between subsequent pours
     * @throws IllegalArgumentException if the given durations are negative or if the name is empty
     */
    public Ingredient(@NonNull String name, int pos, int hold, int wait) throws IllegalArgumentException {
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


    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) throws IllegalArgumentException {
        if (name.replace(" ", "").equals("")) {
            throw new IllegalArgumentException("name must not be empty");
        }
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getHold() {
        return hold;
    }

    public void setHold(int hold) throws IllegalArgumentException {
        if (hold < 0) {
            throw new IllegalArgumentException("hold duration must not be negative");
        }
        this.hold = hold;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) throws IllegalArgumentException {
        if (wait < 0) {
            throw new IllegalArgumentException("wait duration must not be negative");
        }
        this.wait = wait;
    }


    public boolean isActivated() {
        return activated;
    }

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
