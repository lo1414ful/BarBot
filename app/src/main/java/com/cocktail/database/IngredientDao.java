package com.cocktail.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IngredientDao {


    @Insert(onConflict = OnConflictStrategy.ABORT)
    long insert(IngredientEntity i);

    @Query("SELECT * FROM IngredientEntity WHERE UID = :uid")
    LiveData<IngredientEntity> getByUid(long uid);

    @Query("SELECT * FROM IngredientEntity")
    LiveData<List<IngredientEntity>> getAll();

}
