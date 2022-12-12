package com.androidstudioprojects.grapevine

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseQuery
import kotlinx.coroutines.newFixedThreadPoolContext


class PostAdapter(val context: Context, val posts: List<Post>)
    : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)

        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPFP: ImageView
        val ivOrg: ImageView
        val tvUsername: TextView
        val tvDescription: TextView
        val tvLocation: TextView
        val tvTitle: TextView
        val tvCount: TextView
        val ibLike: ImageButton

        init {
            ivPFP = itemView.findViewById(R.id.ivPFP)
            ivOrg = itemView.findViewById(R.id.ivOrg)
            tvUsername = itemView.findViewById(R.id.tvUsername)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            tvLocation = itemView.findViewById(R.id.tvLocation)
            tvTitle = itemView.findViewById(R.id.tvTitle)

            tvCount = itemView.findViewById(R.id.tvCount)
            ibLike = itemView.findViewById(R.id.ibLike)
        }


        fun bind(post: Post) {
            tvUsername.text = post.getUser()?.username
            tvCount.text = post.getCount().toString()
            if (post.getEvent() == true){
                tvTitle.text = post.getName()
                tvLocation.text = post.getLocation()
                tvDescription.text = post.getDescription()
            }else{
                tvTitle.text = post.getDescription()
            }

            itemView.findViewById<ImageButton>(R.id.ibLike).setOnClickListener {
                tvCount.text = (post.getCount()?.plus(1)).toString()
                post.setCount(post.getCount()?.plus(1))
                post.getCount()?.let { it1 -> post.put("Count", it1) }
                post.saveInBackground()
            }


            Glide.with(itemView.context)
                .load(post.getUser()?.getParseFile("pfp")?.url)
                .circleCrop()
                .into(ivPFP)
            Glide.with(itemView.context)
                .load(post.getUser()?.getParseFile("orgPic")?.url)
                .circleCrop()
                .into(ivOrg)
        }
    }
}