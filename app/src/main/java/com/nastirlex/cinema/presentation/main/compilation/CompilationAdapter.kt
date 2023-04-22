package com.nastirlex.cinema.presentation.main.compilation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.MovieDto
import com.nastirlex.cinema.databinding.ItemBinding


class CompilationAdapter(
    private val compilation: List<MovieDto>
) : BaseAdapter() {

    override fun getCount(): Int {
        return compilation.size
    }

    override fun getItem(position: Int): MovieDto {
        return compilation[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item, parent, false)

        val viewBinding = ItemBinding.bind(view)


        Glide.with(viewBinding.root)
            .load(getItem(position).poster)
            .into(viewBinding.movieImageView)

        return view
    }

}