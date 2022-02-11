package com.example.myapplication.utils

import android.content.Context
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


@BindingAdapter("ScrollToBottom")
fun scrollToBottom(recyclerView: RecyclerView,position:Int){
    recyclerView.scrollToPosition(position)
}

/*
@BindingAdapter("setLayout")
fun setLayout(recyclerView: RecyclerView,context: Context,count:Int){
    val gridLayoutManager = GridLayoutManager(context,count)
    recyclerView.layoutManager = gridLayoutManager
}*/
