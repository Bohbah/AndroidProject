package com.androidstudioprojects.grapevine.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.androidstudioprojects.grapevine.Post
import com.androidstudioprojects.grapevine.R
import com.parse.ParseUser
import java.time.LocalDate

class ComposeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false)
    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set onClickListeners and logic
        //Need to be able to make posts and retrieve posts
        view.findViewById<Button>(R.id.btn_Submit).setOnClickListener{
            val user = ParseUser.getCurrentUser()
            val name = view.findViewById<EditText>(R.id.et_NameOfEvent).text.toString()
            val description = view.findViewById<EditText>(R.id.et_EventDescription).text.toString()
            val location = view.findViewById<EditText>(R.id.et_EventLocation).text.toString()
            val isEvent = view.findViewById<Switch>(R.id.isEventSwitch).isChecked
            var day = view.findViewById<DatePicker>(R.id.Create_EventDate).dayOfMonth.toString()
            var month = view.findViewById<DatePicker>(R.id.Create_EventDate).month.toString()
            var year = view.findViewById<DatePicker>(R.id.Create_EventDate).year.toString()
            //Log.i("ROB", "day: $day month: $month year: $year")
            var hour = view.findViewById<TimePicker>(R.id.Create_EventTime).hour.toString()
            var minute = view.findViewById<TimePicker>(R.id.Create_EventTime).minute.toString()
            if (day < 10.toString()){
                day = "0$day"
            }
            if (month < 10.toString()){
                month = "0$month"
            }
            if (hour < 10.toString()){
                hour = "0$hour"
            }
            if (minute < 10.toString()){
                minute = "0$minute"
            }
            val dateString = "$year-$month-$day"+"T"+"$hour:$minute:00.00"
            //Log.i("ROB", "hour: $hour minute: $minute")
            Log.i("ROB", dateString)

            submitPost(description, user, location, dateString, name, isEvent)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun submitPost(description: String, user:ParseUser, location: String, dateString: String, Name: String, isEvent: Boolean){
        val post = Post()
        post.setDescription(description)
        post.setUser(user)
        post.setLocation(location)
        //post.setDateTime(dateString)
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