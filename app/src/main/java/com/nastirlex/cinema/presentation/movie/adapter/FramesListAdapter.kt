package com.nastirlex.cinema.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ItemFramesListBinding

class FramesListAdapter : RecyclerView.Adapter<FramesListAdapter.FramesListViewHolder>() {
    class FramesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemFramesListBinding.bind(view)

        fun bind(image: Int) {
            viewBinding.frameImageView.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FramesListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_frames_list, parent, false)

        return FramesListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(holder: FramesListViewHolder, position: Int) {
        holder.bind(R.drawable.frames_list_example)
    }
}