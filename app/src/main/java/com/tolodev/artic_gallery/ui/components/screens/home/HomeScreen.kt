package com.tolodev.artic_gallery.ui.components.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.tolodev.artic_gallery.domain.models.DataProviderMock
import com.tolodev.artic_gallery.ui.components.general.ArticGalleryApp
import com.tolodev.artic_gallery.ui.components.general.ArticGalleryError
import com.tolodev.artic_gallery.ui.components.general.ArticGalleryLoader
import com.tolodev.artic_gallery.ui.models.UIArtwork
import com.tolodev.artic_gallery.ui.models.UIStatus
import timber.log.Timber

@Composable
fun HomeScreen(
    uiStatus: UIStatus<List<UIArtwork>>,
    primaryAction: () -> Unit,
    secondaryAction: (Long) -> Unit
) {
    ArticGalleryApp(content = {
        var isRefreshing by remember { mutableStateOf(false) }

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = {
                isRefreshing = true
                primaryAction()
            },
        ) {
            isRefreshing = false
            when (uiStatus) {
                is UIStatus.Loading -> ArticGalleryLoader()
                is UIStatus.Successful -> HomeArtworkList(
                    paddingValues = it,
                    artworks = uiStatus.value,
                    showArtworkDetail = { secondaryAction(it) }
                )

                is UIStatus.Error -> {
                    Timber.e("Error: ${uiStatus.msg}")
                    ArticGalleryError(uiStatus.msg)
                }
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(UIStatus.Successful(value = DataProviderMock.getMockedUIArtworks), {}, {})
}