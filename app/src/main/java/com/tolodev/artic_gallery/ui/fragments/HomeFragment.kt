package com.tolodev.artic_gallery.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.tolodev.artic_gallery.DataProvider
import com.tolodev.artic_gallery.Puppy
import com.tolodev.artic_gallery.ui.theme.ArticGalleryTheme

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentColor = MaterialTheme.colorScheme.background,
        content = {
            BarkHomeContent(it)
        }
    )
}

@Composable
fun BarkHomeContent(paddingValues: PaddingValues) {
    val puppies: List<Puppy> = DataProvider.puppyList
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp),
        contentPadding = paddingValues
    ) {
        items(puppies.size) { index ->
            PuppyListItem(puppies[index])
        }
    }
}

@Composable
fun PuppyListItem(puppy: Puppy) {
    Log.d("PuppyListItem", "Title: ${puppy.title}, Description: ${puppy.description}")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = puppy.title, style = MaterialTheme.typography.titleMedium, color = Color.Black)
        Text(
            text = puppy.description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    ArticGalleryTheme {
        MyApp()
    }
}