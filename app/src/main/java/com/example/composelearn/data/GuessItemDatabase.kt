package com.example.composelearn.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GuessItem::class], version = 1, exportSchema = false)
abstract class GuessItemDatabase : RoomDatabase() {

    abstract fun guessItemDao(): GuessItem

    companion object {
        @Volatile
        private var Instance: GuessItemDatabase? = null

        fun getDatabase(context: Context): GuessItemDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, GuessItemDatabase::class.java, "item_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}