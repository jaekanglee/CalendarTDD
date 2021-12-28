package com.example.calendarmvvm.present

import androidx.recyclerview.widget.RecyclerView
import com.example.calendarmvvm.databinding.ItemCalendarDayBinding
import com.example.calendarmvvm.model.DayListEntity


class ItemViewHolder(
    private val binding: ItemCalendarDayBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: DayListEntity?) {
        binding.day = item?.value
        binding.executePendingBindings()
    }
}
