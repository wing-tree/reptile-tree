package com.wing.tree.reptile.tree.presentation.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.wing.tree.reptile.tree.R
import com.wing.tree.reptile.tree.databinding.FragmentProfileCreationBinding
import com.wing.tree.reptile.tree.presentation.view.base.BaseFragment
import com.wing.tree.reptile.tree.presentation.viewmodel.profile.ProfileCreationViewModel

class ProfileCreationFragment : BaseFragment<FragmentProfileCreationBinding>() {
    private val viewModel by viewModels<ProfileCreationViewModel>()

    override fun inflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileCreationBinding {
        return FragmentProfileCreationBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
    }

    private fun bind() {
        with(viewBinding) {
            toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }
}