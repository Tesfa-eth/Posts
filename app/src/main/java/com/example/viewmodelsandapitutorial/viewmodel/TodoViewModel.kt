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

class TodoViewModel : ViewModel() {
    private val _todoList = mutableStateListOf<Post>()
    var errorMessage: String by mutableStateOf("")
    val todoList: List<Post>
        get() = _todoList

    fun getTodoList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                _todoList.clear()
                _todoList.addAll(apiService.getTodos())
            } catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}