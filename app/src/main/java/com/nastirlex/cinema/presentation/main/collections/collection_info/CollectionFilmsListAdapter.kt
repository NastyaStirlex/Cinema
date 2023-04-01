package com.nastirlex.cinema.presentation.main.collections.collection_info

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ItemCollectionFilmsListBinding

class CollectionFilmsListAdapter :
    RecyclerView.Adapter<CollectionFilmsListAdapter.CollectionFilmsListViewHolder>() {

    val images = arrayOf(
        R.drawable.collection_film_example
    )
    val names = arrayOf(
        R.string.name_example
    )
    val desc = arrayOf(
        R.string.description_example
    )

    class CollectionFilmsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemCollectionFilmsListBinding.bind(view)

        fun bind(image: Int, name: Int, description: Int) {
            viewBinding.posterImageView.setImageResource(image)
            viewBinding.nameFilmTextView.text = "Jessica Jones"
            viewBinding.descriptionFilmTextView.text = "Eliot is in his happy place, unaware that he is being possessed by the Monster. To have control over his body, Eliot must travel to the place that contains his greatest regret: turning down Quentin when he suggests he and Eliot should be together after their memories are restored of their life in past-Fillory, happily living together and raising a family. Iris tasks Julia to trap the Monster and will kill her if she fails. Alice sends Plover to the Poison Room and reunites with Quentin. At the park, Eliot takes over his body and tells Quentin that he is alive. The Monster takes control back. Iris appears and kills Shoshana. Before she can kill Julia for failing, the Monster kills her. Alice diverts the plan to save Quentin. Meanwhile, Fillory is having new problems. Penny-23 is kidnapped."
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
        return 10
    }

    override fun onBindViewHolder(holder: CollectionFilmsListViewHolder, position: Int) {
        holder.bind(images[0], names[0], desc[0])
    }
}