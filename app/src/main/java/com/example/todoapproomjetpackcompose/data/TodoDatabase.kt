package com.example.todoapproomjetpackcompose.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoDatabase(): RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var INSTANCE: TodoDatabase? = null

        fun createDb(context: Context): TodoDatabase {
            val instance = INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    TodoDatabase::class.java,
                    "todo_database"
                ).build()
            }
            INSTANCE = instance
            return instance
        }
    }
}