package com.example.myapplication.adapter


import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.myapplication.data.ChatTopics
import com.example.myapplication.data.SecureChat
import com.example.myapplication.databinding.ChatTopicsMenuBinding
import com.example.myapplication.databinding.HeaderMsgChatBinding
import com.example.myapplication.databinding.IncomingSecureChatBinding
import com.example.myapplication.databinding.OutgoingSecureChatBinding
import com.example.myapplication.utils.RecyclerViewTouchListener
import com.example.myapplication.utils.SimpleListener
import com.example.myapplication.utils.SpaceItemDecoration
import com.example.myapplication.utils.changeDateFormat

sealed class SecureChatRecyclerViewHolder(
    binding:ViewBinding
):RecyclerView.ViewHolder(binding.root) {


    class HeaderMessageHolder(private val headerMsgChatBinding: HeaderMsgChatBinding):SecureChatRecyclerViewHolder(headerMsgChatBinding){
        fun bind(){
            headerMsgChatBinding.txtDate.text = changeDateFormat(System.currentTimeMillis(),"dd MMM yyyy")
        }
    }

    class IncomingMessageHolder(private val incomingSecureChatBinding: IncomingSecureChatBinding):SecureChatRecyclerViewHolder(incomingSecureChatBinding){
        fun bind(incomingMsg:SecureChat){
            incomingSecureChatBinding.txtIncomingMsg.text = incomingMsg.message
            incomingSecureChatBinding.txtDate.text = changeDateFormat(incomingMsg.timeStamp.toLong(),"HH:mm")
        }
    }

    class OutgoingMessageHolder(private val outgoingSecureChatBinding: OutgoingSecureChatBinding):SecureChatRecyclerViewHolder(outgoingSecureChatBinding){
        fun bind(outgoingMsg:SecureChat){
            outgoingSecureChatBinding.txtOutgoingMsg.text = outgoingMsg.message
            outgoingSecureChatBinding.txtDate.text = changeDateFormat(outgoingMsg.timeStamp.toLong(),"HH:mm")
        }
    }

    class ChatTopicsMenuViewHolder(private val chatTopicsMenuBinding: ChatTopicsMenuBinding):SecureChatRecyclerViewHolder(chatTopicsMenuBinding){
        fun bind(chatTopicsList:List<ChatTopics>,simpleListener: SimpleListener){
            val spaceItemDecoration = SpaceItemDecoration(30)
            chatTopicsMenuBinding.chatTopicRecyclerview.addItemDecoration(spaceItemDecoration)
            val chatTopicsAdapter = ChatTopicsAdapter(simpleListener)
            chatTopicsMenuBinding.topicsadapter = chatTopicsAdapter
            chatTopicsAdapter.chatTopicList = chatTopicsList
        }

    }
}