package com.nastirlex.cinema.presentation.main.compilation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.databinding.ItemBinding


class CompilationAdapter(
    private val compilation: List<MovieDto>
) : RecyclerView.Adapter<CompilationAdapter.CompilationViewHolder>() {

    class CompilationViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val viewBinding = ItemBinding.bind(view)

        fun bind(movie: MovieDto) {
            Glide.with(viewBinding.root)
                .load(movie.poster)
                .into(viewBinding.movieImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompilationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)

        return CompilationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return compilation.size
    }

    override fun onBindViewHolder(holder: CompilationViewHolder, position: Int) {
        holder.bind(compilation[position])
    }

}