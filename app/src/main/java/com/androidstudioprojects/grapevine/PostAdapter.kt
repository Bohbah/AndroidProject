package com.androidstudioprojects.grapevine

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


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

        init {
            ivPFP = itemView.findViewById(R.id.ivPFP)
            ivOrg = itemView.findViewById(R.id.ivOrg)
            tvUsername = itemView.findViewById(R.id.tvUsername)
            tvDescription = itemView.findViewById(R.id.tvDescription)
        }

        fun bind(post: Post) {
            tvDescription.text = post.getDescription()
            tvUsername.text = post.getUser()?.username

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