package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LostFoundDao {

    @Query("SELECT * FROM lost_found ORDER BY id DESC")
    fun getAllItems(): LiveData<List<LostFoundItem>>

    @Query("SELECT * FROM lost_found WHERE id = :id LIMIT 1")
    suspend fun getItemById(id: Int): LostFoundItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: LostFoundItem)

    @Update
    suspend fun update(item: LostFoundItem)

    @Delete
    suspend fun delete(item: LostFoundItem)
}
