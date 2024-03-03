package com.demo.moviesss.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.moviesss.databinding.ListItemProdCompaniesBinding
import com.demo.moviesss.models.MovieDetails
import com.demo.moviesss.utils.Constants

class ProductionCompaniesAdapter : RecyclerView.Adapter<ProductionCompaniesAdapter.VH>() {

    private var moviesList: MutableList<MovieDetails.ProductionCompanies> = mutableListOf()

    fun updateList(moviesList: MutableList<MovieDetails.ProductionCompanies>) {
        this.moviesList = moviesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(
            ListItemProdCompaniesBinding.inflate(
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

    inner class VH(private val binding: ListItemProdCompaniesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieDetails.ProductionCompanies) {
            binding.run {
                txtName.text = data.name

                Glide.with(itemView.context)
                    .load(Constants.POSTER_PATH_BASE_URL + data.logoPath)
                    .into(thumbProdCompany)
            }
        }
    }
}