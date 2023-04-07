package com.nastirlex.cinema.presentation.main.collections.collection_icon_select

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ItemCollectionIconListBinding

class CollectionIconListAdapter(private val onIconClick: (Int) -> Unit) :
    RecyclerView.Adapter<CollectionIconListAdapter.CollectionIconSelectViewHolder>() {

    val iconsArray = arrayOf(
        R.drawable.ic_heart,
        R.drawable.ic_book,
        R.drawable.ic_check_circle,
        R.drawable.ic_clock,
        R.drawable.ic_cloud_showers_heavy,
        R.drawable.ic_coffee,
        R.drawable.ic_compass,
        R.drawable.ic_dollar,
        R.drawable.ic_bolt,
        R.drawable.ic_basketball,
        R.drawable.ic_assistive_listening_systems,
        R.drawable.ic_award,
        R.drawable.ic_dumbbell,
        R.drawable.ic_favorite,
        R.drawable.ic_film,
        R.drawable.ic_fire,
        R.drawable.ic_flask,
        R.drawable.ic_flower,
        R.drawable.ic_heart_break,
        R.drawable.ic_grin,
        R.drawable.ic_frown,
        R.drawable.ic_graduation_cap,
        R.drawable.ic_key_skeleton,
        R.drawable.ic_lightbulb,
        R.drawable.ic_medkit,
        R.drawable.ic_moon,
        R.drawable.ic_music,
        R.drawable.ic_ninja,
        R.drawable.ic_plane_departure,
        R.drawable.ic_rocket,
        R.drawable.ic_snowflake,
        R.drawable.ic_syringe,
        R.drawable.ic_sun,
        R.drawable.ic_yin_yang,
        R.drawable.ic_venus,
        R.drawable.ic_mars,
    )

    class CollectionIconSelectViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {


        private var viewBinding = ItemCollectionIconListBinding.bind(view)

        fun bind(position: Int, image: Int, onClickListener: (Int) -> Unit) {
            viewBinding.iconImageView.setImageResource(image)
            viewBinding.iconImageView.setOnClickListener {
                onClickListener.invoke(position)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CollectionIconSelectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_collection_icon_list, parent, false)

        return CollectionIconSelectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return iconsArray.size
    }

    override fun onBindViewHolder(holder: CollectionIconSelectViewHolder, position: Int) {
        holder.bind(
            position = position,
            image = iconsArray[position],
            onClickListener = { onIconClick.invoke(position) }
        )
    }

}