package com.nastirlex.cinema.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.databinding.ItemTrendListBinding

class TrendListAdapter(
    private var trends: List<MovieDto>,
    private val onMovieClick: (MovieDto) -> Unit
) :
    RecyclerView.Adapter<TrendListAdapter.TrendListViewHolder>() {
    class TrendListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemTrendListBinding.bind(view)

        fun bind(movie: MovieDto, onClickListener: (MovieDto) -> Unit) {
            Glide
                .with(viewBinding.root)
                .load(movie.poster)
                .placeholder(R.drawable.placeholder_image)
                .into(viewBinding.trendPosterImageView)
            viewBinding.trendPosterImageView.setOnClickListener {
                onClickListener.invoke(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_trend_list, parent, false)
        return TrendListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trends.size
    }

    override fun onBindViewHolder(holder: TrendListViewHolder, position: Int) {
        holder.bind(movie = trends[position], onClickListener = { onMovieClick.invoke(trends[position]) })
    }
}