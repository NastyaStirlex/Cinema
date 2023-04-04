package com.nastirlex.cinema.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ItemEpisodesListBinding

class EpisodesListAdapter : RecyclerView.Adapter<EpisodesListAdapter.EpisodesListViewHolder>() {
    class EpisodesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemEpisodesListBinding.bind(view)

        fun bind(image: Int, name: String, description: String, year: String) {
            viewBinding.episodeImageView.setImageResource(image)
            viewBinding.episodeNameTextView.text = name
            viewBinding.episodeDescTextView.text = description
            viewBinding.yearTextView.text = year
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_episodes_list, parent, false)

        return EpisodesListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(holder: EpisodesListViewHolder, position: Int) {
        holder.bind(
            R.drawable.episodes_lise_example,
            "Нелегальная магия",
            "Квентин и Джулия приглашены на тест их волшебных навыков...",
            "2015"
        )
    }
}