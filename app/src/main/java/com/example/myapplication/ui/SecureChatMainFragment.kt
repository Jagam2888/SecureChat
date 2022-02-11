package com.example.myapplication.ui

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.R
import com.example.myapplication.adapter.SecureChatAdapter
import com.example.myapplication.data.SecureChat
import com.example.myapplication.databinding.FragmentSecureChatMainBinding
import com.example.myapplication.utils.SimpleListener


class SecureChatMainFragment : Fragment(R.layout.fragment_secure_chat_main) {

    private lateinit var secureChatAdapter: SecureChatAdapter
    private lateinit var chatBinding:FragmentSecureChatMainBinding
    val chatList:MutableLiveData<List<SecureChat>> = MutableLiveData()

    val currentChatList = mutableListOf<SecureChat>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatBinding = FragmentSecureChatMainBinding.bind(view)

        setAdapter()

        chatBinding.btnSend.setOnClickListener(onClickListener())
        chatBinding.backBtn.setOnClickListener { showAlertDialog() }
        chatBinding.chatRecyclerview.addOnLayoutChangeListener(onLayoutChangeListener())
    }

    private fun onClickListener()= View.OnClickListener {
        if (!chatBinding.txtSend.text.isNullOrEmpty()){
            val secureChatSample = SecureChat("OUTGOING",chatBinding.txtSend.text.toString(),System.currentTimeMillis().toString())
            secureChatAdapter.addItem(secureChatSample)
            chatBinding.txtSend.setText("")
        }
    }

    private fun onLayoutChangeListener() = object :View.OnLayoutChangeListener{
        override fun onLayoutChange(
            view: View?,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int
        ) {
            if (bottom < oldBottom){
                chatBinding.chatRecyclerview.postDelayed(Runnable { chatBinding.chatRecyclerview.smoothScrollToPosition(secureChatAdapter.itemCount) },100)
            }
        }

    }

    private fun setAdapter(){
        secureChatAdapter = SecureChatAdapter(object : SimpleListener{
            override fun onClickItem(topics: String) {
                Log.d("item_pos_act",topics)
                removeTopics()
                //Toast.makeText(requireContext(),topics,Toast.LENGTH_LONG).show()
                val secureChatSample = SecureChat("OUTGOING",topics,System.currentTimeMillis().toString())
                secureChatAdapter.addItem(secureChatSample)

                timer()

            }

        })
        chatBinding.chatadapter = secureChatAdapter
        addDefaultItems()


    }

    private fun timer(){
        val timer = object :CountDownTimer(5000,1000){
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                val secureChatSample = SecureChat("INCOMING","Hi I'm Melisha, How can i assist you?",System.currentTimeMillis().toString())
                secureChatAdapter.addItem(secureChatSample)
            }

        }
        timer.start()
    }

    private fun removeTopics(){
        currentChatList.removeAt(2)
        chatList.value = currentChatList
        secureChatAdapter.secureChatList = chatList.value!!
    }

    private fun addDefaultItems(){
        val secureChatSample = SecureChat("HEADER","",System.currentTimeMillis().toString())
        val secureChatSample1 = SecureChat("INCOMING","Hello Mr. Jaga, how may we help you today?\n\nChoose one of the topics below...",System.currentTimeMillis().toString())
        val secureChatSample2 = SecureChat("TOPIC","",System.currentTimeMillis().toString())
        //val list = mutableListOf<SecureChat>()
        currentChatList.add(secureChatSample)
        currentChatList.add(secureChatSample1)
        currentChatList.add(secureChatSample2)
        chatList.value = currentChatList
        secureChatAdapter.secureChatList = chatList.value!!
    }

    //private fun onBackClickListener() = View.OnClickListener { showAlertDialog() }

    private fun showAlertDialog(){
        val customLayout = layoutInflater.inflate(R.layout.show_secure_chat_dialog,null)

        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setView(customLayout)
        val dialog = alertDialog.create()
        dialog.show()
    }
}