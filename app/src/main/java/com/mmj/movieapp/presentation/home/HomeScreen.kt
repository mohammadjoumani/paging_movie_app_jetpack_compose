package com.mmj.movieapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mmj.movieapp.R
import com.mmj.movieapp.core.app.AppPreferences
import com.mmj.movieapp.domain.model.Movie
import com.mmj.movieapp.presentation.home.component.ItemMovie
import com.mmj.movieapp.presentation.main.MainEvent
import com.mmj.movieapp.presentation.main.MainViewModel
import com.mmj.movieapp.presentation.util.ErrorMessage
import com.mmj.movieapp.presentation.util.LoadingNextPageItem
import com.mmj.movieapp.presentation.util.PageLoader
import com.mmj.movieapp.presentation.util.resource.route.AppScreen
import com.mmj.movieapp.presentation.util.resource.theme.AppTheme

@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val moviePagingItems: LazyPagingItems<Movie> = viewModel.moviesState.collectAsLazyPagingItems()
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.primary),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(25.dp)
                    )
                }

                Text(
                    text = stringResource(id = R.string.app_name),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                        .weight(1.0f),
                    textAlign = TextAlign.Center
                )

                IconButton(
                    onClick = {
                        if (mainViewModel.stateApp.theme == AppTheme.Light) {
                            AppPreferences.setTheme(AppTheme.Dark)
                            mainViewModel.onEvent(MainEvent.ThemeChange(AppTheme.Dark))
                        } else {
                            AppPreferences.setTheme(AppTheme.Light)
                            mainViewModel.onEvent(MainEvent.ThemeChange(AppTheme.Light))
                        }
                    }
                ) {
                    Icon(
                        painter = if (mainViewModel.stateApp.theme == AppTheme.Light)
                            painterResource(id = R.drawable.ic_dark_mode)
                        else
                            painterResource(id = R.drawable.ic_light_mode),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            item { Spacer(modifier = Modifier.padding(4.dp)) }
            items(moviePagingItems.itemCount) { index ->
                ItemMovie(
                    itemEntity = moviePagingItems[index]!!,
                    onClick = {
                        navController.navigate(AppScreen.DetailsScreen.route)
                    }
                )
            }
            moviePagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = moviePagingItems.loadState.refresh as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier.fillParentMaxSize(),
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingNextPageItem(modifier = Modifier) }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = moviePagingItems.loadState.append as LoadState.Error
                        item {
                            ErrorMessage(
                                modifier = Modifier,
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.padding(4.dp)) }
        }
    }
}