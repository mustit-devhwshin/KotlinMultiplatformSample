package com.example.kotlinmultiplatformsample.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlinmultiplatformsample.GithubRepositoriesModel
import com.example.kotlinmultiplatformsample.GithubRepositoryModel
import com.example.kotlinmultiplatformsample.Repository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SampleView()
                }
            }
        }
    }
}

@Composable
fun SampleView() {
    val repository = Repository()
    var githubRepositories: GithubRepositoriesModel? by remember { mutableStateOf(null) }
    var savedString by remember { mutableStateOf(repository.getSavedString()) }
    LaunchedEffect(Unit) {
        githubRepositories = repository.getGithubRepositories()
    }
    LazyColumn(
        Modifier.fillMaxSize()
    ) {
        item {
            EditSavedString(savedString = savedString) {
                repository.saveString(it)
                savedString = repository.getSavedString()
            }
        }
        items(githubRepositories?.items?.size ?: 0) { index ->
            githubRepositories?.items?.get(index)?.let {
                GithubRepositoryItemView(it)
            }
        }
    }
}


@Composable
fun EditSavedString(savedString: String, onSaveNewString: (String) -> Unit) {
    var newString by remember { mutableStateOf(savedString) }
    Column {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Saved String",
            color = Color.DarkGray,
            fontSize = 24.sp
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            value = newString,
            onValueChange = { newString = it }
        )
        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color.LightGray),
            onClick = { onSaveNewString(newString) }
        ) {
            Text(text = "save", color = Color.DarkGray, fontSize = 16.sp)
        }
    }
}

@Composable
fun GithubRepositoryItemView(item: GithubRepositoryModel) {
    Column(Modifier.padding(8.dp)) {
        Text(text = item.fullName, color = Color.DarkGray, fontSize = 16.sp)
        item.description?.let { Text(text = it, color = Color.Gray, fontSize = 12.sp) }
    }
}
