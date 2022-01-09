package com.wing.tree.reptile.tree.presentation.view.base

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class LinearLayoutManagerWrapper(context: Context, orientation: Int = RecyclerView.VERTICAL, reverseLayout: Boolean = false) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            Timber.e(e)
        }
    }
}