package com.demo.moviesss.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.demo.moviesss.databinding.ActivityMainBinding
import com.demo.moviesss.ui.fragments.NowPlayingFragment
import com.demo.moviesss.ui.fragments.PopularFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val tabs = arrayOf("Latest", "Popular")

    private val nowPlayingFragment by lazy { NowPlayingFragment.newInstance() }
    private val popularFragment = PopularFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpViewPager()

        binding.run {

        }
    }

    private fun setUpViewPager() {
        binding.run {
            homePager.adapter = HomePagerAdapter(arrayListOf(nowPlayingFragment, popularFragment))
            homePager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            tabLayout.addTab(tabLayout.newTab().apply {
                text = tabs[0]
            })
            tabLayout.addTab(tabLayout.newTab().apply {
                text = tabs[1]
            })

            TabLayoutMediator(tabLayout, homePager) { tab, position ->
                tab.text = tabs[position]
            }.attach()
        }
    }

    inner class HomePagerAdapter(val fragments: ArrayList<Fragment>) : FragmentStateAdapter(this) {
        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

    }
}