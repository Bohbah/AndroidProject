package com.androidstudioprojects.grapevine

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class EventAdapter(val context: Context, val events: List<Event>)
    : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.calendar_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventAdapter.ViewHolder, position: Int) {
        val event = events.get(position)
        holder.bind(event)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //TODO Org
        val CalEventTitle: TextView
        val CalEventDetail: TextView
        val CalEventDateTime: TextView
        val calEventLocation: TextView

        init {
            //TODO Org
            CalEventTitle = itemView.findViewById(R.id.CalEventTitle)
            CalEventDetail = itemView.findViewById(R.id.CalEventDetail)
            CalEventDateTime = itemView.findViewById(R.id.CalEventDateTime)
            calEventLocation = itemView.findViewById(R.id.calEventLocation)
        }

        fun bind(event: Event) {
            CalEventTitle.text = event.getTitle()
            CalEventDetail.text = event.getDescription()
            CalEventDateTime.text = event.getDate().toString()
            calEventLocation.text = event.getLocation()
        }
    }
}