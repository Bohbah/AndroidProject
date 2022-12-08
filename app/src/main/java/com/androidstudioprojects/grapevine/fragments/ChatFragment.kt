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
import com.androidstudioprojects.grapevine.R
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseUser
import com.parse.SaveCallback


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
        val USER_SEND_ID_KEY = "sender"
        val BODY_KEY = "content"
        // Find the text field and button
        var etMessage = view.findViewById<EditText>(R.id.etMessage)
        var ibSend = view.findViewById<ImageButton>(R.id.ibSend)

        ibSend.setOnClickListener {
            val data: String = etMessage.text.toString()
            val message = ParseObject.create("message")
            message.put(USER_SEND_ID_KEY, ParseUser.getCurrentUser())
            message.put(BODY_KEY, data)
            message.saveInBackground(object : SaveCallback {
                override fun done(e: ParseException?) {
                    if (e == null) {
                        Toast.makeText(activity, "Successfully created message on Parse", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e("ROB", "Failed to save message", e)
                    }
                }
            })
            etMessage.text = null
        }
    }
}
