package com.nastirlex.cinema.presentation.main.collections.collection_info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.database.Film
import com.nastirlex.cinema.databinding.ItemCollectionFilmsListBinding

class CollectionFilmsListAdapter(private val collectionFilms: List<Film>) :
    RecyclerView.Adapter<CollectionFilmsListAdapter.CollectionFilmsListViewHolder>() {

    class CollectionFilmsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemCollectionFilmsListBinding.bind(view)

        fun bind(film: Film) {
            Glide.with(viewBinding.root).load(film.poster).into(viewBinding.filmPosterImageView)
            viewBinding.nameFilmTextView.text = film.name
            viewBinding.descriptionFilmTextView.text = film.description
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionFilmsListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_collection_films_list, parent, false)

        return CollectionFilmsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return collectionFilms.size
    }

    override fun onBindViewHolder(holder: CollectionFilmsListViewHolder, position: Int) {
        holder.bind(collectionFilms[position])
    }
}