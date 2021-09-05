package com.example.calendarmvvm.present

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("setCalednarGridAdapter")
fun setCalednarGridAdapter(recyclerView: RecyclerView,adapter:RecyclerView.Adapter<*>){

    val context = recyclerView.context
    recyclerView.run {
        layoutManager =GridLayoutManager(context,7)
        this.adapter = adapter
    }


}