package com.mmj.movieapp.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mmj.movieapp.presentation.util.resource.route.NavGraph
import com.mmj.movieapp.presentation.util.resource.theme.AppTheme
import com.mmj.movieapp.presentation.util.resource.theme.MovieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieTheme(appTheme = viewModel.stateApp.theme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(viewModel)
                }
            }
        }
    }
}