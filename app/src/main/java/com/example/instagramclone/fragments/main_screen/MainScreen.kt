package com.example.instagramclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.instagramclone.databinding.FragmentMainScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreen() : Fragment() {

    private lateinit var binding: FragmentMainScreenBinding
    private val mainScreenViewModel by viewModels<MainScreenViewModel>()
    val TAG = "MainScreen"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        collectLatestLifecycleFlow(mainScreenViewModel.uiState) { result ->
//            when (result) {
//                is MainScreenState.Success -> {
//                    binding.composeView.setContent {
//                        MainScreenComposable(
//                            postList = result.postList,
//                            savePost = { item ->
//                                mainScreenViewModel.savePost(item)
//                            }
//                        )
//                    }
//                }
//                is MainScreenState.Failure -> {
//                }
//                is MainScreenState.Loading -> {
//                }
//                else -> Unit
//            }
//        }
    }
}
