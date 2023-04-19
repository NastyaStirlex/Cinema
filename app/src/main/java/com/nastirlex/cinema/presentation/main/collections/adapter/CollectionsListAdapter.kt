package com.nastirlex.cinema.presentation.main.collections.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.CollectionDto
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.databinding.ItemCollectionsListBinding

class CollectionsListAdapter(
    private val collections: List<CollectionDto>,
    private val onCollectionClick: (CollectionDto) -> Unit
) :
    RecyclerView.Adapter<CollectionsListAdapter.CollectionsListViewHolder>() {
    class CollectionsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewBinding = ItemCollectionsListBinding.bind(view)

        fun bind(collection: CollectionDto, onClickListener: (CollectionDto) -> Unit) {
            viewBinding.collectionNameTextView.text = collection.name
            viewBinding.constraintLayout.setOnClickListener {
                onClickListener.invoke(collection)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionsListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_collections_list, parent, false)

        return CollectionsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return collections.size
    }

    override fun onBindViewHolder(holder: CollectionsListViewHolder, position: Int) {
        holder.bind(
            collection = collections[collections.size - 1 - position],
            onClickListener = { onCollectionClick.invoke(collections[collections.size - 1 - position]) }
        )
    }
}