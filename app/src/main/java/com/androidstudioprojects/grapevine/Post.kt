package com.androidstudioprojects.grapevine

import com.parse.*


// Description : String
// Image : File
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

    fun getPFP(): ParseFile? {
        return getParseFile(KEY_PFP)
    }
    fun setPFP(parsefile: ParseFile) {
        put(KEY_PFP, parsefile)
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
        const val KEY_PFP = "Image"
        const val KEY_USER = "User"
    }
}