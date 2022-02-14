package com.example.myapplication.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ObservableInt
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.ChatTopics
import com.example.myapplication.data.SecureChat
import com.example.myapplication.databinding.ChatTopicsMenuBinding
import com.example.myapplication.databinding.HeaderMsgChatBinding
import com.example.myapplication.databinding.IncomingSecureChatBinding
import com.example.myapplication.databinding.OutgoingSecureChatBinding
import com.example.myapplication.utils.SimpleListener
import java.lang.IllegalArgumentException

class SecureChatAdapter(
    private val context: Context,
    private val simpleListener: SimpleListener

): RecyclerView.Adapter<SecureChatRecyclerViewHolder>() {


    val scrollTo = ObservableInt()
    var secureChatList = listOf<SecureChat>()
    set(value) {
        field = value
        scrollTo.set(field.size - 1)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SecureChatRecyclerViewHolder {
        return when(viewType){
            R.layout.incoming_secure_chat -> SecureChatRecyclerViewHolder.IncomingMessageHolder(
                IncomingSecureChatBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.outgoing_secure_chat -> SecureChatRecyclerViewHolder.OutgoingMessageHolder(
                OutgoingSecureChatBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.header_msg_chat -> SecureChatRecyclerViewHolder.HeaderMessageHolder(
                HeaderMsgChatBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.chat_topics_menu -> SecureChatRecyclerViewHolder.ChatTopicsMenuViewHolder(
                ChatTopicsMenuBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Error")
        }
    }

    override fun onBindViewHolder(holder: SecureChatRecyclerViewHolder, position: Int) {
        when(secureChatList[position].direction){
            "INCOMING" -> (holder as SecureChatRecyclerViewHolder.IncomingMessageHolder).bind(secureChatList[position])
            "OUTGOING" -> (holder as SecureChatRecyclerViewHolder.OutgoingMessageHolder).bind(secureChatList[position])
            "TOPIC" -> (holder as SecureChatRecyclerViewHolder.ChatTopicsMenuViewHolder).bind(topicsList(),simpleListener,context)
            "HEADER" -> (holder as SecureChatRecyclerViewHolder.HeaderMessageHolder).bind()
            else -> throw IllegalArgumentException("Error")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(secureChatList[position].direction){
            "INCOMING" -> R.layout.incoming_secure_chat
            "OUTGOING" -> R.layout.outgoing_secure_chat
            "HEADER" -> R.layout.header_msg_chat
            "TOPIC" -> R.layout.chat_topics_menu
            else -> throw IllegalArgumentException("Error")
        }
    }

    override fun getItemCount() = secureChatList.size

    fun addItem(secureChat: SecureChat){
        val tempList = mutableListOf<SecureChat>()
        tempList.addAll(secureChatList)
        tempList.add(secureChat)
        secureChatList = tempList
    }


    private fun topicsList():List<ChatTopics>{
        val topicsList = mutableListOf<ChatTopics>()
        topicsList.add(ChatTopics("Credit Card"))
        topicsList.add(ChatTopics("Loan/Financing"))
        //topicsList.add(ChatTopics("Debit Card"))
        topicsList.add(ChatTopics("General/Others"))
        return topicsList
    }
}