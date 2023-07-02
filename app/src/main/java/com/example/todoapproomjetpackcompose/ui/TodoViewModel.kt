package com.example.todoapproomjetpackcompose.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapproomjetpackcompose.OnCreateTodoListener
import com.example.todoapproomjetpackcompose.data.Todo
import com.example.todoapproomjetpackcompose.data.TodoDatabase
import com.example.todoapproomjetpackcompose.data.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(
    private val application: Application
    ): ViewModel() {
    companion object {
        const val TAG = "TodoViewModel"
    }
    private val repository: TodoRepository
    private var listener: OnCreateTodoListener? = null
    private var _todos = MutableLiveData<List<Todo>>()
    var todos: LiveData<List<Todo>> = _todos

    init {
        Log.d(TAG, "init")
        val dao = TodoDatabase.createDb(application).todoDao()
        repository = TodoRepository(dao)
    }

    fun getTodoList() {
        viewModelScope.launch {
            val list = repository.getTodoList()
            _todos.value = list
            Log.d(TAG, "todos=${todos.value}")
            Log.d(TAG, "_todos=${_todos.value}")

            listener?.notifyCreateTodo()
        }
    }

    fun addTodo(title: String, description: String) {
        viewModelScope.launch {
            val todo = Todo(title = title, description = description)
            createTodo(todo)
        }
    }

    fun createTodo(todo: Todo) {
        Log.d(TAG, "createTodo")
        viewModelScope.launch {
            repository.createTodo(todo)
            getTodoList()
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            repository.updateTodo(todo)
            getTodoList()
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
            getTodoList()
        }
    }

    fun setListener(listener: OnCreateTodoListener) {
        this.listener = listener
    }
}