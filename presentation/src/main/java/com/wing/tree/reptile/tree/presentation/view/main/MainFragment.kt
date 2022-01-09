package com.wing.tree.reptile.tree.presentation.view.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.wing.tree.reptile.tree.presentation.R
import com.wing.tree.reptile.tree.presentation.adapter.main.FragmentStateAdapter
import com.wing.tree.reptile.tree.presentation.databinding.FragmentMainBinding
import com.wing.tree.reptile.tree.presentation.view.base.BaseFragment
import timber.log.Timber

class MainFragment : BaseFragment<FragmentMainBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()

        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {

                Log.d("sjk", "a;;;: $names")
            }
        })
    }

    private object Tab {
        val textArray = arrayOf(
            R.string.home,
            R.string.calendar
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