package com.drake.demeapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.drake.demeapp.databinding.ItemImageBinding

class MainAdapter(private val data: ArrayList<String>) :
    RecyclerView.Adapter<MainAdapter.MainItemViewHolder>() {

    class MainItemViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.imageView.load(item) {
                this.scale(Scale.FILL)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainItemViewHolder {
        return MainItemViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MainItemViewHolder, position: Int) {
        holder.bind(data[position])
    }
}