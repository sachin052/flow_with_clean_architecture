package com.example.flowexample.core.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.flowexample.R
import com.example.flowexample.features.posts.domain.entity.PostEntity

class WeatherAdapter(private val onItemClickListener: OnItemClickListener<PostEntity>) : BaseListAdapter<PostEntity>(COMPARATOR) {
    override fun onItemClickListener(): OnItemClickListener<PostEntity> {
        return onItemClickListener
    }

    override fun inflateLayout(): Int {
       return R.layout.weather_item_layout
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<PostEntity>() {
            override fun areItemsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean =
                oldItem.postBody == newItem.postBody

            override fun areContentsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean =
                oldItem.postTitle == newItem.postTitle

        }
    }
}