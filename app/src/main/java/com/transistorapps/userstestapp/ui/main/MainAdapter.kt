package com.transistorapps.userstestapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.transistorapps.userstestapp.R
import com.transistorapps.userstestapp.databinding.UsersListItemBinding
import com.transistorapps.userstestapp.domain.UserUiModel
import com.transistorapps.userstestapp.extensions.setCircleImage

class MainAdapter(private val clickListener: (UserUiModel) -> Unit) :
    ListAdapter<UserUiModel, MainViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UsersListItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            holder.bind(uiModel)
        }
    }
}

class MainViewHolder(
    binding: UsersListItemBinding,
    clickListener: (UserUiModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private val ivAvatar = binding.ivAvatar
    private val tvName = binding.tvName
    private val tvCountValue = binding.tvCountValue

    private var item: UserUiModel? = null

    init {
        binding.root.setOnClickListener {
            item?.let { clickListener(it) }
        }
    }

    fun bind(userUiModel: UserUiModel) {
        this.item = userUiModel
        tvName.text = userUiModel.name
        tvCountValue.text = userUiModel.posts.size.toString()
        ivAvatar.setCircleImage(userUiModel.thumbnailUrl, R.drawable.ic_sync)
    }
}

private class DiffCallback : DiffUtil.ItemCallback<UserUiModel>() {
    override fun areItemsTheSame(oldItem: UserUiModel, newItem: UserUiModel): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(old: UserUiModel, new: UserUiModel): Boolean = old == new
}