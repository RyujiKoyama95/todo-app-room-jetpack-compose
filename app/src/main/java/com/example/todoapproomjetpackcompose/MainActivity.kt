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

    private val viewModel: TodoViewModel by viewModels {TodoViewModel.factory}

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

                    val dummyData = Todo(title = "dummy data", status = TodoStatus.NOT_COMPLETED)
                    viewModel.getTodoList()
                    Log.d(TAG, "onCreate step1")
                    Thread.sleep(15000)

                    Log.d(TAG, "onCreate step2")
                    Log.d(TAG, "onCreate todoList=${viewModel.todoList}")
//                    val text = viewModel.todoList[0].title
                    val text = "test"

                    Greeting(text)
                }
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