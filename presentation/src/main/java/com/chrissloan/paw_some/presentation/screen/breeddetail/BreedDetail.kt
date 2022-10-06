package com.chrissloan.paw_some.presentation.screen.breeddetail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.chrissloan.paw_some.domain.entity.ImageDomainEntity
import com.chrissloan.paw_some.presentation.R
import com.chrissloan.paw_some.presentation.common.LoadingView
import com.chrissloan.paw_some.presentation.common.PawsomeAppBar
import com.chrissloan.paw_some.presentation.common.appBarThemed
import com.chrissloan.paw_some.presentation.screen.breeddetail.BreedDetailViewModel.BreedDetailAction.ErrorMessageShown
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun BreedDetailScreen(
    breedId: String,
    onBackClicked: () -> Unit,
    viewModel: BreedDetailViewModel = getViewModel(parameters = { parametersOf(breedId) })
) {
    val uiState = viewModel.uiState
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    val scrollBehavior =
        remember { TopAppBarDefaults.enterAlwaysScrollBehavior(canScroll = { true }) }

    var expanded by remember { mutableStateOf(false) }

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
            PawsomeAppBar(
                title = { uiState.breed?.name ?: "Pawsome" },
                navIconVector = { Icons.Filled.ArrowBack },
                navIconContentDescription = { "Return to Breeds list" },
                onNavIconClicked = { onBackClicked },
                scrollBehavior = scrollBehavior,
                actionIcon = {
                    if (expanded) {
                        CloseActionIcon(
                            onClick = { expanded = false }
                        )
                    } else {
                        RevealActionIcon(
                            onClick = { expanded = true }
                        )
                    }
                }
            )
        },
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) {
            if (uiState.isLoading) {
                LoadingView()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    Surface(
                        modifier = Modifier
                            .appBarThemed()
                            .fillMaxWidth()
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                    ) {
                        if (expanded) {
                            Column(
                                modifier = Modifier
                                    .appBarThemed()
                            ) {
                                ExpandedBreedProperty(
                                    name = { "Synopsis" },
                                    value = { uiState.breed?.description.orEmpty() }
                                )
                                ExpandedBreedProperty(
                                    name = { "Temperament" },
                                    value = { uiState.breed?.temperament.orEmpty() }
                                )
                                ExpandedBreedProperty(
                                    name = { "Origin" },
                                    value = { uiState.breed?.origin.orEmpty() }
                                )
                                ExpandedBreedProperty(
                                    name = { "Wikipedia Url" },
                                    value = { uiState.breed?.wikiUrl.orEmpty() },
                                    isLink = true
                                )
                            }
                        }
                    }
                    BreedImages(
                        images = { uiState.images },
                        clickListener = { image -> println("<<<<< - TODO Full screen size expand - $image") },
                        breedName = uiState.breed?.name.orEmpty()
                    )
                }
            }
        }
    }
}

@Composable
fun ExpandedBreedProperty(
    name: () -> String,
    value: () -> String,
    isLink: Boolean = false
) {
    Column(
        modifier = Modifier
            .appBarThemed()
            .padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
    ) {
        Divider()
        Text(
            text = name(),
            modifier = Modifier
                .padding(top = 8.dp)
                .height(24.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        val style = if (isLink) {
            MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary)
        } else {
            MaterialTheme.typography.bodyLarge
        }
        Text(
            text = value(),
            modifier = Modifier
                .padding(top = 8.dp),
            style = style
        )
    }
}

@Composable
fun RevealActionIcon(
    onClick: () -> Unit
) {
    Icon(
        imageVector = Icons.Outlined.Info,
        tint = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 16.dp)
            .height(24.dp),
        contentDescription = "Show Expanded Details"
    )
}

@Composable
fun CloseActionIcon(
    onClick: () -> Unit
) {
    Icon(
        imageVector = Icons.Filled.Info,
        tint = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = Modifier
            .clickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 16.dp)
            .height(24.dp),
        contentDescription = "Hide Expanded Details"
    )
}

@Composable
fun BreedImages(
    images: () -> List<ImageDomainEntity>,
    clickListener: (ImageDomainEntity) -> Unit,
    breedName: String
) {
    val scrollState = rememberLazyListState(initialFirstVisibleItemScrollOffset = 0)

    LazyColumn(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .background(MaterialTheme.colorScheme.surface),
        state = scrollState,

        ) {
        items(items = images()) { image ->
            Card(
                backgroundColor = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable { clickListener(image) },
                elevation = 16.dp
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
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(image.url)
                .crossfade(true)
                .build(),
            contentDescription = "Image of $breedName",
            placeholder = painterResource(R.drawable.image_placeholder),
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp),
        )
    }
}
