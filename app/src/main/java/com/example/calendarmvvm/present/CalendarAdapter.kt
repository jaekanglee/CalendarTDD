package com.example.calendarmvvm.present

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarmvvm.databinding.ItemCalendarDayBinding
import com.example.calendarmvvm.model.DayListEntity

class CalendarAdapter(
    private val listener: AdapterListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            ItemCalendarDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder)
            holder.bind(listener.getItemFromIndex(position))
    }

    override fun getItemCount(): Int {
        return listener.getItemsSize()
    }

}