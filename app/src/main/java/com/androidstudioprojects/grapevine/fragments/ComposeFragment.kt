package com.androidstudioprojects.grapevine.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.androidstudioprojects.grapevine.Post
import com.androidstudioprojects.grapevine.R
import com.parse.ParseFile
import com.parse.ParseUser
import java.text.DateFormat
import java.util.*

class ComposeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set onClickListeners and logic
        //Need to be able to make posts and retrieve posts
        view.findViewById<Button>(R.id.btn_Submit).setOnClickListener{
            val user = ParseUser.getCurrentUser()
            val name = view.findViewById<EditText>(R.id.et_NameOfEvent).text.toString()
            val description = view.findViewById<EditText>(R.id.et_EventDescription).text.toString()
            val location = view.findViewById<EditText>(R.id.et_EventLocation).text.toString()
            //val dateTime = view.findViewById<DatePicker>(R.id.Create_EventDate)
            val isEvent = view.findViewById<Switch>(R.id.isEventSwitch).isChecked
            submitPost(description, user, location, name, isEvent)
        }
    }
    fun submitPost(description: String, user:ParseUser, location: String, Name: String, isEvent: Boolean){
        val post = Post()
        post.setDescription(description)
        post.setUser(user)
        post.setLocation(location)
        post.setDateTime(DateTime)
        post.setName(Name)
        post.setEvent(isEvent)
        post.saveInBackground{ exc->
            if (exc != null){
                Log.e("ROB", "Error saving post $exc")
                exc.printStackTrace()
            }else{
                Log.i("ROB", "Saved post suc")
                //findViewById<ImageView>(R.id.imageView).setImageBitmap(null)
            }
        }
    }
}