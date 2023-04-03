package com.nastirlex.cinema.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ItemTagsListBinding

class TagsListAdapter : RecyclerView.Adapter<TagsListAdapter.TagsListViewHolder>() {
    val tags = arrayOf(
        "Фэнтэзи",
        "Приключения",
        "США",
        "Телесериал",
    )

    class TagsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemTagsListBinding.bind(view)

        fun bind(tag: String) {
            viewBinding.tagTextView.text = tag
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_tags_list, parent, false)

        return TagsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: TagsListViewHolder, position: Int) {
        holder.bind(tag = tags[position])
    }
}