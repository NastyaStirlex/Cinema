package com.nastirlex.cinema.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ItemFreshListBinding

class FreshListAdapter : RecyclerView.Adapter<FreshListAdapter.FreshListViewHolder>() {
    val images = arrayOf(
        R.drawable.fresh_example
    )

    class FreshListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemFreshListBinding.bind(view)

        fun bind(image: Int) {
            viewBinding.imageView3.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreshListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_fresh_list, parent, false)
        return FreshListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(holder: FreshListViewHolder, position: Int) {
        holder.bind(images[0])
    }
}