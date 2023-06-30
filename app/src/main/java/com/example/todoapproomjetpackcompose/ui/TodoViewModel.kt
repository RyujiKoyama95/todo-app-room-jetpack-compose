package com.example.todoapproomjetpackcompose.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private var _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        Log.d(TAG, "init")
        val dao = TodoDatabase.createDb(application).todoDao()
        repository = TodoRepository(dao)
    }

    private fun getTodoList() {
        viewModelScope.launch {
            _todos.postValue(repository.getTodoList())
        }
    }

    fun createTodo(todo: Todo) {
        Log.d(TAG, "createTodo")
        viewModelScope.launch {
            repository.createTodo(todo)
            getTodoList()
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
            getTodoList()
        }
    }
}