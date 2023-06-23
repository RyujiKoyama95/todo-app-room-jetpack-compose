package com.example.todoapproomjetpackcompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun observeAll(): List<Todo>
    @Insert
    fun insert(todo: Todo)
    @Delete
    fun delete(todo: Todo)
}