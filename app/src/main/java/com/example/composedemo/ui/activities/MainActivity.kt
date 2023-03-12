package com.example.composedemo.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.R
import com.example.composedemo.navigation.SetupNavGraph
import com.example.composedemo.utils.showToast
import com.example.composedemo.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            viewModel.getAllUser()
            ShowToolbar(viewModel = viewModel, context = this)
        }
    }

    //Toolbar Layout
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun ShowToolbar(viewModel: UserViewModel, context: Context) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(text = getString(R.string.app_name))
                }, navigationIcon = {
                    Image(painter = painterResource(id = R.drawable.ic_drawer_menu),
                        contentDescription = "image",
                        modifier = Modifier
                            .padding(15.dp, 0.dp, 0.dp, 0.dp)
                            .size(20.dp, 30.dp)
                            .clickable(indication = rememberRipple(
                                bounded = false, color = Color.Black
                            ), interactionSource = remember { MutableInteractionSource() }) {
                                this@MainActivity.showToast("Drawer Menu Clicked")
                            })
                })
            },
        ) {
            SetupNavGraph(
                navController = rememberNavController(),
                viewModel = viewModel,
                context = context
            )
        }
    }
}