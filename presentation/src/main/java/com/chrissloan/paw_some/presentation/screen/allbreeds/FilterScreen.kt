package com.chrissloan.paw_some.presentation.screen.allbreeds

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chrissloan.paw_some.presentation.R

@Composable
fun FiltersDrawerContent(
    onApply: () -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            val properties = stringArrayResource(id = R.array.filter_property)
            LazyColumn {
                item { FilterHeader(onCancel, onApply) }

                properties.forEach { property ->
                    item(key = property) {
                        BreedProperty(
                            property = property
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FilterHeader(
    onCancel: () -> Unit,
    onApply: () -> Unit
) {

    val title = stringResource(id = R.string.filters_title)
    Text(
        text = title,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.headlineSmall
    )
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CancelButton(onCancel = onCancel)
        ApplyButton(onApply = onApply)
    }
}


@Composable
fun CancelButton(
    onCancel: () -> Unit
) {
    Button(
        modifier = Modifier.padding(8.dp),
        onClick = { onCancel() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Text(
            text = stringResource(id = R.string.filters_cancel),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun ApplyButton(
    onApply: () -> Unit
) {
    Button(
        onClick = { onApply() },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = stringResource(id = R.string.filters_apply),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Composable
fun BreedProperty(
    property: String
) {
    var sliderPosition by remember { mutableStateOf(0f) }

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 4.dp)) {
        Divider()
        Text(
            text = property,
            modifier = Modifier
                .padding(top = 8.dp)
                .height(24.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Slider(
            value = sliderPosition,
            valueRange = 0f..5f,
            steps = 6,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondary,
                activeTickColor = MaterialTheme.colorScheme.primary,
                inactiveTickColor = MaterialTheme.colorScheme.secondary
            ),
            onValueChange = { newValue ->
                sliderPosition = newValue
            },
        )
    }
}

