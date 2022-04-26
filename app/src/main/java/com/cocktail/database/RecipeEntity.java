package com.cocktail.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RecipeEntity {
    @PrimaryKey
    public int uid;
}
