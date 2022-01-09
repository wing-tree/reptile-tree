package com.wing.tree.reptile.tree.presentation.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.wing.tree.reptile.tree.presentation.Key
import com.wing.tree.reptile.tree.presentation.TransitionName
import com.wing.tree.reptile.tree.presentation.databinding.ActivityProfileBinding
import com.wing.tree.reptile.tree.presentation.parcelable.ParcelableProfile
import com.wing.tree.reptile.tree.presentation.view.base.BaseActivity

class ProfileActivity : BaseActivity<ActivityProfileBinding>() {
    override fun inflate(inflater: LayoutInflater): ActivityProfileBinding {
        return ActivityProfileBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    private fun initData() {
        val parcelableProfile = intent?.getParcelableExtra<ParcelableProfile>(Key.PARCELABLE_PROFILE)

        with(viewBinding) {
            imageViewProfilePicture.transitionName = TransitionName.PROFILE_PHOTO
            textViewName.transitionName = TransitionName.NAME

            Glide.with(applicationContext)
                .load(parcelableProfile?.photo)
                .transform(CenterCrop())
                .into(imageViewProfilePicture)

            textViewName.text = parcelableProfile?.name
        }
    }
}