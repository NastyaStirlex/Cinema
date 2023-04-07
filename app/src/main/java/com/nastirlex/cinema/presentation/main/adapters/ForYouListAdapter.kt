package com.nastirlex.cinema.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ItemForYouListBinding

class ForYouListAdapter : RecyclerView.Adapter<ForYouListAdapter.ForYouViewHolder>() {
    val images = arrayOf(
        R.drawable.for_you_example
    )

    class ForYouViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemForYouListBinding.bind(view)

        fun bind(image: Int) {
            viewBinding.forYouImageView.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForYouViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_for_you_list, parent, false)
        return ForYouViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(holder: ForYouViewHolder, position: Int) {
        holder.bind(images[0])
    }
}