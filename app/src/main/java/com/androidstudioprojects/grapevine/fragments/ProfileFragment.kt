package com.androidstudioprojects.grapevine.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidstudioprojects.grapevine.*
import com.androidstudioprojects.grapevine.R
import com.bumptech.glide.Glide
import com.parse.*


class ProfileFragment : Fragment() {

    lateinit var postsRecyclerView: RecyclerView
    lateinit var adapter: PostAdapter
    var allPosts: MutableList<Post> = mutableListOf()
    lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.view_profile, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //search bar stuff
        searchView = view.findViewById(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // query database for username
                val q = ParseUser.getQuery()
                q.whereEqualTo("username", query)
                q.findInBackground { users, e ->
                    if (e == null) {
                        if (users.isEmpty()){
                            Toast.makeText(context, "No users by that username found.", Toast.LENGTH_SHORT).show()
                        }
                        for (user1 in users) {
                            //Toast.makeText(context, user1.toString(), Toast.LENGTH_SHORT).show()
                            val user = user1
                            val NameView= view.findViewById<TextView>(R.id.tvViewName)
                            NameView.text = user.get("name").toString()
                            val OrgView = view.findViewById<TextView>(R.id.tvViewOrganization)
                            OrgView.text = user.get("org").toString()
                            val JoinedView = view.findViewById<TextView>(R.id.tvSemester)
                            JoinedView.text = user.get("joined").toString()
                            val PositionView = view.findViewById<TextView>(R.id.tvPositionName)
                            PositionView.text = user.get("role").toString()
                            val profilePic = view.findViewById<ImageView>(R.id.ivProfileImage)
                            val orgPic = view.findViewById<ImageView>(R.id.ivOrgImage)
                            Glide.with(view.context)
                                .load(user.getParseFile("pfp")?.url)
                                .circleCrop()
                                .into(profilePic)
                            Glide.with(view.context)
                                .load(user.getParseFile("orgPic")?.url)
                                .circleCrop()
                                .into(orgPic)

                            // See Recent Posts
                            postsRecyclerView = view.findViewById(R.id.rvRecentPosts)
                            adapter = PostAdapter(requireContext(), allPosts)
                            postsRecyclerView.adapter = adapter
                            postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

                            //initialize RV posts
                            val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
                            query.include(Post.KEY_USER)
                            query.whereEqualTo(Post.KEY_USER, user1)
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
                                            allPosts.clear()
                                            allPosts.addAll(posts)
                                            adapter.notifyDataSetChanged()
                                        }
                                    }
                                }
                            })
                        }
                    } else {
                        Toast.makeText(context, "Major error searching for user.", Toast.LENGTH_SHORT).show()
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val btnEdit = view.findViewById<Button>(R.id.btnEdit)
                btnEdit.visibility = View.INVISIBLE
                return false
            }
        })

        //switch to edit profile
        view.findViewById<Button>(R.id.btnEdit).setOnClickListener {
            val i = Intent(activity, SignupActivity::class.java)
            startActivity(i)
        }
        view.findViewById<Button>(R.id.btnLogout).setOnClickListener {
            val i = Intent(activity, LoginActivity::class.java)
            ParseUser.logOut()
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
        val profilePic = view.findViewById<ImageView>(R.id.ivProfileImage)
        val orgPic = view.findViewById<ImageView>(R.id.ivOrgImage)
        Glide.with(view.context)
            .load(user.getParseFile("pfp")?.url)
            .circleCrop()
            .into(profilePic)
        Glide.with(view.context)
            .load(user.getParseFile("orgPic")?.url)
            .circleCrop()
            .into(orgPic)

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