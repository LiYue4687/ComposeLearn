package com.example.composelearn.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GuessItemDao {

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<GuessItem>>

    @Query("SELECT * from items ORDER BY RANDOM() LIMIT 10")
    fun get10RandomItems(): Flow<List<GuessItem>>

    // OnConflict strategy constant to abort the transaction. The transaction is rolled back.
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: GuessItem)

}