package com.example.todoapproomjetpackcompose.data

import android.content.Context

class TodoRepository(context: Context) {
    private val db = TodoDatabase.createDb(context)
    private val dao = db.todoDao()

    suspend fun getTodoList(): List<Todo> {
        return dao.observeAll()
    }

    suspend fun createTodo(todo: Todo) {
        dao.insert(todo)
    }

    suspend fun delete(todo: Todo) {
        dao.delete(todo)
    }
}