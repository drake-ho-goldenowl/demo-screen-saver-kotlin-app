package com.drake.demeapp.ui.carousel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import coil.load
import coil.size.Scale
import com.drake.demeapp.core.BaseAdapter
import com.drake.demeapp.databinding.ItemCarouselBinding

class CarouselAdapter : BaseAdapter<String>() {
    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemCarouselBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    }

    override fun bind(binding: ViewBinding, position: Int) {
        val item = getItem(position)
        (binding as ItemCarouselBinding).apply {
            imageView.load(item) {
                this.scale(Scale.FILL)
            }
        }
    }
}