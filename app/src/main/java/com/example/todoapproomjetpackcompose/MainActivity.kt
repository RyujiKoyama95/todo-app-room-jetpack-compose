package com.example.todoapproomjetpackcompose

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapproomjetpackcompose.data.Todo
import com.example.todoapproomjetpackcompose.data.TodoStatus
import com.example.todoapproomjetpackcompose.ui.TodoViewModel
import com.example.todoapproomjetpackcompose.ui.ViewModelFactory
import com.example.todoapproomjetpackcompose.ui.theme.TodoAppRoomJetpackComposeTheme

// AppCompatActivityはフラグメントやアクションバーなどの機能が追加されたComponentActivityのサブクラス
// 上記の機能は必要ないので、ComponentActivityをインプリする
class MainActivity : ComponentActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContent {
            TodoAppRoomJetpackComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // viewModelをviewへ繋ぎこむ
                    // Composable関数の中でviewmodelインスタンスを取得する場合はlifecycle-viewmodel-composeライブラリのviewModel()を使用する
                    val owner = LocalViewModelStoreOwner.current
                    owner?.let {
                        val viewModel = viewModel<TodoViewModel>(
                            it,
                            "TodoViewModel",
                            ViewModelFactory(LocalContext.current.applicationContext as Application)
                        )
                        MainContent(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(viewModel: TodoViewModel) {
    val isShowDialog = remember { mutableStateOf(false) }
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    Scaffold(floatingActionButton =  {
        FloatingActionButton(onClick = { isShowDialog.value = true }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Button")
        }
    }) {
        if (isShowDialog.value) {
            EditDialog(isShowDialog = isShowDialog, viewModel = viewModel)
        }

        val todos = viewModel.todos.value
        if (todos != null) {
            if (todos.isNotEmpty()) {
                title.value = todos[0].title
                description.value = todos[0].description
            }
        }

        Text(text = title.value)
        Text(text = description.value)
    }
}

// Composable関数内で使用できるMutableStateはUI状態を保持するオブジェクト
// MutableState.valueが更新されると自動でComposable関数が再評価される(UIが更新される)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditDialog(isShowDialog: MutableState<Boolean>, viewModel: TodoViewModel) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { isShowDialog.value = false },
        title = { Text(text = "create Todo") },
        text = {
            Column {
                Text(text = "title")
                OutlinedTextField(value = title, onValueChange = { title = it })
                Text(text = "description")
                OutlinedTextField(value = description, onValueChange = { description = it })
            }
        },
        confirmButton = {
            Button(onClick = { isShowDialog.value = false }) {
                Text(text = "OK")
            }
            viewModel.createTodo(
                Todo(title = title, description = description, status = TodoStatus.NOT_COMPLETED)
            )
        },
        dismissButton =  {
            Button(onClick = { isShowDialog.value = false }) {
                Text(text = "Cancel")
            }
        }
    )
}