package com.wing.tree.reptile.tree.presentation.view.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wing.tree.reptile.tree.presentation.constant.Key
import com.wing.tree.reptile.tree.presentation.databinding.FragmentSingleChoiceDialogBinding
import com.wing.tree.reptile.tree.presentation.databinding.SingleChoiceItemBinding

class SingleChoiceDialogFragment : BaseDialogFragment<FragmentSingleChoiceDialogBinding>() {
    interface OnItemChoiceListener {
        fun onItemChoice(item: String)
    }

    private var onItemChoiceListener: OnItemChoiceListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        with(parentFragment) {
            if (this is OnItemChoiceListener) {
                onItemChoiceListener = this
            }
        }
    }

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSingleChoiceDialogBinding {
        return FragmentSingleChoiceDialogBinding.inflate(inflater, container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        val array = arguments?.getStringArray(Key.STRING_ARRAY) ?: emptyArray()

        with(viewBinding) {
            recyclerView.apply {
                adapter = Adapter(array)
                layoutManager = LinearLayoutManagerWrapper(context)
            }
        }

        return viewBinding.root
    }

    inner class Adapter(private val array: Array<String>): RecyclerView.Adapter<Adapter.ViewHolder>() {
        inner class ViewHolder(val viewBinding: SingleChoiceItemBinding): RecyclerView.ViewHolder(viewBinding.root) {
            fun bind(item: String) {
                viewBinding.root.text = item
                with(viewBinding) {
                    root.text = item
                    root.setOnClickListener {
                        onItemChoiceListener?.onItemChoice(item)
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val viewBinding = SingleChoiceItemBinding.inflate(layoutInflater, parent, false)

            return ViewHolder(viewBinding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(array[position])
        }

        override fun getItemCount(): Int = array.count()
    }


    companion object {
        fun newInstance(array: Array<String>): SingleChoiceDialogFragment {
            val bundle = Bundle().apply {
                putStringArray(Key.STRING_ARRAY, array)
            }

            return SingleChoiceDialogFragment().apply {
                arguments = bundle
            }
        }
    }
}