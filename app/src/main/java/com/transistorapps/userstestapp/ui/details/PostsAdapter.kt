package com.transistorapps.userstestapp.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.transistorapps.userstestapp.databinding.PostListItemBinding
import com.transistorapps.userstestapp.domain.PostUiModel

class PostsAdapter : ListAdapter<PostUiModel, PostViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostListItemBinding.inflate(inflater, parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            holder.bind(uiModel)
        }
    }
}

class PostViewHolder(
    binding: PostListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private val tvTitle = binding.tvTitle
    private val tvBody = binding.tvBody

    fun bind(postUiModel: PostUiModel) {
        tvTitle.text = postUiModel.title
        tvBody.text = postUiModel.body
    }
}

private class DiffCallback : DiffUtil.ItemCallback<PostUiModel>() {
    override fun areItemsTheSame(oldItem: PostUiModel, newItem: PostUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(old: PostUiModel, new: PostUiModel): Boolean = old == new
}