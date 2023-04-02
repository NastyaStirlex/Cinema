package com.nastirlex.cinema.presentation.main.collections.collection_change

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ActivityCollectionChangeBinding

class CollectionChangeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCollectionChangeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCollectionChangeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}