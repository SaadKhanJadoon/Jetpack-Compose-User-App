package com.example.composedemo.viewmodel

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedemo.model.User
import com.example.composedemo.model.UserModel
import com.example.composedemo.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val isLoading = mutableStateOf(false)
    private val _userData = MutableStateFlow<List<UserModel>>(emptyList())
    val userData = _userData.asStateFlow()

    //Detail Screen variable
    var user by mutableStateOf<User?>(null)
        private set

//    init {
//        getAllUser()
//    }

    fun getAllUser() = viewModelScope.launch {
        isLoading.value = true
        val fetchUserListEntity = repository.getAllUser()
        _userData.value = listOf(fetchUserListEntity)
    }.invokeOnCompletion {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            isLoading.value = false
        }, 3000)
    }

    fun addUser(newUser: User) {
        user = newUser
    }
}