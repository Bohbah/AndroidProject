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
import java.lang.String.format
import java.util.*
import java.text.SimpleDateFormat
import java.text.Format


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
            //variables
            val user = ParseUser.getCurrentUser()
            val name = view.findViewById<EditText>(R.id.et_NameOfEvent).text.toString()
            val description = view.findViewById<EditText>(R.id.et_EventDescription).text.toString()
            val location = view.findViewById<EditText>(R.id.et_EventLocation).text.toString()
            val isEvent = view.findViewById<Switch>(R.id.isEventSwitch).isChecked
            //date
            val day = view.findViewById<DatePicker>(R.id.Create_EventDate).dayOfMonth
            val month = view.findViewById<DatePicker>(R.id.Create_EventDate).month
            val year = view.findViewById<DatePicker>(R.id.Create_EventDate).year
            //time
            val hour = view.findViewById<TimePicker>(R.id.Create_EventTime).hour
            val minute = view.findViewById<TimePicker>(R.id.Create_EventTime).minute
            //calendar
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day, hour, minute, 0)
            val tv = calendar.timeInMillis

            Log.i("ROB", "Time in milis: $tv")
            val longArray = LongArray(6) { it.toLong() }
            longArray[0] = day.toLong()
            longArray[1] = month.toLong()
            longArray[2] = year.toLong()
            longArray[3] = hour.toLong()
            longArray[4] = minute.toLong()
            longArray[5] = tv
            submitPost(description, user, location,name, longArray, isEvent)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun submitPost(description: String, user:ParseUser, location: String, Name: String, timeVal: LongArray, isEvent: Boolean){
        val post = Post()
        post.setDescription(description)
        post.setUser(user)
        post.setLocation(location)
        post.setName(Name)
        // TODO post.setDateTime(timeVal)
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