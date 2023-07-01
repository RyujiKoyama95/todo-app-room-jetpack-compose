package com.example.todoapproomjetpackcompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val status: TodoStatus = TodoStatus.NOT_COMPLETED
)

enum class TodoStatus {
    COMPLETED,
    NOT_COMPLETED
}