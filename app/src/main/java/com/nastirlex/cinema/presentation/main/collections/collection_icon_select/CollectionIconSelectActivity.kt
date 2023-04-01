package com.nastirlex.cinema.presentation.main.collections.collection_icon_select

import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.nastirlex.cinema.databinding.ActivityCollectionIconSelectBinding
import com.nastirlex.cinema.utils.CollectionItemListSpacesItemDecoration
import com.nastirlex.cinema.utils.dpToPixel

class CollectionIconSelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCollectionIconSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCollectionIconSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.iconsRecyclerView.layoutManager = GridLayoutManager(this, 4)

        /* pass item dato to activity / fragment when clicked */
        binding.iconsRecyclerView.adapter = CollectionIconListAdapter { position ->
            Toast.makeText(
                this,
                "item $position clicked",
                Toast.LENGTH_LONG
            ).show()
        }

        binding.iconsRecyclerView.addItemDecoration(
            CollectionItemListSpacesItemDecoration(
                bottom = this.dpToPixel(
                    16f
                ).toInt(),
                start = this.dpToPixel(5f).toInt(),
                end = this.dpToPixel(5f).toInt()
            )
        )
    }

}