package com.nastirlex.cinema.presentation.chats.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.MessageDto
import com.nastirlex.cinema.databinding.ItemDateBinding
import com.nastirlex.cinema.databinding.ItemMyMessageBinding
import com.nastirlex.cinema.databinding.ItemOtherMessageBinding

class ChatMessagesListAdapter(private val userId: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val MY_MESSAGE = 1
    private val OTHER_MESSAGE = 2
    private val DATE = 3


    private val messagesArray: ArrayList<MessageDto> = ArrayList()

    fun addMessage(message: MessageDto) {
        messagesArray.add(message)
        notifyDataSetChanged()
    }

    class MyMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemMyMessageBinding.bind(view)

        fun bind(message: MessageDto, isAvatarHide: Boolean) {
            viewBinding.myMessageTextView.text = message.text
            viewBinding.myMessageAuthorTimeTextView.text =
                message.authorName + " • " + message.creationDateTime.substring(11, 16)
            if (isAvatarHide) {
                viewBinding.myMessageAvatarCardView.visibility = View.INVISIBLE
            } else {
                viewBinding.myMessageAvatarCardView.visibility = View.VISIBLE
                Glide
                    .with(viewBinding.root)
                    .load(message.authorAvatar)
                    .into(viewBinding.myMessageAvatarImageView)
            }

        }
    }

    class OtherMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemOtherMessageBinding.bind(view)

        fun bind(message: MessageDto, isAvatarHide: Boolean) {
            viewBinding.otherMessageTextView.text = message.text
            viewBinding.otherMessageAuthorTimeTextView.text =
                message.authorName + " • " + message.creationDateTime.substring(11, 16)
            if (isAvatarHide) {
                viewBinding.otherMessageAvatarCardView.visibility = View.INVISIBLE
            } else {
                viewBinding.otherMessageAvatarCardView.visibility = View.VISIBLE
                Glide
                    .with(viewBinding.root)
                    .load(message.authorAvatar)
                    .into(viewBinding.otherMessageImageView)
            }

        }
    }

    class DateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemDateBinding.bind(view)

        fun bind(message: MessageDto) {
            viewBinding.dayTextView.text = message.creationDateTime
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (messagesArray[position].authorId == userId) {
            return MY_MESSAGE
        } else if (messagesArray[position].authorId != userId) {
            return OTHER_MESSAGE
        } else {
            return DATE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            1 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_my_message, parent, false)
                return MyMessageViewHolder(view)
            }

            2 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_other_message, parent, false)
                return OtherMessageViewHolder(view)
            }

            3 -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_date, parent, false)
                return DateViewHolder(view)
            }
        }

        throw IllegalArgumentException("Unknown view type");
    }

    override fun getItemCount(): Int {
        return messagesArray.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyMessageViewHolder -> {
                if (position + 1 < messagesArray.size && messagesArray[position + 1].authorId == messagesArray[position].authorId) {
                    val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
                    params.bottomMargin = 6
                    holder.bind(messagesArray[position], true)

                } else {
                    val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
                    params.bottomMargin = 18
                    holder.bind(messagesArray[position], false)
                }

            }

            is OtherMessageViewHolder -> {
                if (position + 1 < messagesArray.size && messagesArray[position + 1].authorId == messagesArray[position].authorId) {
                    val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
                    params.bottomMargin = 6
                    holder.bind(messagesArray[position], true)

                } else {
                    val params = holder.itemView.layoutParams as RecyclerView.LayoutParams
                    params.bottomMargin = 18
                    holder.bind(messagesArray[position], false)
                }
            }

            is DateViewHolder -> {
                holder.bind(messagesArray[position])
            }
        }
    }
}