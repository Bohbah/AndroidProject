package com.androidstudioprojects.grapevine

import android.provider.Settings.Global.getString
import com.parse.*
import android.util.Log
import java.io.File
import java.util.*


// Description : String
// User : User
@ParseClassName("Event")
class Event : ParseObject() {

    // getters and setters
    fun getTitle(): String? {
        return getString(KEY_TITLE)
    }
    fun setTitle(title: String) {
        put(KEY_TITLE, title)
    }

    fun getDescription(): String? {
        return getString(KEY_DESCRIPTION)
    }
    fun setDescription(description: String) {
        put(KEY_DESCRIPTION, description)
    }
    fun getImage(): ParseFile?{
        return getParseFile(KEY_IMAGE)
    }
    fun setImage(parseFile: ParseFile){
        put(KEY_IMAGE, parseFile)
    }

    fun getUser(): ParseUser? {
        return getParseUser(Post.KEY_USER)
    }
    fun setUser(user: ParseUser) {
        put(Post.KEY_USER, user)
    }

    fun getDate(): Date? {
        return getDate(KEY_DATETIME)
    }
    fun getLocation(): String? {
        return getDate(KEY_LOCATION).toString()
    }
    // Keys
    companion object {
        const val KEY_TITLE = "Name"
        const val KEY_DESCRIPTION = "Description"
        const val KEY_PFP = "pfp"
        const val KEY_USER = "User"
        const val KEY_IMAGE = "Image"
        const val KEY_LOCATION = "Location"
        const val KEY_DATETIME = "Date_Time"
    }
}