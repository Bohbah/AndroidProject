package com.androidstudioprojects.grapevine.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidstudioprojects.grapevine.Event
import com.androidstudioprojects.grapevine.EventAdapter
import com.androidstudioprojects.grapevine.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

class CalendarFragment : Fragment() {

    lateinit var postsRecyclerView: RecyclerView
    lateinit var adapter: EventAdapter

    var allEvents: MutableList<Event> = mutableListOf()

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

        postsRecyclerView = view.findViewById(R.id.rvCalEvents)

        adapter = EventAdapter(requireContext(), allEvents)
        postsRecyclerView.adapter = adapter

        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        queryEvents()
    }

    open fun queryEvents() {
        val query: ParseQuery<Event> = ParseQuery.getQuery(Event::class.java)
        query.include(Event.KEY_USER)
        query.findInBackground(object : FindCallback<Event> {
            override fun done(events: MutableList<Event>?, e: ParseException?) {
                if(e != null) {
                    Log.e(TAG, "Error fetching events: " + e.message)
                } else {
                    if(events != null) {
                        for (event in events) {
                            Log.i(
                                TAG, "EVENTS: " + event.getDescription() + " , title: " + event.getTitle())
                        }

                        allEvents.addAll(events)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
    companion object {
        const val TAG = "CALENDAR FRAG"
    }
}