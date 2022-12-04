package com.androidstudioprojects.grapevine

import android.util.Log
import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
import java.io.File
import java.util.*


//Image, User, Description, Location, Date_Time, Name, isEvent, Count
@ParseClassName("Post")
class Post : ParseObject() {

    fun getDescription():String?{
        return getString(KEY_DESCRIPTION)
    }
    fun setDescription(description: String){
        put(KEY_DESCRIPTION, description)
    }

    fun getImage(): ParseFile?{
        return getParseFile(KEY_IMAGE)
    }
    fun setImage(parseFile: ParseFile){
        put(KEY_IMAGE, parseFile)
    }

    fun getUser():ParseUser?{
        return getParseUser(KEY_USER)
    }
    fun setUser(user: ParseUser){
        put(KEY_USER, user)
    }

    fun getDateCreated(): Date? {
        Log.i("ROB", "Date is ${getDate(KEY_CREATEDAT)}")
        return getDate(KEY_CREATEDAT)
    }

    fun getDateTime(): Date? {
        Log.i("ROB", "Date is ${getDate(KEY_CREATEDAT)}")
        return getDate(KEY_DATE_TIME)
    }

    fun getLocation():String?{
        return getString(KEY_LOCATION)
    }
    fun setLocation(location: String){
        put(KEY_LOCATION, location)
    }

    fun getName():String?{
        return getString(KEY_NAME)
    }
    fun setName(Name: String){
        put(KEY_NAME, Name)
    }

    fun getEvent():String?{
        return getString(KEY_ISEVENT)
    }
    fun setEvent(isEvent: Boolean){
        put(KEY_ISEVENT, isEvent)
    }

    fun getCount():String?{
        return getString(KEY_COUNT)
    }
    fun setCount(Count: String){
        put(KEY_COUNT, Count)
    }


    companion object{
        //Image, User, Description, Location, Date_Time, Name, isEvent, Count
        const val KEY_CREATEDAT = "createdAt"
        const val KEY_IMAGE = "Image"
        const val KEY_USER = "User"
        const val KEY_DESCRIPTION = "Description"
        const val KEY_LOCATION = "Location"
        const val KEY_DATE_TIME = "Date_Time"
        const val KEY_NAME = "Name"
        const val KEY_ISEVENT = "isEvent"
        const val KEY_COUNT = "Count"

    }
}