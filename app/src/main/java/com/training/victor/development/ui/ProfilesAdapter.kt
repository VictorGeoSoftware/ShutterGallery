package com.training.victor.development.ui

import android.graphics.Color
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.training.victor.development.R
import com.training.victor.development.data.models.ImageViewModel
import com.training.victor.development.utils.inflate
import kotlinx.android.synthetic.main.adapter_profile_item.view.*

class ProfilesAdapter(private val imageList: ArrayList<ImageViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    // https://stackoverflow.com/questions/35682292/implementing-recyclerview-with-sectioned-header-based-on-category
    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_ITEM = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // TODO :: adjust simpleDrawee size
        return CreatorViewHolder(parent.inflate(R.layout.adapter_profile_item))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CreatorViewHolder) {
            holder.bind(imageList[position])
        }
    }

    class CreatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: ImageViewModel) = with(itemView) {
            val uri = Uri.parse(image.imageUrl)
            imgImage.setImageURI(uri)
            imgImage.aspectRatio = image.aspectRatio.toFloat()
            imgImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
            txtDescription.text = image.imageDescription

            when {
                image.category == "photo" -> {
                    itemView.setBackgroundColor(Color.parseColor("#3F51B5"))
                }
                image.category == "vector" -> {
                    itemView.setBackgroundColor(Color.parseColor("#C5CAE9"))
                }
                else -> {
                    itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
                }
            }
        }
    }
}