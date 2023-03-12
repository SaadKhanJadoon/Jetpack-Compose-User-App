package com.example.composedemo.api

import com.example.composedemo.model.UserModel
import com.example.composedemo.utils.Constants
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.BASE_URL)
    suspend fun getAllUsers(
    ): UserModel

}