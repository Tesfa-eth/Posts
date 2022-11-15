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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
                PostView(vm)
            }
        }
    }
}

@Composable
fun PostView(vm: PostViewModel) {
    // To call suspend function safely inside composeable, use LaunchedEffectComposable
    LaunchedEffect(key1 = Unit, block = {
        vm.getPostList()
    })
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = "Posts")
                    }
                })
        },
        content = {
            if (vm.errorMessage.isEmpty()){
                Column(modifier = Modifier.padding(16.dp)) {
                    LazyColumn(modifier = Modifier.fillMaxHeight()){
                        items(vm.postList){post ->
                            CardViewRow(post)
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
fun CardViewRow(post: Post){
    Column{
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            PostCard(post = post)

            Spacer(modifier = Modifier.width(10.dp))
        }

        
    }
}

@Composable
fun PostCard(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    //append("User ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                    ) {
                        append("User ${post.id.toString()}")
                    }
                }
            )
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                        append(post.title)
                    }
                }
            )
            Text(text = post.body)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ViewModelsAndApiTutorialTheme {
    }
}