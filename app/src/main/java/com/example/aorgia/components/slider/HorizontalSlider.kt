package com.example.aorgia.components.slider

import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun HorizontalSlider(
    screens: List<SliderScreen>,
    modifier: Modifier
) {
    val sliderState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyRow(
        modifier = modifier,
        userScrollEnabled = false,
        state = sliderState
    ) {
        items(screens) { screen: SliderScreen ->
            val index = screens.indexOf(screen)
            screen.screen {
                coroutineScope.launch {
                    sliderState.animateScrollToItem(index + 1, 0)
                }
            }
        }
    }
}