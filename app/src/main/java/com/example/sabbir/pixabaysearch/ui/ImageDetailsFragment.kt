package com.example.sabbir.pixabaysearch.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sabbir.pixabaysearch.R
import com.example.sabbir.pixabaysearch.databinding.FragmentSecondBinding
import dagger.android.support.DaggerFragment

/**
 * Created by himelhimu on ${DATE}
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */
class ImageDetailsFragment : DaggerFragment() {

    private var _binding: FragmentSecondBinding? = null
    private val args: ImageDetailsFragmentArgs by navArgs()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.image = args.image
        binding.executePendingBindings()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}