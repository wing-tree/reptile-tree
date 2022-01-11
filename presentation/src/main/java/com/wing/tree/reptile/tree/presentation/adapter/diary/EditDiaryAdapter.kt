package com.wing.tree.reptile.tree.presentation.adapter.diary

import android.net.Uri
import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.wing.tree.reptile.tree.domain.model.Diary
import com.wing.tree.reptile.tree.presentation.constant.BLANK
import com.wing.tree.reptile.tree.presentation.databinding.ImageItemBinding
import com.wing.tree.reptile.tree.presentation.databinding.TextItemBinding
import com.wing.tree.reptile.tree.presentation.databinding.TitleItemBinding
import java.io.File

class EditDiaryAdapter : RecyclerView.Adapter<EditDiaryAdapter.ViewHolder>() {
    private val currentList = mutableListOf<AdapterItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val viewBinding = when(viewType) {
            ViewType.TITLE -> TitleItemBinding.inflate(layoutInflater, parent, false)
            ViewType.TEXT -> TextItemBinding.inflate(layoutInflater, parent, false)
            ViewType.IMAGE -> ImageItemBinding.inflate(layoutInflater, parent, false)
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
            is AdapterItem.Title -> ViewType.TITLE
            is AdapterItem.Text -> ViewType.TEXT
            is AdapterItem.Image -> ViewType.IMAGE
        }
    }

    private object ViewType {
        const val TITLE = 0
        const val TEXT = 1
        const val IMAGE = 2
    }

    inner class ViewHolder(private val viewBinding: ViewBinding): RecyclerView.ViewHolder(viewBinding.root) {
        inner class TextWatcher(
            private val onAfterTextChanged: (s: CharSequence) -> Unit
        ) : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let { onAfterTextChanged.invoke(it) }
            }
        }

        private val textWatcher = TextWatcher {
            when(val item = currentList[adapterPosition]) {
                is AdapterItem.Title -> {
                    item.text = it.toString()
                }
                is AdapterItem.Text -> {
                    item.text = it.toString()
                }
                is AdapterItem.Image -> {
                }
            }
        }

        fun bind(position: Int) {
            when(val item = currentList[position]) {
                is AdapterItem.Title -> with(viewBinding) {
                    if (this is TitleItemBinding) {
                        bind(item)
                    }
                }
                is AdapterItem.Text -> with(viewBinding) {
                    if (this is TextItemBinding) {
                        bind(item)
                    }
                }
                is AdapterItem.Image -> with(viewBinding) {
                    if (this is ImageItemBinding) {
                        bind(item)
                    }
                }
            }
        }

        private fun TitleItemBinding.bind(item: AdapterItem.Title) {
            with(editText) {
                removeTextChangedListener(textWatcher)
                setText(item.text)
                addTextChangedListener(textWatcher)
            }
        }

        private fun TextItemBinding.bind(item: AdapterItem.Text) {
            with(editText) {
                removeTextChangedListener(textWatcher)
                setText(item.text)
                addTextChangedListener(textWatcher)
            }
        }

        private fun ImageItemBinding.bind(item: AdapterItem.Image) {
            Glide.with(root.context)
                .load(item.uri)
                .transform(CenterCrop())
                .into(imageView)
        }
    }

    fun addText() {
        currentList.add(AdapterItem.Text(BLANK))
        notifyItemInserted(currentList.size.dec())
    }

    fun addImage(uri: File) {
        currentList.add(AdapterItem.Image(uri.path))
        notifyItemInserted(currentList.size.dec())
    }

    fun toDiary(id: Long): Diary {
        var title = BLANK
        val textList = mutableListOf<Diary.Content.Text>()
        val imageList = mutableListOf<Diary.Content.Image>()

        currentList.forEachIndexed { index, item ->
            when(item) {
                is AdapterItem.Title -> { title = item.text }
                is AdapterItem.Text -> textList.add(Diary.Content.Text(index, item.text))
                is AdapterItem.Image -> imageList.add(Diary.Content.Image(index, item.uri.toString()))
            }
        }

        return object : Diary() {
            override val profileId: Long
                get() = id
            override val title: String
                get() = title
            override val textArray: Array<Content.Text>
                get() = textList.toTypedArray()
            override val imageArray: Array<Content.Image>
                get() = imageList.toTypedArray()
        }
    }

    sealed class AdapterItem {
        class Title(var text: String) : AdapterItem()
        class Text(var text: String) : AdapterItem()
        class Image(var uri: String) : AdapterItem()
    }
}