package com.cocktail.logic;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Ingredient {

    private String name;
    private int position;
    private int hold;
    private int wait;
    private boolean activated;

    public Ingredient(@NonNull String name, int pos, int hold, int wait) throws IllegalArgumentException {
        if (name.equals("") || hold < 0 || wait < 0) {
            String message = "";
            if (name.equals("")) {
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

    public void setName(String name) {
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

    public void setHold(int hold) {
        this.hold = hold;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
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
