package com.example.sabbir.pixabaysearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sabbir.pixabaysearch.databinding.ListItemBinding
import com.example.sabbir.pixabaysearch.models.ImageHit

/**
 * Created by himelhimu on 11/20/2021
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */
class ImageViewHolder private constructor(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup): ImageViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(layoutInflater, parent, false)

            return ImageViewHolder(binding)
        }
    }

    fun bind(viewModel: MainViewModel, item: ImageHit) {
        binding.viewmodel = viewModel
        binding.image = item
        binding.executePendingBindings()
    }


}