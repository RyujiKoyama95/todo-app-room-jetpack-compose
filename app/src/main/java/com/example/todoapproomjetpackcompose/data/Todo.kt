package com.example.todoapproomjetpackcompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey val id: Int,
    val title: String,
    val status: TodoStatus
)

enum class TodoStatus {
    COMPLETED,
    NOT_COMPLETED
}