package com.chrissloan.paw_some.presentation.screen.breeddetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DrawerValue
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chrissloan.paw_some.domain.entity.ImageDomainEntity
import com.chrissloan.paw_some.presentation.R
import com.chrissloan.paw_some.presentation.common.LoadingView
import com.chrissloan.paw_some.presentation.screen.breeddetail.BreedDetailViewModel.BreedDetailAction.ErrorMessageShown
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun BreedDetailScreen(
    breedId: String,
    viewModel: BreedDetailViewModel = getViewModel(parameters = { parametersOf(breedId) })
) {
    val uiState = viewModel.uiState
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    if (uiState.errorMessage != null) {
        LaunchedEffect(key1 = uiState.errorMessage) {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(message = uiState.errorMessage)
                viewModel.handleAction(ErrorMessageShown)
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = uiState.breed?.name ?: "Pawsome",
                        color = MaterialTheme.colorScheme.onSecondary,
                        style = MaterialTheme.typography.headlineMedium,
                    )
                },
                backgroundColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch { scaffoldState.drawerState.open() }
                        }
                    ) {
                        Icon(Icons.Filled.Info, "Breed Info")
                    }
                }
            )
        },
        drawerContent = { Text(text = "${uiState.breed}") },
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) {
            if (uiState.isLoading) {
                println("<<<<< - IsLoading")
                LoadingView()
            } else {
                BreedImages(
                    images = { uiState.images },
                    clickListener = { println("<<<<< - $it") },
                    breedName = uiState.breed?.name.orEmpty()
                )
            }
        }
    }
}

@Composable
fun BreedImages(
    images: () -> List<ImageDomainEntity>,
    clickListener: (ImageDomainEntity) -> Unit,
    breedName: String
) {
    val scrollState = rememberLazyListState(initialFirstVisibleItemScrollOffset = 0)

    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp),
        state = scrollState,
    ) {
        items(items = images()) { image ->
            Card(
                backgroundColor = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .clickable { clickListener(image) },
                elevation = 8.dp
            ) {
                CardContent(image, breedName)
            }
        }
    }
}

@Composable
private fun CardContent(
    image: ImageDomainEntity,
    breedName: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image.url)
                .crossfade(true)
                .build(),
            contentDescription = "Image of $breedName",
            placeholder = painterResource(R.drawable.image_placeholder),
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(size = 12.dp))
        )
    }
}
