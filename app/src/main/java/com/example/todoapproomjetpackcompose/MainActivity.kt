package com.example.todoapproomjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapproomjetpackcompose.data.Todo
import com.example.todoapproomjetpackcompose.data.TodoDao
import com.example.todoapproomjetpackcompose.data.TodoDatabase
import com.example.todoapproomjetpackcompose.data.TodoRepository
import com.example.todoapproomjetpackcompose.data.TodoStatus
import com.example.todoapproomjetpackcompose.ui.TodoViewModel
import com.example.todoapproomjetpackcompose.ui.theme.TodoAppRoomJetpackComposeTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

//    val viewModel: TodoViewModel by viewModels {TodoViewModel.factory}
    lateinit var dao: TodoDao
    lateinit var todoList: MutableList<Todo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppRoomJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Log.d(TAG, "onCreate")
                    dao = TodoDatabase.createDb(applicationContext).todoDao()
                    todoList = mutableListOf<Todo>()

                    val dummyData = Todo(title = "dummy data", status = TodoStatus.NOT_COMPLETED)
                    createTodo(dummyData)

                    Log.d(TAG, "onCreate todoList=$todoList")
//                    val text = todoList[0].title
                    val text = "test"

                    Greeting(text)
                }
            }
        }
    }
    private fun getTodoList() {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                Log.d(TAG, "getTodoList start")
                dao.observeAll().forEach {
                    todoList.add(it)
                }
                Log.d(TAG, "getTodoList todoList=$todoList")
                Log.d(TAG, "getTodoList end")
            }
        }
    }

    private fun createTodo(todo: Todo) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                Log.d(TAG, "createTodo start")
                // Todo: daoのAPIが呼べていなさそう
                dao.insert(todo)
                getTodoList()
                Log.d(TAG, "createTodo end")
            }
        }
    }

    private fun delete(todo: Todo) {
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default) {
                dao.delete(todo)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoAppRoomJetpackComposeTheme {
        Greeting("Android")
    }
}