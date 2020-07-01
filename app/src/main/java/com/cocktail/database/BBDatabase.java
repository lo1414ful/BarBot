package com.cocktail.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cocktail.database.daos.IngredientDao;
import com.cocktail.database.daos.RecipeDao;
import com.cocktail.database.daos.RecipeIngredientDao;
import com.cocktail.database.entities.IngredientEntity;
import com.cocktail.database.entities.RecipeEntity;
import com.cocktail.database.entities.RecipeIngredientJoin;

/**
 * This class is for accessing the Barbot Database where all ingredients, recipes etc. are stored.
 * It follows the singleton-design-pattern as only one instance of an access-object is needed.
 * @see #getSingleton(Context)
 */
@Database(entities = {IngredientEntity.class, RecipeEntity.class, RecipeIngredientJoin.class}, version = 0, exportSchema = true)
public abstract class BBDatabase extends RoomDatabase {

    private final static String DB_NAME = "Cookbook";
    private final static Object DB_SYNC = new Object();
    private static BBDatabase singleton;


    //FIXME (???)
/*
    @NonNull
    @Override
    protected abstract SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config);

    @NonNull
    @Override
    protected abstract InvalidationTracker createInvalidationTracker();
*/
    @Override
    public void clearAllTables() {
        //FIXME
    }

    /**
     * this is the access-method for the BBDatabase-singleton. If the singleton has not yet been
     * initialized, it gets initialized with the given context. For this reason, it is no problem to
     * pass 'null' as context as long as the singleton was initialized before. if it was not,
     * passing 'null' will result in a NullPointerException as it tries to initialize the BBDatabase
     * without context.
     *
     * This method is thread-save, as the initialization of the singleton is synchronized.
     *
     * @param context the App-Context, which is required for initialization of the singleton-object,
     *               can be null if already initialized.
     * @return the BBDatabase-singleton-object
     * @throws NullPointerException when the given context is 'null' and the singleton isn't
     * initialized yet.
     */
    @NonNull
    public static BBDatabase getSingleton(@Nullable Context context) {
        if (singleton == null) {
            synchronized(DB_SYNC) {
                if (singleton == null) {
                    if (context == null) throw new NullPointerException("cannot initialize the" +
                            " BBDatabase without context");
                    singleton = Room.databaseBuilder(context, BBDatabase.class, DB_NAME)
                            .enableMultiInstanceInvalidation().build();
                }
            }
        }
        return singleton;
    }


    public abstract IngredientDao ingredientDao();
    public abstract RecipeDao recipeDao();
    public abstract RecipeIngredientDao recipeIngredientDao();
}
