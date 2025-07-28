package com.tolodev.imperative

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.tolodev.imperative.adapter.ArtworkAdapter
import com.tolodev.imperative.databinding.ActivityMainImperativeBinding
import com.tolodev.imperative.model.Artwork

class MainImperativeActivity : AppCompatActivity() {

    private var _binding: ActivityMainImperativeBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var artworkAdapter: ArtworkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainImperativeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        loadSampleData()
    }
    
    private fun setupRecyclerView() {
        artworkAdapter = ArtworkAdapter()
        binding.recyclerView.apply {
            adapter = artworkAdapter
            layoutManager = GridLayoutManager(this@MainImperativeActivity, 2)
            setHasFixedSize(true)
        }
    }
    
    private fun loadSampleData() {
        val sampleArtworks = listOf(
            Artwork(
                id = 1,
                title = "Starry Night",
                artist = "Vincent van Gogh",
                imageUrl = "https://www.moma.org/collection/works/79802",
                date = "1889"
            ),
            Artwork(
                id = 2,
                title = "The Persistence of Memory",
                artist = "Salvador Dalí",
                imageUrl = "https://www.moma.org/collection/works/79018",
                date = "1931"
            ),
            Artwork(
                id = 3,
                title = "The Scream",
                artist = "Edvard Munch",
                imageUrl = "https://www.moma.org/collection/works/120030",
                date = "1893"
            ),
            Artwork(
                id = 4,
                title = "Girl with a Pearl Earring",
                artist = "Johannes Vermeer",
                imageUrl = "https://www.mauritshuis.nl/en/explore/the-collection/artworks/girl-with-a-pearl-earring-670/",
                date = "1665"
            )
        )
        
        artworkAdapter.submitList(sampleArtworks)
    }
}