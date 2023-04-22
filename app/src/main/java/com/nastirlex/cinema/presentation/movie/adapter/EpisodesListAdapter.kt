package com.nastirlex.cinema.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.EpisodeDto
import com.nastirlex.cinema.databinding.ItemEpisodesListBinding

class EpisodesListAdapter(
    private val episodes: List<EpisodeDto>,
    private val onEpisodeClick: (EpisodeDto) -> Unit
) :
    RecyclerView.Adapter<EpisodesListAdapter.EpisodesListViewHolder>() {
    class EpisodesListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemEpisodesListBinding.bind(view)

        fun bind(
            episode: EpisodeDto,
            onClickListener: (EpisodeDto) -> Unit
        ) {
            Glide
                .with(viewBinding.root)
                .load(episode.preview)
                .into(viewBinding.episodeImageView)

            viewBinding.episodeNameTextView.text = episode.name

            viewBinding.episodeDescTextView.text = episode.description

            viewBinding.yearTextView.text = episode.year

            viewBinding.constraintLayout.setOnClickListener {
                onClickListener.invoke(episode)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_episodes_list, parent, false)

        return EpisodesListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return episodes.size
    }

    override fun onBindViewHolder(holder: EpisodesListViewHolder, position: Int) {
        holder.bind(
            episode = episodes[position],
            onClickListener = { onEpisodeClick.invoke(episodes[position]) }
        )
    }
}