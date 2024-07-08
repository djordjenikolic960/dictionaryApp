package com.example.dictionaryapp.features.dictionary.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionaryapp.features.feature_dictionary.presentation.components.WordInfoItem
import com.example.dictionaryapp.features.feature_dictionary.presentation.view_model.WordInfoViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WordInfo() {
    val viewModel: WordInfoViewModel = hiltViewModel()
    val state = viewModel.state
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                    snackBarHostState.showSnackbar(
                        message = event.message
                    )
                }

                else -> {}
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackBarHostState) { data ->
                Snackbar(
                    modifier = Modifier
                        .padding(12.dp)
                ) {
                    Text(data.visuals.message)
                }
            }
        }
    ) {
        it
        Box(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp)
            ) {
                TextField(
                    value = viewModel.searchQuery.value,
                    onValueChange = viewModel::onSearch,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("search")
                    })
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.value.wordInfoItems.size) { i ->
                        val wordInfo = state.value.wordInfoItems[i]
                        if (i > 0) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        WordInfoItem(wordInfo = wordInfo)
                        if (i < state.value.wordInfoItems.size - 1) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}