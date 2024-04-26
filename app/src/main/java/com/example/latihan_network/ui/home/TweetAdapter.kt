package com.example.latihan_network.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.latihan_network.databinding.RvItemBinding
import com.example.latihan_network.model.Tweet

class TweetAdapter : ListAdapter<Tweet, TweetAdapter.TweetViewHolder>(DiffCallback){


    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int):
            TweetAdapter.TweetViewHolder{
        return TweetViewHolder(RvItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TweetViewHolder(private var binding : RvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (tweet: Tweet){
            val add_at_in_author = "@"+tweet.author
            binding.tvInfoTitle.text = add_at_in_author
            binding.tvPrice.text = tweet.teks
            binding.tvId.text = tweet.id.toString()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Tweet>() {
        override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean {
            return oldItem.teks == newItem.teks
        }
    }

}