package com.example.todoapproomjetpackcompose.data

import android.util.Log

class TodoRepository(private val dao: TodoDao) {
    companion object {
        const val TAG = "TodoRepository"
    }

    suspend fun getTodoList(): List<Todo> {
        Log.d(TAG, "getTodoList")
        return dao.observeAll()
    }

    suspend fun createTodo(todo: Todo) {
        Log.d(TAG, "createTodo")
        // Todo: daoのAPIが呼べていなさそう
        dao.insert(todo)
    }

    suspend fun delete(todo: Todo) {
        dao.delete(todo)
    }
}