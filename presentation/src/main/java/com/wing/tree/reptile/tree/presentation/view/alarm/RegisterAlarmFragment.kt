package com.wing.tree.reptile.tree.presentation.view.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wing.tree.reptile.tree.presentation.databinding.FragmentRegisterAlarmBinding
import com.wing.tree.reptile.tree.presentation.view.base.BaseFragment

class RegisterAlarmFragment : BaseFragment<FragmentRegisterAlarmBinding>() {
    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegisterAlarmBinding {
        return FragmentRegisterAlarmBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        with(viewBinding) {

        }
    }
}