package com.chrissloan.paw_some.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
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
    SmallTopAppBar(
        title = { AutosizeText(text = title()) },
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

@Composable
fun AutosizeText(
    text: String
) {
    var multiplier by remember { mutableStateOf(1f) }
    var needsEllipsis by remember { mutableStateOf(false) }

    val overflow = if (needsEllipsis) {
        TextOverflow.Ellipsis
    } else {
        TextOverflow.Visible
    }
    ProvideTextStyle(value = MaterialTheme.typography.headlineMedium) {
        Text(
            text = text,
            modifier = Modifier.padding(8.dp),
            maxLines = 1,
            overflow = overflow,
            style = LocalTextStyle.current.copy(
                fontSize = LocalTextStyle.current.fontSize * multiplier,
            ),
            onTextLayout = {
                if (it.hasVisualOverflow) {
                    if (multiplier < 0.75f) {
                        needsEllipsis = true
                    } else {
                        multiplier *= 0.99f
                    }

                }
            }
        )
    }
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
        actionIcon = { }
    )
}
