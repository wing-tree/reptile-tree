package com.wing.tree.reptile.tree.presentation.view.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wing.tree.reptile.tree.presentation.databinding.FragmentAlarmListBinding
import com.wing.tree.reptile.tree.presentation.view.base.BaseFragment

class AlarmListFragment : BaseFragment<FragmentAlarmListBinding>() {
    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAlarmListBinding {
        return FragmentAlarmListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        with(viewBinding) {
            imageViewAddAlarm.setOnClickListener {
                addFragment(RegisterAlarmFragment())
            }
        }
    }
}