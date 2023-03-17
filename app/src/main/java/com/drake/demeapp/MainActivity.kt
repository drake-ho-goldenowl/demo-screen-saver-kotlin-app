package com.drake.demeapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.drake.demeapp.ui.carousel.CarouselFragment
import com.drake.demeapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    private val handler = Handler(Looper.getMainLooper())
    private val viewModel : MainViewModel by viewModels()

    private val callback = Runnable {
        openViewPager()
    }

    private fun resetTimer() {
        val fragment = supportFragmentManager.findFragmentByTag(CarouselFragment.TAG)
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .remove(fragment)
                .commitNow()
        }
        handler.removeCallbacks(callback)
        handler.postDelayed(callback, viewModel.getTime())
    }

    private fun openViewPager(){
        supportFragmentManager.beginTransaction()
            .add(R.id.container, CarouselFragment.newInstance(), CarouselFragment.TAG)
            .commitNow()
        stopTimer()
        viewModel.dismissBottomSheet.postValue(true)
    }

    private fun stopTimer() {
        handler.removeCallbacks(callback)
    }

    override fun onUserInteraction() {
        resetTimer()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        viewModel.onClick.observe(this){
            if(it == true){
                openViewPager()
                viewModel.onClick.postValue(false)
            }
        }

        viewModel.time.observe(this){
            resetTimer()
        }

        viewModel.isReset.observe(this){
            if(it == true){
                resetTimer()
                viewModel.isReset.postValue(false)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        resetTimer()
    }

    override fun onStop() {
        super.onStop()
        stopTimer()
    }
}