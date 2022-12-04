package com.androidstudioprojects.grapevine

import com.parse.*
import org.parceler.Parcel


// Description : String
// User : User
@Parcel
@ParseClassName("Post")

public class Post : ParseObject() {

    //added for Parcelable
    public class Post(){}

    // getters and setters
    fun getDescription(): String? {
        return getString(KEY_DESCRIPTION)
    }
    fun setDescription(description: String) {
        put(KEY_DESCRIPTION, description)
    }

    fun getEvent(): Boolean? {
        return getBoolean(KEY_EVENT)
    }
    fun setEvent(isEvent: Boolean) {
        put(KEY_EVENT, isEvent)
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
        const val KEY_EVENT = "isEvent"
        const val KEY_USER = "User"
    }
}