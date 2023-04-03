package com.nastirlex.cinema.presentation.main.collections.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ItemCollectionsListBinding

class CollectionsListAdapter :
    RecyclerView.Adapter<CollectionsListAdapter.CollectionsListViewHolder>() {
    class CollectionsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewBinding = ItemCollectionsListBinding.bind(view)

        fun bind() {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_collections_list, parent, false)

        return CollectionsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: CollectionsListViewHolder, position: Int) {
        holder.bind()
    }
}