package com.wing.tree.reptile.tree.presentation.adapter.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.wing.tree.reptile.tree.domain.model.Diary
import com.wing.tree.reptile.tree.presentation.databinding.DiaryItemBinding
import timber.log.Timber

class DiaryListAdapter : ListAdapter<Diary, DiaryListAdapter.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = DiaryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val viewBinding: DiaryItemBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: Diary) {
            with(viewBinding) {
                if (item.imageArray.isNotEmpty()) {
                    val image = item.imageArray[0]

                    Glide.with(root)
                        .load(image.filePath)
                        .transform(CenterCrop())
                        .into(imageView)
                }

                textViewTitle.text = item.title
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Diary>() {
        override fun areItemsTheSame(oldItem: Diary, newItem: Diary): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Diary, newItem: Diary): Boolean {
            return oldItem == newItem
        }
    }
}