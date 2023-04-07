package com.nastirlex.cinema.presentation.main.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nastirlex.cinema.R
import com.nastirlex.cinema.databinding.ItemTrendListBinding

class TrendListAdapter : RecyclerView.Adapter<TrendListAdapter.TrendListViewHolder>() {
    val images = arrayOf(
        R.drawable.trend
    )

    class TrendListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemTrendListBinding.bind(view)

        fun bind(image: Int) {
            viewBinding.imageView2.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_trend_list, parent, false)
        return TrendListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 8
    }

    override fun onBindViewHolder(holder: TrendListViewHolder, position: Int) {
        holder.bind(images[0])
    }
}