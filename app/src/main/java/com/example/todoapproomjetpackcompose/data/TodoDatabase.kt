package com.example.todoapproomjetpackcompose.data

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase(): RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        var INSTANSE: TodoDatabase? = null

        fun createDb(context: Context): TodoDatabase {
            val instanse = INSTANSE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()
            }
            INSTANSE = instanse
            return instanse
        }
    }
}