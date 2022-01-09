package com.wing.tree.reptile.tree.presentation.adapter.diary

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.wing.tree.reptile.tree.presentation.databinding.PictureItemBinding
import com.wing.tree.reptile.tree.presentation.databinding.TextEditorItemBinding
import com.wing.tree.reptile.tree.presentation.databinding.TextItemBinding

class DiaryAdapter : RecyclerView.Adapter<DiaryAdapter.ViewHolder>() {
    private val currentList = mutableListOf<AdapterItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val viewBinding = when(viewType) {
            ViewType.TEXT -> TextItemBinding.inflate(layoutInflater, parent, false)
            ViewType.TEXT_EDITOR -> TextEditorItemBinding.inflate(layoutInflater, parent, false)
            ViewType.PICTURE -> PictureItemBinding.inflate(layoutInflater, parent, false)
            else -> throw IllegalArgumentException("viewType :$viewType")
        }

        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = currentList.count()

    override fun getItemViewType(position: Int): Int {
        return when(currentList[position]) {
            is AdapterItem.Text -> ViewType.TEXT
            is AdapterItem.TextEditor -> ViewType.TEXT_EDITOR
            is AdapterItem.Picture -> ViewType.PICTURE
        }
    }

    private object ViewType {
        const val TEXT = 0
        const val TEXT_EDITOR = 1
        const val PICTURE = 2
    }

    inner class ViewHolder(private val viewBinding: ViewBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(position: Int) {
            when(val item = currentList[position]) {
                is AdapterItem.Text -> with(viewBinding) {
                    if (this is TextItemBinding) {
                        bind(item)
                    }
                }
                is AdapterItem.TextEditor -> with(viewBinding) {
                    if (this is TextEditorItemBinding) {
                        bind(item)
                    }
                }
                is AdapterItem.Picture -> with(viewBinding) {
                    if (this is PictureItemBinding) {
                        bind(item)
                    }
                }
            }
        }

        private fun TextItemBinding.bind(item: AdapterItem.Text) {

        }

        private fun TextEditorItemBinding.bind(item: AdapterItem.TextEditor) {

        }

        private fun PictureItemBinding.bind(item: AdapterItem.Picture) {
            Glide.with(root.context)
                .load(item.uri)
                .transform(CenterCrop())
                .into(imageViewPicture)
        }
    }

    fun addTextEditor() {
        currentList.add(AdapterItem.TextEditor())
        notifyItemInserted(currentList.size.dec())
    }

    fun addPicture(pictureUri: Uri) {
        currentList.add(AdapterItem.Picture(pictureUri))
        notifyItemInserted(currentList.size.dec())
    }

    sealed class AdapterItem {
        class Text : AdapterItem()
        class TextEditor : AdapterItem()
        class Picture(val uri: Uri) : AdapterItem()
    }
}