package com.wing.tree.reptile.tree.presentation.view.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import com.wing.tree.reptile.tree.databinding.FragmentProfileBinding
import com.wing.tree.reptile.tree.presentation.view.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }
}