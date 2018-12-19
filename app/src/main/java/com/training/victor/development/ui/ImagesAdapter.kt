package com.training.victor.development.ui

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.training.victor.development.R
import com.training.victor.development.data.models.ImageViewModel
import com.training.victor.development.utils.inflate
import kotlinx.android.synthetic.main.adapter_images_header.view.*
import kotlinx.android.synthetic.main.adapter_images_item.view.*

class ImagesAdapter(private val imageList: ArrayList<ImageViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TYPE_FIRST_HEADER = 0
        const val TYPE_HEADER = 1
        const val TYPE_ITEM = 2
        const val NO_ITEM = "NO_ITEM"
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == 0 && imageList.isNotEmpty()) {
            TYPE_FIRST_HEADER
        } else if (imageList[position].category.contentEquals(NO_ITEM)) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_HEADER || viewType == TYPE_FIRST_HEADER) {
            CreatorHeaderViewHolder(parent.inflate(R.layout.adapter_images_header))
        } else {
            CreatorItemViewHolder(parent.inflate(R.layout.adapter_images_item))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CreatorHeaderViewHolder) {
            holder.bind(imageList[position + 1].category)
        }
        else if (holder is CreatorItemViewHolder) {
            holder.bind(imageList[position])

            if (position + 1 < imageList.size
                && !imageList[position + 1].category.contentEquals(imageList[position].category)) {
                imageList.add(position + 1, ImageViewModel("", 0.0, NO_ITEM, ""))
            }

        }
    }


    class CreatorHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(headerName: String) = with(itemView) {
            txtHeader.text = headerName
        }
    }

    class CreatorItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: ImageViewModel) = with(itemView) {
            val uri = Uri.parse(image.imageUrl)
            imgImage.setImageURI(uri)
            imgImage.aspectRatio = image.aspectRatio.toFloat()
            imgImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
            txtDescription.text = image.imageDescription
        }
    }
}