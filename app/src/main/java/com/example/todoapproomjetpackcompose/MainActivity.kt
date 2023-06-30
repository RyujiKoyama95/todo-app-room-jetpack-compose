package com.example.todoapproomjetpackcompose

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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

@Composable
fun MainContent(viewModel: TodoViewModel) {
    Scaffold(FloatingActionButton(onClick = { /*TODO*/ }) {

    }) {

    }
}