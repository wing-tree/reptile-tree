package com.wing.tree.reptile.tree.presentation.adapter.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wing.tree.reptile.tree.domain.model.Profile
import com.wing.tree.reptile.tree.presentation.databinding.ProfileListItemBinding

class ProfileListAdapter(
    private val onClick: (viewBinding: ProfileListItemBinding, item: Profile) -> Unit
) : ListAdapter<Profile, ProfileListAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ProfileListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val viewBinding: ProfileListItemBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: Profile) {
            with(viewBinding) {
                Glide.with(imageViewProfilePicture)
                    .load(item.profilePictureUri)
                    .centerCrop()
                    .into(imageViewProfilePicture)

                textViewName.text = item.name

                root.setOnClickListener {
                    onClick(this, item)
                }
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem == newItem
        }
    }
}