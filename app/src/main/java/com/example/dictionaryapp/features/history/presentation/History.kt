package com.example.dictionaryapp.features.history.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionaryapp.features.feature_dictionary.presentation.components.WordInfoItem
import com.example.dictionaryapp.features.history.presentation.presentation.components.Align
import com.example.dictionaryapp.features.history.presentation.presentation.view_model.HistoryState
import com.example.dictionaryapp.features.history.presentation.view_model.HistoryViewModel

@Composable
fun History(
    state:
    State<HistoryState>
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .background(color = Color.Blue)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                    text = "History",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 22.sp,
                    )
                )
            }
        }
    ) { innerPadding ->
        if (state.value.isLoading) {
            CircularProgressIndicator()
        } else if (state.value.isError) {
            Align(child = {
                Text(
                    text = "Error occurred",
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color.Black
                    )
                )
            })
        } else if (state.value.wordInfoItems.isEmpty()) {
            Align(child = {
                Text(
                    text = "List is Empty",
                    style = TextStyle(fontSize = 22.sp, color = Color.Black)
                )
            })
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
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