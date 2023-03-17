package com.drake.demeapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.drake.demeapp.ui.carousel.CarouselFragment
import com.drake.demeapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TIMEOUT = 10 * 1000
    }

    private val handler = Handler(Looper.getMainLooper())

    private val callback = Runnable {
        supportFragmentManager.beginTransaction()
            .add(R.id.container, CarouselFragment.newInstance(), CarouselFragment.TAG)
            .commitNow()
    }

    private fun resetTimer() {
        val fragment = supportFragmentManager.findFragmentByTag(CarouselFragment.TAG)
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .remove(fragment)
                .commitNow()
        }
        handler.removeCallbacks(callback)
        handler.postDelayed(callback, TIMEOUT.toLong())
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