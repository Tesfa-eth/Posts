package com.example.viewmodelsandapitutorial.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viewmodelsandapitutorial.api.ApiService
import com.example.viewmodelsandapitutorial.api.Post
import kotlinx.coroutines.launch
import java.lang.Exception

class PostViewModel : ViewModel() {
    private val _postList = mutableStateListOf<Post>()
    var errorMessage: String by mutableStateOf("")
    val postList: List<Post>
        get() = _postList

    fun getTodoList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                _postList.clear()
                _postList.addAll(apiService.getPost())
            } catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}