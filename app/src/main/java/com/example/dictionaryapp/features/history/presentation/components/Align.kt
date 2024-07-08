package com.example.dictionaryapp.features.history.presentation.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Align(
    child: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = contentAlignment
    ) {
        child()
    }
}