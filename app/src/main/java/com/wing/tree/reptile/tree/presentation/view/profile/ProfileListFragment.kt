package com.wing.tree.reptile.tree.presentation.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.wing.tree.reptile.tree.databinding.FragmentProfileListBinding
import com.wing.tree.reptile.tree.presentation.adapter.profile.ProfileListAdapter
import com.wing.tree.reptile.tree.presentation.view.base.BaseFragment
import com.wing.tree.reptile.tree.presentation.view.main.MainFragmentDirections
import com.wing.tree.reptile.tree.presentation.viewmodel.profile.ProfileListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileListFragment : BaseFragment<FragmentProfileListBinding>() {
    private val viewModel by viewModels<ProfileListViewModel>()

    private val profileListAdapter by lazy {
        ProfileListAdapter {

        }
    }

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileListBinding {
        return FragmentProfileListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind()
        observe()
    }

    private fun bind() {
        val spanCount = 2

        with(viewBinding) {
            recyclerView.apply {
                adapter = profileListAdapter
                layoutManager = GridLayoutManager(context, spanCount)
            }

            floatingActionButton.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToProfileCreationFragment())
            }
        }
    }

    private fun observe() {
        viewModel.profileList.observe(viewLifecycleOwner, {
            profileListAdapter.submitList(it)
        })
    }
}