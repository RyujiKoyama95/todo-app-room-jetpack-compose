package com.example.todoapproomjetpackcompose.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.todoapproomjetpackcompose.data.Todo
import com.example.todoapproomjetpackcompose.data.TodoRepository
import com.example.todoapproomjetpackcompose.data.TodoStatus
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: TodoRepository
    ): ViewModel() {
    companion object {
        const val TAG = "TodoViewModel"
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val context = checkNotNull(extras[APPLICATION_KEY])
                val repository = TodoRepository(context)
                return TodoViewModel(repository) as T
            }
        }
    }

    // dummy data insert
    init {
        Log.d(TAG, "init")
        val dummyData = Todo(1,"dummy", TodoStatus.NOT_COMPLETED)
        createTodo(dummyData)
    }

    var todoList = listOf<Todo>()

    fun getTodoList() {
        viewModelScope.launch {
            todoList = repository.getTodoList()
            Log.d(TAG, "1 $todoList")
        }
        Log.d(TAG, "2 $todoList")
    }

    fun createTodo(todo: Todo) {
        viewModelScope.launch {
            repository.createTodo(todo)
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch {
            repository.delete(todo)
        }
    }
}