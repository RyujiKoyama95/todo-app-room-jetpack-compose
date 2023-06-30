package com.example.todoapproomjetpackcompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    suspend fun observeAll(): List<Todo>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)
    @Update
    suspend fun update(todo: Todo)
    @Delete
    suspend fun delete(todo: Todo)
}