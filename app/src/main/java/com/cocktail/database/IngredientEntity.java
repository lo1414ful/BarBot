package com.cocktail.database;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;



import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Entity
public class IngredientEntity {
    @PrimaryKey(autoGenerate = true)
    long uid = 0;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "position")
    private int position;
    @ColumnInfo(name = "hold")
    private int hold;
    @ColumnInfo(name = "wait")
    private int wait;
    @ColumnInfo(name = "active")
    private boolean active;



    IngredientEntity() {}


    /**
     * Constructs an Ingredient which has a name, a position, a hold- and a wait duration between pours
     *
     * @param name the name of the Ingredient (must not be null or empty string)
     * @param position the position
     * @param hold the hold duration (must not be negative)
     * @param wait the wait duration between subsequent pours (must not be negative)
     * @throws IllegalArgumentException if the given durations are negative or if the name is an empty string
     */
    @Ignore
    private IngredientEntity(@NonNull String name, int position, int hold, int wait) throws IllegalArgumentException {
        this.name = name;
        this.position = position;
        this.hold = hold;
        this.wait = wait;
        active = false;
    }

    public static LiveData<IngredientEntity> createIngredient(@NonNull String name, int position, int hold, int wait) throws IllegalArgumentException {
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
        IngredientEntity entity = new IngredientEntity(name, position, hold, wait);
        Callable<Long> callable = new Callable<Long>() {
            @Override
            public Long call() {
                return BBDatabase.getSingleton(null).ingredientDao().insert(entity);
            }
        };
        Future<Long> f = Executors.newSingleThreadExecutor().submit(callable);

        try {
            entity.uid = f.get();
        } catch (ExecutionException | InterruptedException e) {
            // TODO;
        }
        return BBDatabase.getSingleton(null).ingredientDao().getByUid(entity.uid);
    }


    public long getUid() {
        return uid;
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
    public boolean isActive() {
        return active;
    }

    /**
     * (de-)actives the ingredients
     * @param active true, if this ingredient should be active, false otherwise
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !this.getClass().isAssignableFrom(o.getClass())) {
            return false;
        }
        IngredientEntity i = (IngredientEntity) o;
        return (i.getUid() != 0 && i.getUid() == this.getUid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid());
    }

}
