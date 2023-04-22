package com.nastirlex.cinema.presentation.chats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cinema.data.dto.ChatDto
import com.nastirlex.cinema.databinding.FragmentChatListBinding
import com.nastirlex.cinema.presentation.chats.adapter.ChatsListAdapter

class ChatListFragment : Fragment() {

    private lateinit var binding: FragmentChatListBinding

    private val chatListViewModel by lazy { ChatListViewModel(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatListBinding.inflate(inflater, container, false)

        setupChatsObserver()
        setupOnBackButtonClick()

        return binding.root
    }

    private fun setupChatsObserver() {
        val chatsObserver: Observer<List<ChatDto>> = Observer {
            setupChatsRecyclerView(it)
        }

        chatListViewModel.chats.observe(viewLifecycleOwner, chatsObserver)
    }

    private fun setupChatsRecyclerView(chats: List<ChatDto>) {
        if (chats.isNotEmpty()) {
            binding.chatListRecyclerView.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

            binding.chatListRecyclerView.adapter = ChatsListAdapter(chats)
        }
    }

    private fun setupOnBackButtonClick() {
        binding.chatListBackImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}