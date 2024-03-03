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
import com.demo.moviesss.adapters.NowPlayingAdapter
import com.demo.moviesss.api_helper.CallbackStatus
import com.demo.moviesss.databinding.FragmentNowPlayingBinding
import com.demo.moviesss.ui.activities.MovieDetailsActivity
import com.demo.moviesss.utils.gone
import com.demo.moviesss.utils.visible

class NowPlayingFragment : Fragment() {
    private lateinit var ctx: Context
    private lateinit var _binding: FragmentNowPlayingBinding
    private val binding: FragmentNowPlayingBinding get() = _binding
    private val moviesVM: MoviesViewModel by lazy { ViewModelProvider(this)[MoviesViewModel::class.java] }
    private val nowPlayingAdapter: NowPlayingAdapter by lazy {
        NowPlayingAdapter { result ->
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
            _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            rvNowPlaying.layoutManager = GridLayoutManager(ctx, 2)
            rvNowPlaying.setHasFixedSize(true)
            rvNowPlaying.adapter = nowPlayingAdapter
        }

        loadMovies()
    }

    private fun loadMovies() {
        moviesVM.nowPlayingList.observe(viewLifecycleOwner) { apiResult ->
            when (apiResult.callbackStatus) {
                CallbackStatus.LOADING -> {
                    binding.progressBar.visible()
                    binding.rvNowPlaying.gone()
                }

                CallbackStatus.SUCCESS -> {
                    binding.progressBar.gone()
                    binding.rvNowPlaying.visible()

                    nowPlayingAdapter.updateList(apiResult.list)
                }

                else -> {}
            }
        }

        moviesVM.loadNowPlaying()
    }

    companion object {
        fun newInstance(): NowPlayingFragment {
            val fragment = NowPlayingFragment()
            return fragment
        }
    }
}