package com.example.todoapproomjetpackcompose.data

import android.content.Context
import android.util.Log

class TodoRepository(context: Context) {
    companion object {
        const val TAG = "TodoRepository"
    }

    private val dao = TodoDatabase.createDb(context).todoDao()

    suspend fun getTodoList(): List<Todo> {
        Log.d(TAG, "getTodoList")
        return dao.observeAll()
    }

    suspend fun createTodo(todo: Todo) {
        Log.d(TAG, "createTodo")
        dao.insert(todo)
    }

    suspend fun delete(todo: Todo) {
        dao.delete(todo)
    }
}