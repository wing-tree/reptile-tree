package com.wing.tree.reptile.tree.presentation.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.wing.tree.reptile.tree.presentation.R
import com.wing.tree.reptile.tree.presentation.adapter.profile.DiaryListAdapter
import com.wing.tree.reptile.tree.presentation.constant.Extra
import com.wing.tree.reptile.tree.presentation.constant.TransitionName
import com.wing.tree.reptile.tree.presentation.databinding.ActivityProfileBinding
import com.wing.tree.reptile.tree.presentation.parcelable.ParcelableProfile
import com.wing.tree.reptile.tree.presentation.view.base.BaseActivity
import com.wing.tree.reptile.tree.presentation.view.base.LinearLayoutManagerWrapper
import com.wing.tree.reptile.tree.presentation.view.diary.EditDiaryFragment
import com.wing.tree.reptile.tree.presentation.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : BaseActivity<ActivityProfileBinding>() {
    private val viewModel by viewModels<ProfileViewModel>()
    private val diaryListAdapter = DiaryListAdapter()

    override fun inflate(inflater: LayoutInflater): ActivityProfileBinding {
        return ActivityProfileBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        observe()
    }

    private fun bind() {
        val parcelableProfile = intent?.getParcelableExtra<ParcelableProfile>(Extra.PARCELABLE_PROFILE)
            ?: run {
                finishAfterTransition()
                return@bind
            }

        viewModel.profile = parcelableProfile
        viewModel.diaryList(parcelableProfile.id)

        with(viewBinding) {
            imageViewProfilePicture.transitionName = TransitionName.PROFILE_PHOTO
            textViewName.transitionName = TransitionName.NAME

            Glide.with(applicationContext)
                .load(parcelableProfile.imageFilePath)
                .transform(CenterCrop())
                .into(imageViewProfilePicture)

            textViewName.text = parcelableProfile.name

            recyclerViewDiary.apply {
                adapter = diaryListAdapter
                layoutManager = LinearLayoutManagerWrapper(context)
                setHasFixedSize(true)
            }

            imageViewAddDiary.setOnClickListener {
                replaceFragment(EditDiaryFragment.newInstance(parcelableProfile))
            }
        }
    }

    private fun observe() {
        viewModel.diaryList.observe(this) {
            diaryListAdapter.submitList(it)
        }
    }
}