package com.example.instagramclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.instagramclone.databinding.FragmentSearchScreenBinding

class SearchScreen : Fragment() {
    private lateinit var binding: FragmentSearchScreenBinding

    val args: SearchScreenArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchScreenBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.adText.text = args.numbers.toString()

        binding.adText.setOnClickListener() {
//           Navigation.findNavController(binding.root).navigate(R.id.action_searchScreen_to_loginFragment)
        }
    }
}
