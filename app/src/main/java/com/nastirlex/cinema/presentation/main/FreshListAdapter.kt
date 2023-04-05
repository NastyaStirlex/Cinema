package com.nastirlex.cinema.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.databinding.ItemFreshListBinding

class FreshListAdapter(private val fresh: List<MovieDto>) : RecyclerView.Adapter<FreshListAdapter.FreshListViewHolder>() {

    class FreshListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemFreshListBinding.bind(view)

        fun bind(image: String) {
            Glide.with(viewBinding.root).load(image).into(viewBinding.freshImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreshListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_fresh_list, parent, false)
        return FreshListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fresh.size
    }

    override fun onBindViewHolder(holder: FreshListViewHolder, position: Int) {
        holder.bind(fresh[position].poster)
    }
}