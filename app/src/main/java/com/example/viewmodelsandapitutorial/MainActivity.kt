package com.example.viewmodelsandapitutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.viewmodelsandapitutorial.api.Post
import com.example.viewmodelsandapitutorial.ui.theme.ViewModelsAndApiTutorialTheme
import com.example.viewmodelsandapitutorial.viewmodel.PostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //get the view model before on create
        val vm = PostViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelsAndApiTutorialTheme {
                TodoView(vm)
            }
        }
    }
}

@Composable
fun TodoView(vm: PostViewModel) {
    // To call suspend function safely inside composeable, use LaunchedEffectComposable
    LaunchedEffect(key1 = Unit, block = {
        vm.getTodoList()
    })
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = "Todos")
                    }
                })
        },
        content = {
            if (vm.errorMessage.isEmpty()){
                Column(modifier = Modifier.padding(16.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()){
                        items(vm.postList){post ->
                            CardView(post)
                        }
                    }
                }
            }
            else{
                Text(text = vm.errorMessage)
            }
        }
    )
}

@Composable
fun CardView(post: Post){
    Column{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 16.dp, 0.dp)
            ){
                Text(text = post.title, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }

            Spacer(modifier = Modifier.width(16.dp))

            //Checkbox(checked = todo.completed, onCheckedChange = null)
        }

        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewModelsAndApiTutorialTheme {
        val vm = PostViewModel()
        TodoView(vm = vm)
    }
}