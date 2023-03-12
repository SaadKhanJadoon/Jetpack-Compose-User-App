package com.example.composedemo.repo

import com.example.composedemo.api.ApiService
import javax.inject.Inject

class UserRepository @Inject constructor(private var apiService: ApiService) {

    suspend fun getAllUser() = apiService.getAllUsers()
}