package com.example.composedemo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composedemo.theme.Purple200

@Composable
fun ProgressBarComponent() {
    // CircularProgressIndicator is generally used
    // at the isLoading screen and it indicates that
    // some progress is going on so please wait.
    Column(
        // we are using column to align our
        // imageview to center of the screen.
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),

        // below line is used for specifying
        // vertical arrangement.
        verticalArrangement = Arrangement.Center,

        // below line is used for specifying
        // horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        // below line is use to display
        // a circular progress bar.
        CircularProgressIndicator(
            // below line is use to add padding
            // to our progress bar.
            modifier = Modifier.padding(16.dp),

            // below line is use to add color
            // to our progress bar.
            color = Purple200,

            // below line is use to add stroke
            // width to our progress bar.
            strokeWidth = Dp(value = 4F)
        )
    }
}