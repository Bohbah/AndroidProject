package com.androidstudioprojects.grapevine

import com.parse.*
import java.util.*


// Description : String
// User : User
@ParseClassName("Post")
class Post : ParseObject() {

    // getters and setters
    fun getDescription(): String? {
        return getString(KEY_DESCRIPTION)
    }
    fun setDescription(description: String) {
        put(KEY_DESCRIPTION, description)
    }

    fun getLocation(): String? {
        return getString(KEY_LOCATION)
    }
    fun setLocation(Location: String) {
        put(KEY_LOCATION, Location)
    }

    fun getDateTime(): String? {
        return getString(KEY_DATETIME).toString()
    }
    fun setDateTime(Date_Time: Date) {
        put(KEY_DATETIME, Date_Time)
    }

    fun getName(): String? {
        return getString(KEY_NAME)
    }
    fun setName(Name: String) {
        put(KEY_NAME, Name)
    }

    fun getEvent(): String? {
        return getString(KEY_ISEVENT)
    }
    fun setEvent(isEvent: Boolean) {
        put(KEY_NAME, isEvent)
    }

    fun getUser(): ParseUser? {
        return getParseUser(KEY_USER)
    }
    fun setUser(user: ParseUser) {
        put(KEY_USER, user)
    }

    // Keys
    companion object {
        const val KEY_DESCRIPTION = "Description"
        const val KEY_PFP = "pfp"
        const val KEY_USER = "User"
        const val KEY_LOCATION = "Location"
        const val KEY_DATETIME = "Date_Time"
        const val KEY_NAME = "Name"
        const val KEY_ISEVENT = "isEvent"
    }
}