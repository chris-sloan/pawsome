package com.chrissloan.paw_some.presentation.common

import androidx.annotation.StringRes
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ErrorView(
    @StringRes messageId: Int,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    onHandled: () -> Unit
) {
    val message = stringResource(id = messageId)
    LaunchedEffect(key1 = message) {
        scope.launch {
            scaffoldState.snackbarHostState.showSnackbar(message = message)
            onHandled()
        }
    }
}
