package com.androidstudioprojects.grapevine.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidstudioprojects.grapevine.*
import com.androidstudioprojects.grapevine.R
import com.parse.*


class ChatFragment : Fragment() {

    lateinit var chatsRecyclerView: RecyclerView
    lateinit var adapter: ChatAdapter

    var allMessages: MutableList<Message> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chatlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set onClickListeners and logic
        chatsRecyclerView = view.findViewById(R.id.rvChat)

        adapter = ChatAdapter(requireContext(), allMessages)
        chatsRecyclerView.adapter = adapter

        chatsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryMessages()


        ParseObject.registerSubclass(Message::class.java)
        val USER_SEND_ID_KEY = "sender"
        val BODY_KEY = "content"
        // Find the text field and button
        var etMessage = view.findViewById<EditText>(R.id.etMessage)
        var ibSend = view.findViewById<ImageButton>(R.id.ibSend)

        ibSend.setOnClickListener {
            val data: String = etMessage.text.toString()
            val message = Message()
            message.setSendUser(ParseUser.getCurrentUser())
            message.setContent(data)
            message.saveInBackground(object : SaveCallback {
                override fun done(e: ParseException?) {
                    if (e == null) {
                        Toast.makeText(activity, "Message Sent!", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e("ROB", "Failed to save message", e)
                    }
                }
            })
            etMessage.text = null
            queryMessages()
        }
    }
    open fun queryMessages() {
        val query: ParseQuery<Message> = ParseQuery.getQuery(Message::class.java)
        query.include(Message.USER_SEND_ID_KEY)
        query.findInBackground(object : FindCallback<Message> {
            override fun done(messages: MutableList<Message>?, e: ParseException?) {
                if(e != null) {
                    Log.e(HomeFragment.TAG, "Error fetching posts: " + e.message)
                } else {
                    if(messages != null) {
                        //Log.i("ROB", messages.toString())
                        allMessages.clear()
                        allMessages.addAll(messages)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
}
