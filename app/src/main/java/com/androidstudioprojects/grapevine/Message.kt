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


@ParseClassName("message")
class Message : ParseObject() {
    fun getSendUser(): ParseUser?{
        return getParseUser(USER_SEND_ID_KEY)
    }
    fun setSendUser(user: ParseUser){
        put(USER_SEND_ID_KEY, user)
    }
    fun getContent():String?{
        return getString(BODY_KEY)
    }
    fun setContent(content: String){
        put(BODY_KEY, content)
    }

    companion object {
        const val USER_SEND_ID_KEY = "sender"
        const val BODY_KEY = "content"
    }
}