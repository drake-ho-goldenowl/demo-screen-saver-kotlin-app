package com.drake.demeapp.ui.carousel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.drake.demeapp.databinding.ItemCarouselBinding

class CarouselAdapter(private val data: ArrayList<String>) :
    RecyclerView.Adapter<CarouselAdapter.CarouselItemViewHolder>() {

    class CarouselItemViewHolder(private val binding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.imageView.load(item) {
                this.scale(Scale.FILL)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselItemViewHolder {
        return CarouselItemViewHolder(
            ItemCarouselBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CarouselItemViewHolder, position: Int) {
        holder.bind(data[position])
    }
}