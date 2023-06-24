package com.example.todoapproomjetpackcompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    suspend fun observeAll(): List<Todo>
    @Insert
    suspend fun insert(todo: Todo)
    @Delete
    suspend fun delete(todo: Todo)
}