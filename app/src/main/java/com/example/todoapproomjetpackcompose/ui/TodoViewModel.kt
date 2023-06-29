package com.example.todoapproomjetpackcompose.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.todoapproomjetpackcompose.data.Todo
import com.example.todoapproomjetpackcompose.data.TodoDatabase
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
                val dao = TodoDatabase.createDb(context).todoDao()
                val repository = TodoRepository(dao)
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

    private var _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    fun getTodoList() {
        viewModelScope.launch {
            _todos.postValue(repository.getTodoList())
            Log.d(TAG, "getTodoList _todos=$_todos")
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
        }
    }
}