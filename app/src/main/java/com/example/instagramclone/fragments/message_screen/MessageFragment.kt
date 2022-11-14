package com.example.instagramclone.fragments.message_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.example.instagramclone.adapters.MessageAdapter
import com.example.instagramclone.adapters.MessageAdapter.urlUtil
import com.example.instagramclone.databinding.FragmentMessageBinding
import com.example.instagramclone.util.collectLatestLifecycleFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageFragment : Fragment() {
    private lateinit var fragmentMessageBinding: FragmentMessageBinding
    private val messageViewModel by viewModels<MessageViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMessageBinding = FragmentMessageBinding.inflate(layoutInflater)
        return fragmentMessageBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val messagesAdapter = MessageAdapter()

        collectLatestLifecycleFlow(messageViewModel.uiState) {
            when (it) {
                is MessageScreenState.Success -> {
                    messagesAdapter.submitList(it.messageList)
                }
                else -> Unit
            }
        }

        fragmentMessageBinding.userProfileImage.apply {
            load(urlUtil.getUrl(34)) {
                transformations(CircleCropTransformation())
            }
        }

        fragmentMessageBinding.rvMessages.apply {
            adapter = messagesAdapter
            layoutManager = LinearLayoutManager(this.context)
        }
        fragmentMessageBinding.toolbar.setNavigationOnClickListener() {
            Navigation.findNavController(fragmentMessageBinding.root).popBackStack()
        }
    }
}
