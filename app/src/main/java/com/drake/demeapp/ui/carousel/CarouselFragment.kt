package com.drake.demeapp.ui.carousel

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.drake.demeapp.core.BaseFragment
import com.drake.demeapp.databinding.FragmentCarouselBinding
import com.drake.demeapp.ui.carousel.adapter.CarouselAdapter
import com.drake.demeapp.utils.images
import kotlin.math.abs

class CarouselFragment : BaseFragment<FragmentCarouselBinding>(
    FragmentCarouselBinding::inflate
) {
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var callback: Runnable
    private lateinit var carouselAdapter: CarouselAdapter

    companion object {
        fun newInstance() = CarouselFragment()
        const val TAG = "CAROUSEL_FRAGMENT"
        private const val TIMEOUT = 2 * 1000
    }

    private fun resetHandle() {
        if (::callback.isInitialized) {
            handler.removeCallbacks(callback)
            handler.postDelayed(callback, TIMEOUT.toLong())
        }
    }



    override fun setUpAdapter() {
        carouselAdapter = CarouselAdapter()
    }

    override fun setUpViews() {
        binding.apply {
            carouselAdapter.submitList(images)
            callback = Runnable {
                val index = viewPager.currentItem
                when (index) {
                    carouselAdapter.itemCount - 1 -> viewPager.currentItem = 0
                    else -> viewPager.currentItem = index + 1
                }
                resetHandle()
            }
            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - abs(position)
                page.scaleY = (0.80f + r * 0.20f)
            }

            viewPager.apply {
                clipChildren = false
                clipToPadding = false
                offscreenPageLimit = 3
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                adapter = carouselAdapter
                setPageTransformer(compositePageTransformer)
            }
        }
        resetHandle()
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(callback)
    }

}