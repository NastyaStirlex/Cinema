package com.nastirlex.cinema.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.databinding.ItemForYouListBinding

class ForYouListAdapter(
    private val forYou: List<MovieDto>,
    private val onMovieClick: (MovieDto) -> Unit
) : RecyclerView.Adapter<ForYouListAdapter.ForYouViewHolder>() {

    class ForYouViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemForYouListBinding.bind(view)

        fun bind(movie: MovieDto, onClickListener: (MovieDto) -> Unit) {
            Glide.with(viewBinding.root)
                .load(movie.poster)
                .into(viewBinding.forYouImageView)

            viewBinding.forYouImageView.setOnClickListener {
                onClickListener.invoke(movie)
            }
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
        holder.bind(
            movie = forYou[position],
            onClickListener = { onMovieClick.invoke(forYou[position]) }
        )
    }
}