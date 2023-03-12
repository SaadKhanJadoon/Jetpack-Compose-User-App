package com.example.composedemo.utils

import com.example.composedemo.model.UserModel

sealed class ApiResult {
    class Success(val userModel: UserModel) : ApiResult()
    object Error : ApiResult()
    object Loading : ApiResult()
}