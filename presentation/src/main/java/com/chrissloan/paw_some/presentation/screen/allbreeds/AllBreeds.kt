package com.chrissloan.paw_some.presentation.screen.allbreeds

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.DrawerValue
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chrissloan.paw_some.domain.entity.BreedDomainEntity
import com.chrissloan.paw_some.presentation.R
import com.chrissloan.paw_some.presentation.common.LoadingView
import com.chrissloan.paw_some.presentation.common.PawsomeAppBar
import com.chrissloan.paw_some.presentation.screen.allbreeds.AllBreedsViewModel.AllBreedsAction.ErrorMessageShown
import com.chrissloan.paw_some.presentation.screen.allbreeds.AllBreedsViewModel.AllBreedsNavigationEvent
import com.chrissloan.paw_some.presentation.screen.allbreeds.AllBreedsViewModel.AllBreedsNavigationEvent.ShowBreed
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun AllBreedsScreen(
    onBreedSelected: (BreedDomainEntity) -> Unit,
    viewModel: AllBreedsViewModel = getViewModel()
) {
    val uiState = viewModel.uiState
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    if (uiState.navigationEvent != null) {
        viewModel.navigationEventComplete()
        HandleNavigationEvent(
            navigationEvent = uiState.navigationEvent,
            onBreedSelected = onBreedSelected
        )
    }

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
            PawsomeAppBar(
                title = { "Pawsome" },
                navIconVector = { Icons.Filled.Settings },
                navIconContentDescription = { "Filters Drawer" },
                onNavIconClicked = { { scope.launch { scaffoldState.drawerState.open() } } },
                actionIcon = { }
            )
        },
        drawerContent = { Text(text = "drawerContent") },
    ) {
        Surface(modifier = Modifier.padding(it)) {
            if (uiState.isLoading) {
                LoadingView()
            } else {
                AllBreedsList(
                    breeds = { uiState.breeds },
                    clickListener = viewModel::handleBreedSelectionAction
                )
            }
        }
    }
}

@Composable
fun AllBreedsList(
    breeds: () -> List<BreedDomainEntity>,
    clickListener: (BreedDomainEntity) -> Unit
) {
    val scrollState = rememberLazyListState(initialFirstVisibleItemScrollOffset = 0)

    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp),
        state = scrollState,
    ) {
        itemsIndexed(items = breeds()) { index, breed ->
            val isEven = index % 2 == 0
            val background =
                if (isEven) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.surface
            val content =
                if (isEven) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
            Card(
                backgroundColor = background,
                contentColor = content,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { clickListener(breed) },
                elevation = 8.dp
            ) {
                CardContent(breed, isEven)
            }
        }
    }
}

@Composable
private fun CardContent(
    breed: BreedDomainEntity,
    isEven: Boolean
) {
    val showImage = breed.image.url.isNullOrEmpty().not()
    val placeholderBackground =
        if (isEven) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        val painter = painterResource(R.drawable.image_placeholder)
        val contentDescription = breed.name
        val contentScale = ContentScale.Crop
        val modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
        if (showImage) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(breed.image.url)
                    .crossfade(true)
                    .build(),
                placeholder = painter,
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = modifier,
            )
        } else {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = contentScale,
                modifier = modifier.background(placeholderBackground)

            )
        }
        Spacer(
            modifier = Modifier
                .width(16.dp)
        )
        Text(
            text = breed.name,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Start,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun HandleNavigationEvent(
    navigationEvent: AllBreedsNavigationEvent,
    onBreedSelected: (BreedDomainEntity) -> Unit
) {
    when (navigationEvent) {
        is ShowBreed -> onBreedSelected(navigationEvent.breed)
    }
}


