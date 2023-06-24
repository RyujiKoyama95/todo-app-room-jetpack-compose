package com.example.todoapproomjetpackcompose.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapproomjetpackcompose.data.Todo
import com.example.todoapproomjetpackcompose.data.TodoRepository
import com.example.todoapproomjetpackcompose.data.TodoStatus
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: TodoRepository
    ): ViewModel() {

    // dummy data insert
    init {
        val dummyData = Todo(1,"dummy", TodoStatus.NOT_COMPLETED)
        createTodo(dummyData)
    }

    var todoList = listOf<Todo>()

    fun getTodoList() {
        viewModelScope.launch {
            todoList = repository.getTodoList()
        }
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