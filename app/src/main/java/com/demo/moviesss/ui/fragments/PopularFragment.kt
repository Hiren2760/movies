package com.demo.moviesss.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.demo.moviesss.MoviesViewModel
import com.demo.moviesss.adapters.PopularAdapter
import com.demo.moviesss.api_helper.CallbackStatus
import com.demo.moviesss.databinding.FragmentPopularBinding
import com.demo.moviesss.ui.activities.MovieDetailsActivity
import com.demo.moviesss.utils.gone
import com.demo.moviesss.utils.visible

class PopularFragment : Fragment() {

    private lateinit var ctx: Context
    private lateinit var _binding: FragmentPopularBinding
    private val binding: FragmentPopularBinding get() = _binding
    private val moviesVM: MoviesViewModel by lazy { ViewModelProvider(this)[MoviesViewModel::class.java] }
    private val popularAdapter: PopularAdapter by lazy {
        PopularAdapter { result ->
            Intent(ctx, MovieDetailsActivity::class.java).apply {
                putExtra("movieId", result.id.toInt())
                startActivity(this)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::_binding.isInitialized) {
            _binding = FragmentPopularBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            rvPopular.layoutManager = GridLayoutManager(ctx, 2)
            rvPopular.setHasFixedSize(true)
            rvPopular.adapter = popularAdapter
        }

        loadMovies()
    }

    private fun loadMovies() {
        moviesVM.popularList.observe(viewLifecycleOwner) { apiResult ->
            when (apiResult.callbackStatus) {
                CallbackStatus.LOADING -> {
                    binding.progressBar.visible()
                    binding.rvPopular.gone()
                }

                CallbackStatus.SUCCESS -> {
                    binding.progressBar.gone()
                    binding.rvPopular.visible()

                    popularAdapter.updateList(apiResult.list)
                }

                else -> {}
            }
        }

        moviesVM.loadPopular()
    }

    companion object {
        fun newInstance(): PopularFragment {
            val fragment = PopularFragment()
            return fragment
        }
    }
}