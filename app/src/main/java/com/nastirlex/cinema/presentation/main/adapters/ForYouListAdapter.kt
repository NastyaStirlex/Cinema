package com.nastirlex.cinema.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.databinding.ItemForYouListBinding

class ForYouListAdapter(private val forYou: List<MovieDto>) : RecyclerView.Adapter<ForYouListAdapter.ForYouViewHolder>() {

    class ForYouViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemForYouListBinding.bind(view)

        fun bind(image: String) {
            Glide.with(viewBinding.root).load(image).into(viewBinding.forYouImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForYouViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_for_you_list, parent, false)
        return ForYouViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forYou.size
    }

    override fun onBindViewHolder(holder: ForYouViewHolder, position: Int) {
        holder.bind(forYou[position].poster)
    }
}