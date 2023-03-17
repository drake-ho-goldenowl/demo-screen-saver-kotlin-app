package com.drake.demeapp.ui.bottomSheet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drake.demeapp.databinding.ItemTimerBinding

class TimerAdapter(val listTime: ArrayList<Int>, val onClick: ITimerClick) :
    RecyclerView.Adapter<TimerAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemTimerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTimerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listTime.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            this.radioTime.text = listTime[position].toString() + " mins"

            radioTime.setOnCheckedChangeListener { compoundButton, b ->
                onClick.onClickTime(listTime[position])
            }
        }
    }

    interface ITimerClick {
        fun onClickTime(minute: Int)
    }
}
