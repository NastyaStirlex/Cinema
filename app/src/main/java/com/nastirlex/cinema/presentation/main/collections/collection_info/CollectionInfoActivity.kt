package com.nastirlex.cinema.presentation.main.collections.collection_info

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cinema.databinding.ActivityCollectionInfoBinding
import com.nastirlex.cinema.utils.CollectionFilmsItemListSpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class CollectionInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCollectionInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCollectionInfoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.collectionFilmsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.collectionFilmsRecyclerView.adapter = CollectionFilmsListAdapter()
        binding.collectionFilmsRecyclerView.addItemDecoration(
            CollectionFilmsItemListSpacesItemDecoration(bottom = this.dpToPixel(16f).toInt(), start = 0, end = 0)
        )
    }
}