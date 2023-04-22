package com.nastirlex.cinema.presentation.chats.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.nastirlex.cinema.R
import com.nastirlex.cinema.data.di.interceptors.AuthorizationInterceptor
import com.nastirlex.cinema.data.di.interceptors.RefreshTokenAuthenticator
import com.nastirlex.cinema.data.dto.MessageDto
import com.nastirlex.cinema.data.repositoryImpl.JwtRepositoryImpl
import com.nastirlex.cinema.data.utils.Resource
import com.nastirlex.cinema.databinding.FragmentChatBinding
import com.nastirlex.cinema.presentation.chats.chat.adapter.ChatMessagesListAdapter
import com.nastirlex.cinema.utils.PurchaseConfirmationDialogFragment
import com.nastirlex.cinema.websocket.ChatWebSocketListener
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    private lateinit var chatWebSocketListener: ChatWebSocketListener

    private var websocket: WebSocket? = null

    private val args: ChatFragmentArgs by navArgs()

    private val jwtRepositoryImpl by lazy { JwtRepositoryImpl() }

    private val chatViewModel by lazy {
        ChatViewModel(
            requireActivity().application,
            args.id
        )
    }

    private lateinit var adapter: ChatMessagesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        chatWebSocketListener = ChatWebSocketListener(chatViewModel)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)

        binding.messagesRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        adapter =
            ChatMessagesListAdapter(jwtRepositoryImpl.getUserId(requireContext().applicationContext)!!)

        binding.messagesRecyclerView.adapter = adapter

        chatViewModel.messages.observe(viewLifecycleOwner) {
            adapter.addMessage(it)
            binding.messagesRecyclerView.scrollToPosition(adapter.itemCount - 1)
        }

        val okHttpClient = OkHttpClient.Builder()
            .authenticator(RefreshTokenAuthenticator(requireContext()))
            .addInterceptor(AuthorizationInterceptor(requireContext()))
            .build()

        websocket = okHttpClient.newWebSocket(createRequest(), chatWebSocketListener)


        setupNameObserver()
        setupOnBackButtonClick()
        setupSendButtonClick()
        //setupMessagesObserver()
        setupChatScreenStateObserver()

        return binding.root
    }

    private fun setupChatScreenStateObserver() {
        val chatScreenStateObserver: Observer<Resource<Any>> = Observer {
            when (it) {
                is Resource.Error -> {
                    PurchaseConfirmationDialogFragment(it.message!!).show(
                        childFragmentManager, PurchaseConfirmationDialogFragment.TAG
                    )
                }

                else -> {}
            }
        }

        chatViewModel.chatScreenState.observe(viewLifecycleOwner, chatScreenStateObserver)
    }

    private fun setupOnBackButtonClick() {
        binding.chatBackImageButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupNameObserver() {
        val nameObserver: Observer<String> = Observer {
            binding.chatNameTextView.text = it
        }

        chatViewModel.chatName.observe(viewLifecycleOwner, nameObserver)
    }


    private fun setupSendButtonClick() {
        binding.sendImageButton.setOnClickListener {
            if (binding.edtInput.text.isEmpty()) {
                PurchaseConfirmationDialogFragment(R.string.error_empty_message).show(
                    childFragmentManager, PurchaseConfirmationDialogFragment.TAG)
            } else {
                websocket?.send(binding.edtInput.text.toString())
            }

            binding.edtInput.text.clear()
        }
    }


    private fun createRequest(): Request {
        val websocketURL = "ws://107684.web.hosting-russia.ru:8000/api/chats/${args.id}/messages"

        return Request.Builder()
            .url(websocketURL)
            .build()
    }

}