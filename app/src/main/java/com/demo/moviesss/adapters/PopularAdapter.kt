package com.demo.moviesss.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.moviesss.databinding.ListItemNowPlayingBinding
import com.demo.moviesss.models.PopularPojo
import com.demo.moviesss.utils.Constants

class PopularAdapter(val onClickMovie: (PopularPojo.PopularResults) -> Unit) :
    RecyclerView.Adapter<PopularAdapter.VH>() {

    private var moviesList: MutableList<PopularPojo.PopularResults> = mutableListOf()

    fun updateList(moviesList: MutableList<PopularPojo.PopularResults>) {
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ListItemNowPlayingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val nowPlayingPojo = moviesList[holder.adapterPosition]
        holder.bind(nowPlayingPojo)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class VH(private val binding: ListItemNowPlayingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PopularPojo.PopularResults) {
            binding.run {
                txtTitle.text = data.title
                val votes = (data.voteAverage * 100) / 10f
                txtRatingPerc.text = "${votes.toInt()}%"
                pbRating.progress = votes.toInt()

                txtReleaseDate.text = data.releaseDate

                Glide.with(itemView.context)
                    .load(Constants.POSTER_PATH_BASE_URL + data.posterPath)
                    .into(posterImage)
            }
            itemView.setOnClickListener {
                onClickMovie.invoke(data)
            }
        }
    }
}