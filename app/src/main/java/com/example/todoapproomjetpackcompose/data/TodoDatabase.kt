package com.example.todoapproomjetpackcompose.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 2, exportSchema = false)
abstract class TodoDatabase(): RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private const val TAG = "TodoDatabase"
        private var INSTANCE: TodoDatabase? = null

        fun createDb(context: Context): TodoDatabase {
            Log.d(TAG, "createDb")
            val instance = INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    TodoDatabase::class.java,
                    "todo_database"
                ).fallbackToDestructiveMigration().build()
            }
            INSTANCE = instance
            return instance
        }
    }
}