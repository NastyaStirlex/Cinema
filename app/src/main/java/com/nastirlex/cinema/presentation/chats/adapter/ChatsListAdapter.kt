package com.nastirlex.cinema.presentation.chats.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.dto.ChatDto
import com.nastirlex.cinema.databinding.ItemChatsListBinding

class ChatsListAdapter(private val chats: List<ChatDto>) :
    RecyclerView.Adapter<ChatsListAdapter.ChatsListViewHolder>() {

    class ChatsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val viewBinding = ItemChatsListBinding.bind(view)

        fun bind(chat: ChatDto) {
            if (chat.messageDto.authorAvatar != null)
                Glide
                    .with(viewBinding.root)
                    .load(chat.messageDto.authorAvatar)
                    .into(viewBinding.lastMessageAvatarImageView)

            viewBinding.chatNameTextView.text = chat.chatName

            viewBinding.lastMessageTextView.text =
                "<font color=#AFAFAF>${chat.messageDto.authorName}</font> <font color=#FFFFFF>${chat.messageDto.text}</font>"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_chats_list, parent, false)

        return ChatsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun onBindViewHolder(holder: ChatsListViewHolder, position: Int) {
        holder.bind(chats[position])
    }
}