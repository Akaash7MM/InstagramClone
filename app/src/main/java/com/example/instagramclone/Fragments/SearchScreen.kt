package com.example.instagramclone.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.instagramclone.R
import com.example.instagramclone.databinding.FragmentSearchScreenBinding

class SearchScreen : Fragment() {
    private lateinit var binding: FragmentSearchScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchScreenBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.adText.setOnClickListener() {
            Navigation.findNavController(binding.root).navigate(R.id.action_searchScreen_to_mainScreen)
        }
    }
}
