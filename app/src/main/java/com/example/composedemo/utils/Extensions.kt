package com.example.composedemo.utils

import android.content.Context
import android.widget.Toast
import com.example.composedemo.model.Location
import com.example.composedemo.model.Name

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Name.getUsername(): String {
    return "${this?.title + " " + (this?.first) + " " + (this?.last)}"
}

fun Location.getUserLocation(): String {
    return "${this?.street?.number.toString() + " " + this?.street?.name + " " + (this?.city) + " " + (this?.state) + " " + (this?.country)}"
}