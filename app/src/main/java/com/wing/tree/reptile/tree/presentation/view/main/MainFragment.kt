package com.wing.tree.reptile.tree.presentation.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.wing.tree.reptile.tree.R
import com.wing.tree.reptile.tree.databinding.FragmentMainBinding
import com.wing.tree.reptile.tree.presentation.adapter.main.FragmentStateAdapter
import com.wing.tree.reptile.tree.presentation.view.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private object Tab {
        val textArray = arrayOf(
            R.string.home
        )
    }

    private fun bind() {
        with(viewBinding) {
            viewPager2.adapter = FragmentStateAdapter(this@MainFragment)

            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = getString(Tab.textArray[position])
            }.attach()
        }
    }
}