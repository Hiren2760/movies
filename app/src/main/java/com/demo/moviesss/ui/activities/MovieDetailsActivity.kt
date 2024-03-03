package com.demo.moviesss.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.demo.moviesss.MoviesViewModel
import com.demo.moviesss.adapters.ProductionCompaniesAdapter
import com.demo.moviesss.api_helper.CallbackStatus
import com.demo.moviesss.databinding.ActivityMovieDetailsBinding
import com.demo.moviesss.utils.Constants
import com.demo.moviesss.utils.gone
import com.demo.moviesss.utils.visible

class MovieDetailsActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMovieDetailsBinding.inflate(layoutInflater) }
    private val moviesVM: MoviesViewModel by lazy { ViewModelProvider(this)[MoviesViewModel::class.java] }
    private val movieId: Int by lazy { intent.getIntExtra("movieId", 0) }

    private val prodCompaniesAdapter: ProductionCompaniesAdapter by lazy { ProductionCompaniesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            rvProductionCompanies.setHasFixedSize(true)
            rvProductionCompanies.layoutManager = GridLayoutManager(this@MovieDetailsActivity, 3)
            rvProductionCompanies.isNestedScrollingEnabled = false
            rvProductionCompanies.adapter = prodCompaniesAdapter

            btnBack.setOnClickListener {
                finish()
            }
        }
        loadMovieDetails()
    }

    private fun loadMovieDetails() {
        moviesVM.movieDetails.observe(this) { result ->
            when (result.callbackStatus) {
                CallbackStatus.LOADING -> {
                    binding.progressBar.visible()
                    binding.clDetails.gone()
                }

                CallbackStatus.SUCCESS -> {
                    result.details?.let { details ->
                        binding.progressBar.gone()
                        binding.clDetails.visible()

                        binding.run {
                            Glide.with(this@MovieDetailsActivity)
                                .load(Constants.POSTER_PATH_BASE_URL + details.posterPath)
                                .into(posterImage)

                            txtTitle.text = details.title
                            txtReleaseDate.text = details.releaseDate
                            txtGenres.text = details.genres.joinToString(separator = ", ")
                            txtOverview.text = details.overview

                            val votes = (details.voteAverage * 100) / 10f
                            txtRatingPerc.text = "${votes.toInt()}%"
                            pbRating.progress = votes.toInt()

                            prodCompaniesAdapter.updateList(details.productionCompanies)
                        }
                    }
                }

                else -> {}
            }
        }
        moviesVM.loadMovieDetails(movieId)
    }
}