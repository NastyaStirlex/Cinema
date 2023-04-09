package com.nastirlex.cinema.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ItemFramesListBinding

class FramesListAdapter(private val frames: Array<String>) :
    RecyclerView.Adapter<FramesListAdapter.FramesListViewHolder>() {
    class FramesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemFramesListBinding.bind(view)

        fun bind(image: String) {
            Glide.with(viewBinding.root).load(image).into(viewBinding.frameImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FramesListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_frames_list, parent, false)

        return FramesListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return frames.size
    }

    override fun onBindViewHolder(holder: FramesListViewHolder, position: Int) {
        holder.bind(frames[position])
    }
}