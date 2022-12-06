package com.androidstudioprojects.grapevine

import android.content.Context
import android.content.Intent
import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.androidstudioprojects.grapevine.fragments.ProfileFragment
import com.bumptech.glide.Glide
import org.parceler.Parcels


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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val ivPFP: de.hdodenhof.circleimageview.CircleImageView
        //TODO Org
        val tvUsername: TextView
        val ibLike: ImageButton
        val tvDescription: TextView

        init {
            ivPFP = itemView.findViewById(R.id.ivPFP)
            //TODO Org
            tvUsername = itemView.findViewById(R.id.tvUsername)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            ibLike = itemView.findViewById(R.id.ibLike)
            itemView.setOnClickListener(this)
        }

        fun bind(post: Post) {
            tvDescription.text = post.getDescription()
            tvUsername.text = post.getUser()?.username

            Glide.with(itemView.context)
                .load(post.getUser()?.get("pfp"))
                .into(ivPFP)
        }

        override fun onClick(p0: View?) {
                val post = posts[adapterPosition]
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
                //val intent = Intent(context, ProfileFragment::class.java)
                //intent.putExtra("username", tvUsername)
                //context.startActivity(intent)

        }
    }
}