package com.example.myapplication.adapter

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.ChatTopics
import com.example.myapplication.databinding.ChatTopicsItemBinding
import com.example.myapplication.utils.SimpleListener

class ChatTopicsAdapter(
    private val simpleListener: SimpleListener
):RecyclerView.Adapter<ChatTopicsAdapter.chatTopicsViewHolder>() {


    var chatTopicList = listOf<ChatTopics>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = chatTopicsViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.chat_topics_item,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ChatTopicsAdapter.chatTopicsViewHolder, position: Int) {
        holder.chatTopicsItemBinding.topics = chatTopicList[position]

        holder.chatTopicsItemBinding.txtTopicsItem.setOnClickListener {
            //secureChatAdapter.onClickItem(position)
            simpleListener?.onClickItem(chatTopicList[position].topic)
            //holder.chatTopicsItemBinding.txtTopicsItem.visibility = View.GONE

        }
    }

    override fun getItemCount() = chatTopicList.size

    inner class chatTopicsViewHolder(
        val chatTopicsItemBinding:ChatTopicsItemBinding
    ):RecyclerView.ViewHolder(chatTopicsItemBinding.root)
}