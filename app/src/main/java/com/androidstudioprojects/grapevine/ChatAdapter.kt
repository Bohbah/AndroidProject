package com.androidstudioprojects.grapevine

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.parse.ParseQuery
import com.parse.ParseUser


class ChatAdapter(private val mContext: Context, private val mUserId: String, private val mMessages: List<Message>)
    : RecyclerView.Adapter<ChatAdapter.MessageViewHolder?>() {
    override fun getItemCount(): Int {
        return mMessages.size
    } // TODO: create onCreateViewHolder and onBindViewHolder later (covered in the next few chapters...)
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = mMessages[position]
        holder.bindMessage(message)
    }
    private val MESSAGE_OUTGOING = 123
    private val MESSAGE_INCOMING = 321

    override fun getItemViewType(position: Int): Int {
        return if (isMe(position)) {
            MESSAGE_OUTGOING
        } else {
            MESSAGE_INCOMING
        }
    }
    private fun isMe(position: Int): Boolean {
        val message = mMessages[position]
        return message.getSendUser() != null && message.getSendUser()!!.equals(mUserId)
    }

    abstract class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("NotConstructor")
        fun MessageViewHolder(itemView: View) {
            super.itemView
        }
        abstract fun bindMessage(message: Message?)
    }

    class IncomingMessageViewHolder(itemView: View) :
        MessageViewHolder(itemView) {
        var imageOther: ImageView
        var body: TextView
        var name: TextView
        fun bindMessage(message: Message) {

            // Delete the following comment:
            // TODO: implement later
            /*** START OF CHANGE  */
            Glide.with(mContext)
                .load(getProfileUrl(message.getUserId()))
                .circleCrop() // create an effect of a round profile picture
                .into(imageOther)
            body.setText(message.getBody())
            name.setText(message.getUserId()) // in addition to message show user ID
            /*** END OF CHANGE  */
        }

        init {
            imageOther = itemView.findViewById<View>(R.id.ivProfileOther) as ImageView
            body = itemView.findViewById<View>(R.id.tvBody) as TextView
            name = itemView.findViewById<View>(R.id.tvName) as TextView
        }
    }

    class OutgoingMessageViewHolder(itemView: View) :
        MessageViewHolder(itemView) {
        var imageMe: ImageView
        var body: TextView
        fun bindMessage(message: Message) {

            // Delete the following comment:
            // TODO: implement later
            /*** START OF CHANGE  */
            Glide.with(mContext)
                .load(getProfileUrl(message.getUserId()))
                .circleCrop() // create an effect of a round profile picture
                .into(imageMe)
            body.setText(message.getBody())
            /*** END OF CHANGE  */
        }

        init {
            imageMe = itemView.findViewById<View>(R.id.ivProfileMe) as ImageView
            body = itemView.findViewById<View>(R.id.tvBody) as TextView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder? {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        return if (viewType == MESSAGE_INCOMING) {
            val contactView: View = inflater.inflate(R.layout.message_outgoing, parent, false)
            IncomingMessageViewHolder(contactView)
        } else if (viewType == MESSAGE_OUTGOING) {
            val contactView: View = inflater.inflate(R.layout.message_outgoing, parent, false)
            OutgoingMessageViewHolder(contactView)
        } else {
            throw IllegalArgumentException("Unknown view type")
        }
    }

    fun setupMessagePosting() {
        etMessage = findViewById(R.id.etMessage) as EditText
        ibSend = findViewById(R.id.ibSend) as ImageButton
        rvChat = findViewById(R.id.rvChat) as RecyclerView
        mMessages = ArrayList()
        mFirstLoad = true
        val userId = ParseUser.getCurrentUser().objectId
        mAdapter = ChatAdapter(this@ChatActivity, userId, mMessages)
        rvChat.setAdapter(mAdapter)

        // associate the LayoutManager with the RecylcerView
        val linearLayoutManager = LinearLayoutManager(this@ChatActivity)
        rvChat.setLayoutManager(linearLayoutManager)

        // When send button is clicked, create message object on Parse
        ibSend.setOnClickListener(View.OnClickListener {
            val data: String = etMessage.getText().toString()
            //ParseObject message = ParseObject.create("Message");
            //message.put(Message.USER_ID_KEY, userId);
            //message.put(Message.BODY_KEY, data);
            // Using new `Message` Parse-backed model now
            val message = Message()
            message.setUserId(ParseUser.getCurrentUser().objectId)
            message.setBody(data)
            message.saveInBackground(object : SaveCallback() {
                fun done(e: ParseException?) {
                    Toast.makeText(
                        this@ChatActivity, "Successfully created message on Parse",
                        Toast.LENGTH_SHORT
                    ).show()
                    refreshMessages()
                }
            })
            etMessage.setText(null)
        })
    }

    // Query messages from Parse so we can load them into the chat adapter
    fun refreshMessages() {
        // Construct query to execute
        // Construct query to execute
        val query = ParseQuery.getQuery(
            Message::class.java
        )
        // Configure limit and sort order
        // Configure limit and sort order
        query.limit = 50

        // get the latest 50 messages, order will show up newest to oldest of this group

        // get the latest 50 messages, order will show up newest to oldest of this group
        query.orderByDescending("createdAt")
        // Execute query to fetch all messages from Parse asynchronously
        // This is equivalent to a SELECT query with SQL
        // Execute query to fetch all messages from Parse asynchronously
        // This is equivalent to a SELECT query with SQL
        query.findInBackground { messages, e ->
            if (e == null) {
                mMessages.clear()
                mMessages.addAll(messages)
                mAdapter.notifyDataSetChanged() // update adapter
                // Scroll to the bottom of the list on initial load
                if (mFirstLoad) {
                    rvChat.scrollToPosition(0)
                    mFirstLoad = false
                }
            } else {
                Log.e("message", "Error Loading Messages$e")
            }
        }
    }
}