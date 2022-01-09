package com.wing.tree.reptile.tree.presentation.adapter.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wing.tree.reptile.tree.presentation.view.profile.ProfileListFragment

class FragmentStateAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = ITEM_COUNT

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> ProfileListFragment()
            else -> throw IllegalStateException("position :$position")
        }
    }

    companion object {
        private const val ITEM_COUNT = 1
    }
}