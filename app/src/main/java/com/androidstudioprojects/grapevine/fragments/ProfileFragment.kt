package com.androidstudioprojects.grapevine.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidstudioprojects.grapevine.Post
import com.androidstudioprojects.grapevine.PostAdapter
import com.androidstudioprojects.grapevine.R
import com.androidstudioprojects.grapevine.SignupActivity
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment : Fragment() {

    lateinit var postsRecyclerView: RecyclerView
    lateinit var adapter: PostAdapter

    var allPosts: MutableList<Post> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //switch to edit profile
        view.findViewById<Button>(R.id.btnEdit).setOnClickListener {
            val i = Intent(activity, SignupActivity::class.java)
            startActivity(i)
        }
        //set profile text
        val user = ParseUser.getCurrentUser()
        val NameView= view.findViewById<TextView>(R.id.tvViewName)
        NameView.text = user.get("name").toString()
        val OrgView = view.findViewById<TextView>(R.id.tvViewOrganization)
        OrgView.text = user.get("org").toString()
        val JoinedView = view.findViewById<TextView>(R.id.tvSemester)
        JoinedView.text = user.get("joined").toString()
        val PositionView = view.findViewById<TextView>(R.id.tvPositionName)
        PositionView.text = user.get("role").toString()

        // See Recent Posts
        postsRecyclerView = view.findViewById(R.id.rvRecentPosts)
        adapter = PostAdapter(requireContext(), allPosts)
        postsRecyclerView.adapter = adapter
        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        queryPosts()
    }

    fun queryPosts(){
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())
        query.addDescendingOrder("createdAt")
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

                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
    companion object {
        const val TAG = "ProfileFragment"
    }
}