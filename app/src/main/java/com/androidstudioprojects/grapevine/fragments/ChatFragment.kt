package com.androidstudioprojects.grapevine.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidstudioprojects.grapevine.Post
import com.androidstudioprojects.grapevine.R
import com.parse.ParseClassName
import com.parse.ParseQuery
import com.parse.ParseUser

class ChatFragment : Fragment() {
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
        val user = ParseUser.getCurrentUser()
        Log.i("Rob", user.get("MessageList").toString())
        var MessageList: MutableList<String> = user.get("MessageList") as MutableList<String>
        for(i in 0 until MessageList.size){
            Log.i("Rob", MessageList[i])
        }

    }
}