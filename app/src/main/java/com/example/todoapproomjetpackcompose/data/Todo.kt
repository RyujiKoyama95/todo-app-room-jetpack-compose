package com.example.todoapproomjetpackcompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val status: TodoStatus
)

enum class TodoStatus {
    COMPLETED,
    NOT_COMPLETED
}