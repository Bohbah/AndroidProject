package com.androidstudioprojects.grapevine.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidstudioprojects.grapevine.Post
import com.androidstudioprojects.grapevine.PostAdapter
import com.androidstudioprojects.grapevine.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

class CalendarFragment : Fragment() {

    lateinit var eventsRecyclerView: RecyclerView
    lateinit var adapter: PostAdapter

    var allEvents: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set onClickListeners and logic

        eventsRecyclerView = view.findViewById(R.id.rvEventFeed)

        adapter = PostAdapter(requireContext(), allEvents)
        eventsRecyclerView.adapter = adapter

        eventsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryPosts()
    }

    open fun queryPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.whereEqualTo(Post.KEY_ISEVENT, true)
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if(e != null) {
                    Log.e(TAG, "Error fetching posts: " + e.message)
                } else {
                    if(posts != null) {
                        for (post in posts) {
                            Log.i(TAG, "Post: " + post.getDescription() + " , username: " +
                                    post.getUser()?.username)
                        }

                        allEvents.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }

    companion object {
        const val TAG = "CalendarFragment"
    }
}