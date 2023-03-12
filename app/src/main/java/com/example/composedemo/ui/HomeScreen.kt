package com.example.composedemo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.composedemo.model.User
import com.example.composedemo.theme.fontLexend
import com.example.composedemo.utils.Screen
import com.example.composedemo.utils.getUsername
import com.example.composedemo.viewmodel.UserViewModel

@Composable
fun HomeScreenPreview(
    navController: NavHostController, viewModel: UserViewModel
) {
    val isLoading = viewModel.isLoading.value
    //Creating a variable for the StateFlow variable
    val userList = viewModel.userData.collectAsState().value

    if (isLoading) {
        ProgressBarComponent()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            userList.forEach { user ->
                items(user.results.size) {
                    UserRow(
                        user = user.results[it],
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}

//List Item view
@Composable
fun UserRow(user: User, viewModel: UserViewModel, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(5.dp, 4.dp, 5.dp, 4.dp)
            .fillMaxWidth()
            .clickable(indication = rememberRipple(bounded = true),
                interactionSource = remember { MutableInteractionSource() }) {
                viewModel.addUser(user)
                navController.navigate(Screen.Details.route)
            },
        shape = RoundedCornerShape(CornerSize(5.dp)),
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = user.picture.large),
                contentDescription = "image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(CornerSize(10.dp)))
            )

            Column(
                modifier = Modifier
                    .padding(10.dp, 5.dp, 0.dp, 5.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = user.name.getUsername(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.subtitle1,
                    fontFamily = fontLexend
                )

                Text(
                    text = user.email,
                    maxLines = 1,
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.caption,
                    fontFamily = fontLexend
                )

                Text(
                    text = user.phone,
                    maxLines = 1,
                    modifier = Modifier.padding(5.dp),
                    style = MaterialTheme.typography.caption,
                    fontFamily = fontLexend
                )
            }
        }
    }
}

