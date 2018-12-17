package com.training.victor.development.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.training.victor.development.R
import com.training.victor.development.data.models.ImageViewModel
import com.training.victor.development.utils.inflate

class ProfilesAdapter(private val profilesList: ArrayList<ImageViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // TODO :: re implement design
        return CreatorViewHolder(parent.inflate(R.layout.adapter_profile_item))
    }

    override fun getItemCount(): Int {
        return profilesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CreatorViewHolder) {
            holder.bind(profilesList[position])
        }
    }

    class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(profileItem: ImageViewModel) = with(itemView) {
            // TODO :: set all corresponding data!
        }
    }
}