package com.androidstudioprojects.grapevine

import android.annotation.SuppressLint
import android.util.Log
import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser
import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.util.*


//Image, User, Description, Location, Date_Time, Name, isEvent, Count
@ParseClassName("Message")
class Message : ParseObject() {

    fun getContent():String?{
        return getString(KEY_CONTENT)
    }
    fun setContent(description: String){
        put(KEY_CONTENT, description)
    }

    fun getSendUser():ParseUser?{
        return getParseUser(KEY_SENDUSER)
    }
    fun setSendUser(user: ParseUser){
        put(KEY_SENDUSER, user)
    }

    fun getReceiveUser():ParseUser?{
        return getParseUser(KEY_RECEIVEUSER)
    }
    fun setReceiveUser(user: ParseUser){
        put(KEY_RECEIVEUSER, user)
    }

    fun getDateCreated(): Date? {
        Log.i("ROB", "Date is ${getDate(KEY_CREATEDAT)}")
        return getDate(KEY_CREATEDAT)
    }

    companion object{
        //Image, User, Description, Location, Date_Time, Name, isEvent, Count
        const val KEY_CREATEDAT = "createdAt"
        const val KEY_SENDUSER = "sender"
        const val KEY_RECEIVEUSER = "receiver"
        const val KEY_CONTENT = "content"

    }
}