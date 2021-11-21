package com.example.sabbir.pixabaysearch.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sabbir.pixabaysearch.models.ImageHit

/**
 * Created by himelhimu on 11/20/2021
 * Md Sabbir Ahmed (Himel)
 * This is a free of charge whatever you want to do with it code,
 * However the individul librays used in here might haver their own licensing.
 */
class ImageListAdapter(private val viewModel: MainViewModel,val itemList:List<ImageHit>) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(viewModel,itemList[position])
    }

    override fun getItemCount(): Int {
       return itemList.size
    }



}

class ListDiffCallback : DiffUtil.ItemCallback<ImageHit>() {
    override fun areItemsTheSame(oldItem: ImageHit, newItem: ImageHit): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageHit, newItem: ImageHit): Boolean {
        return oldItem == newItem
    }
}