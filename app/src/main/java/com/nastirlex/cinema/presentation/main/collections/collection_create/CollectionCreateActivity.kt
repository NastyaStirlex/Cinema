package com.nastirlex.cinema.presentation.main.collections.collection_create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ActivityCollectionCreateBinding

class CollectionCreateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCollectionCreateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCollectionCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}