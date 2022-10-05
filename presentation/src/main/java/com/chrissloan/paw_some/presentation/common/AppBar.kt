package com.chrissloan.paw_some.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

fun Modifier.appBarThemed(): Modifier = composed {
    Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
}

@Composable
fun PawsomeAppBar(
    modifier: () -> Modifier = { Modifier.appBarThemed() },
    title: () -> String,
    navIconVector: () -> ImageVector,
    navIconContentDescription: () -> String,
    onNavIconClicked: () -> (() -> Unit),
    scrollBehavior: TopAppBarScrollBehavior? = null,
    actionIcon: @Composable () -> Unit
) {
    MediumTopAppBar(
        title = {
            Text(
                text = title(),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        modifier = modifier(),
        navigationIcon = {
            IconButton(
                onClick = onNavIconClicked(),
            ) {
                Icon(navIconVector(), navIconContentDescription())
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            scrolledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        scrollBehavior = scrollBehavior,
        actions = {
            actionIcon()
        }
    )
}


@Preview
@Composable
fun PreviewAppBar() {
    PawsomeAppBar(
        modifier = { Modifier },
        title = { "Test Title" },
        navIconVector = { Icons.Filled.ArrowBack },
        navIconContentDescription = { "Test Content Description" },
        onNavIconClicked = { { } },
        actionIcon = {  }
    )
}
