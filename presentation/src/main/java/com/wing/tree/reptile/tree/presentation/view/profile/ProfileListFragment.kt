package com.wing.tree.reptile.tree.presentation.view.profile

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.wing.tree.reptile.tree.presentation.Key
import com.wing.tree.reptile.tree.presentation.TransitionName
import com.wing.tree.reptile.tree.presentation.adapter.profile.ProfileListAdapter
import com.wing.tree.reptile.tree.presentation.databinding.FragmentProfileListBinding
import com.wing.tree.reptile.tree.presentation.parcelable.ParcelableProfile
import com.wing.tree.reptile.tree.presentation.view.base.BaseFragment
import com.wing.tree.reptile.tree.presentation.view.main.MainFragmentDirections
import com.wing.tree.reptile.tree.presentation.viewmodel.profile.ProfileListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileListFragment : BaseFragment<FragmentProfileListBinding>() {
    private val viewModel by viewModels<ProfileListViewModel>()

    private val profileListAdapter by lazy {
        ProfileListAdapter { viewBinding, item, adapterPosition ->
            selectedAdapterPosition = adapterPosition

            with(viewBinding) {
                imageViewProfilePicture.transitionName = TransitionName.PROFILE_PHOTO
                textViewName.transitionName = TransitionName.NAME

                val intent = Intent(context, ProfileActivity::class.java).apply {
                    putExtra(Key.PARCELABLE_PROFILE, ParcelableProfile.from(item))
                }

                val activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                    activity,
                    Pair(imageViewProfilePicture, TransitionName.PROFILE_PHOTO),
                    Pair(textViewName, TransitionName.NAME)
                )

                startActivity(intent, activityOptions.toBundle())
            }
        }
    }

    private var selectedAdapterPosition = -1

    override fun inflate(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileListBinding {
        return FragmentProfileListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        bind()
        observe()
    }

    private fun initData() {
        viewModel.getProfileList()
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